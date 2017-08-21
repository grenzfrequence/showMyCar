package com.grenzfrequence.showmycar.car_types.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.grenzfrequence.showmycar.R
import com.grenzfrequence.showmycar.car_types.data.model.BuiltDatesModel
import com.grenzfrequence.showmycar.car_types.data.model.MainTypesModel
import com.grenzfrequence.showmycar.car_types.data.model.ManufacturersModel
import com.grenzfrequence.showmycar.car_types.data.model.base.CarTypesModel
import com.grenzfrequence.showmycar.car_types.data.model.base.EmptyCarTypesModel
import com.grenzfrequence.showmycar.car_types.data.repos.BuiltDatesRepository
import com.grenzfrequence.showmycar.car_types.data.repos.MainTypesRepository
import com.grenzfrequence.showmycar.car_types.data.repos.Repository
import com.grenzfrequence.showmycar.car_types.ui.CarTypesItem
import com.grenzfrequence.showmycar.car_types.ui.base.CarTypesView
import com.grenzfrequence.showmycar.car_types.viewmodel.base.BaseViewModel
import com.grenzfrequence.showmycar.common.ErrMsg
import com.grenzfrequence.showmycar.common.values.HttpResponses
import com.grenzfrequence.showmycar.di.qualifiers.ErrorMessages
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by grenzfrequence on 14.08.17.
 */

class CarTypesViewModel<MODEL : CarTypesModel, REPO : Repository<MODEL>>
@Inject
constructor(
        val repository: REPO,
        @field:ErrorMessages private val errorMessages: Map<Int, ErrMsg>
)
    : BaseViewModel<CarTypesView, MODEL>() {

    val showList = ObservableBoolean(false)
    val showPlaceholder = ObservableBoolean(false)
    val placeHolderIconId = ObservableInt(R.drawable.ic_info_outline_black)
    val errorMessageId = ObservableInt()

    val manufacturer = ObservableField("")
    val mainType = ObservableField("")

    val showManufacturer = ObservableBoolean(false)
    val showMainType = ObservableBoolean(false)

    private var carTypeDisposable: Disposable? = null
    private var wkdaList: ArrayList<CarTypesItem> = ArrayList()

    init {
        val errorId: Int? = errorMessages.get(HttpResponses.HTTP_CUSTOM_DEFAULT)?.errorMessageId
        errorId?.let { errorMessageId.set(errorId) }
    }

    override fun detachView() {
        super.detachView()
        carTypeDisposable?.dispose()
    }

    private fun updateUiTitle(carTypes: CarTypesModel) {
        when (carTypes) {
            is ManufacturersModel -> {
                showManufacturer.set(false)
                showMainType.set(false)
            }
            is MainTypesModel -> {
                val repoMainTypes = repository as MainTypesRepository
                manufacturer.set(repoMainTypes.manufacturer.value)
                showManufacturer.set(true)
            }
            is BuiltDatesModel -> {
                val repoBuiltDates = repository as BuiltDatesRepository
                manufacturer.set(repoBuiltDates.manufacturer.value)
                showManufacturer.set(true)
                mainType.set(repoBuiltDates.mainType.value)
                showMainType.set(true)
            }
        }
    }

    private fun updateUi(carTypes: CarTypesModel, reset: Boolean) {
        updateUiTitle(carTypes)

        if (reset) {
            wkdaList.clear()
        }
        wkdaList.addAll(carTypes.wkda.entries)
        view?.setListItems(wkdaList, reset)

        onRefresh(false, wkdaList.isEmpty())
    }

    override fun loadData(reset: Boolean) {
        updateUiTitle(modelData ?: EmptyCarTypesModel(HashMap()))
        if (reset || (!reset && !isLastPage())) {
            if (reset) {
                onRefresh(true)
                modelData?.wkda?.clear()
            }
            val apiRepositoryObservable = if (reset) repository.firstPage() else repository.nextPage()
            carTypeDisposable?.dispose()
            carTypeDisposable = apiRepositoryObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        carTypesResponse ->
                        modelData = carTypesResponse.body()
                        val isModelDataExists = !(modelData?.wkda?.isEmpty() ?: true)
                        val isError = manageBusinessCaseErrors(carTypesResponse, isModelDataExists)
                        onRefresh(false, isError)
                        if (isError) {
                            return@subscribe
                        }
                        updateUi(modelData ?: EmptyCarTypesModel(HashMap()), reset)
                    }, {
                        throwable ->
                        onRefresh(false, true)
                        manageErrors(throwable)
                    })
        }
    }

    override fun isLastPage(): Boolean {
        return modelData?.isLastPage() ?: false
    }

    fun onRefresh(showRefreshProgressBar: Boolean, isError: Boolean = false) {
        if (showRefreshProgressBar) {
            showList.set(false)
            showPlaceholder.set(false)
        } else if (isError) {
            showList.set(false)
            showPlaceholder.set(true)
        } else {
            showList.set(true)
            showPlaceholder.set(false)
        }
        view?.onRefresh(showRefreshProgressBar)
    }

    private fun manageErrors(throwable: Throwable) {
        val errMsg = errorMessages.get(HttpResponses.HTTP_CUSTOM_DEFAULT)
        errMsg?.let {
            placeHolderIconId.set(errMsg.errorIconId)
            errorMessageId.set(errMsg.errorMessageId)
        }
    }

    private fun manageBusinessCaseErrors(response: Response<*>, isModelDataExists: Boolean): Boolean {
        var errMsg: ErrMsg? = null
        placeHolderIconId.set(R.drawable.ic_info_outline_black)
        val errorCode = response.code()
        if (errorCode != HttpResponses.HTTP_OK) {
            errMsg = errorMessages.get(errorCode) ?: errorMessages.get(HttpResponses.HTTP_CUSTOM_DEFAULT)
        } else if (!isModelDataExists) {
            errMsg = errorMessages.get(HttpResponses.HTTP_NOT_FOUND)
        }
        if (errMsg != null) {
            val errMessageId = errMsg.errorMessageId
            errorMessageId.set(errMessageId)
            placeHolderIconId.set(errMsg.errorIconId)

            showPlaceholder.set(true)
            showList.set(false)
        }
        return errMsg != null
    }
}
