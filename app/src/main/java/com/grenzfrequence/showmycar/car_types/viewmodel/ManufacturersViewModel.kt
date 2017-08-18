package com.grenzfrequence.showmycar.car_types.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import com.grenzfrequence.showmycar.R
import com.grenzfrequence.showmycar.car_types.data.Repos.ManufacturersRepository
import com.grenzfrequence.showmycar.car_types.data.model.ManufacturersModel
import com.grenzfrequence.showmycar.car_types.ui.ManufacturerItem
import com.grenzfrequence.showmycar.car_types.ui.ManufacturersView
import com.grenzfrequence.showmycar.car_types.viewmodel.base.BaseViewModel
import com.grenzfrequence.showmycar.common.ErrMessages
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

class ManufacturersViewModel
@Inject
constructor(
        private val repository: ManufacturersRepository,
        @field:ErrorMessages private val errorMessages: ErrMessages
)
    : BaseViewModel<ManufacturersView, ManufacturersModel>() {

    companion object {
        val TAG_INSTANCE_STATE_VIEW_MODEL = ManufacturersViewModel::class.java.simpleName
    }

    val showList = ObservableBoolean(false)
    val showPlaceholder = ObservableBoolean(false)
    val placeHolderIconId = ObservableInt(R.drawable.ic_info_outline_black)
    val errorMessageId = ObservableInt()

    var manufacturersDisposable: Disposable? = null
    var wkdaList: ArrayList<ManufacturerItem> = ArrayList()

    init {
        val errorId: Int? = errorMessages.get(HttpResponses.HTTP_CUSTOM_DEFAULT)?.errorMessageId
        errorId?.let { errorMessageId.set(errorId) }
    }

    override fun detachView() {
        super.detachView()
        manufacturersDisposable?.dispose()
    }

    private fun updateUi(manufacturers: ManufacturersModel, reset: Boolean) {
        if (reset) {
            wkdaList.clear()
        }
        wkdaList.addAll(manufacturers.wkda.entries)
        view?.setListItems(wkdaList, reset)

        onRefresh(false, wkdaList.isEmpty())
    }

    override fun loadData(reset: Boolean) {
        if (reset || (!reset && !isLastPage())) {
            if (reset) {
                onRefresh(true)
                modelData?.wkda?.clear()
            }
            val apiRepositoryObservable = if (reset) repository.firstPage() else repository.nextPage()
            manufacturersDisposable?.dispose()
            manufacturersDisposable = apiRepositoryObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        manufacturersResponse ->
                        modelData = manufacturersResponse.body()
                        val isModelDataExists = !(modelData?.wkda?.isEmpty() ?: true)
                        val isError = manageBusinessCaseErrors(manufacturersResponse, isModelDataExists)
                        onRefresh(false, isError)
                        if (isError) {
                            return@subscribe
                        }
                        updateUi(modelData ?: ManufacturersModel(0, 0, 0, HashMap<String, String>()), reset)
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
