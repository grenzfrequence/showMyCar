package com.grenzfrequence.showmycar.webserviceTests.offline

import com.grenzfrequence.showmycar.BuildConfig
import com.grenzfrequence.showmycar.base.BaseUnitTest
import com.grenzfrequence.showmycar.car_types.data.model.BuiltDatesModel
import com.grenzfrequence.showmycar.car_types.data.model.MainTypesModel
import com.grenzfrequence.showmycar.car_types.data.model.ManufacturersModel
import com.grenzfrequence.showmycar.lib_extensions.isEqual
import com.squareup.moshi.Moshi
import org.assertj.core.api.Assertions.assertThat
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


/**
 * Created by grenzfrequence on 10.08.17.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(23))
class ModelTests : BaseUnitTest() {

    val moshi: Moshi by lazy { fragmentComponent.moshi() }

    @Test
    fun manufacturersTest() {
        val testData: JSONObject = JSONObject("{\n" +
                "  \"page\" : 0,\n" +
                "  \"pageSize\" : 10,\n" +
                "  \"totalPageCount\" : 8,\n" +
                "  \"wkda\" : {\n" +
                "    \"020\" : \"Abarth\",\n" +
                "    \"040\" : \"Alfa Romeo\",\n" +
                "    \"042\" : \"Alpina\",\n" +
                "    \"057\" : \"Aston Martin\",\n" +
                "    \"060\" : \"Audi\",\n" +
                "    \"130\" : \"BMW\",\n" +
                "    \"095\" : \"Barkas\",\n" +
                "    \"107\" : \"Bentley\",\n" +
                "    \"145\" : \"Brilliance\",\n" +
                "    \"141\" : \"Buick\"\n" +
                "  }\n" +
                "}")
        val adapter = moshi.adapter<ManufacturersModel>(ManufacturersModel::class.java)
        val manufacturersModel: ManufacturersModel? = adapter.fromJson(testData.toString())

        manufacturersModel?.let {
            assertThat(manufacturersModel.page).isEqualTo(testData.get("page"))
            assertThat(manufacturersModel.pageSize).isEqualTo(testData.get("pageSize"))
            assertThat(manufacturersModel.totalPageCount).isEqualTo(testData.get("totalPageCount"))
            assertThat(manufacturersModel.wkda.isEqual(testData.get("wkda") as JSONObject)).isTrue()
        }
    }

    @Test
    fun mainTypesTest() {
        val testData: JSONObject = JSONObject("{\n" +
                "    \"page\": 0,\n" +
                "    \"pageSize\": 10,\n" +
                "    \"totalPageCount\": 1,\n" +
                "    \"wkda\": {\n" +
                "        \"Arnage\": \"Arnage\",\n" +
                "        \"Azure\": \"Azure\",\n" +
                "        \"Continental Flying Spur\": \"Continental Flying Spur\",\n" +
                "        \"Continental GT\": \"Continental GT\",\n" +
                "        \"Continental GTC\": \"Continental GTC\",\n" +
                "        \"Continental SC\": \"Continental SC\"\n" +
                "    }\n" +
                "}")
        val adapter = moshi.adapter<MainTypesModel>(MainTypesModel::class.java)
        val mainTypesModel: MainTypesModel? = adapter.fromJson(testData.toString())

        mainTypesModel?.let {
            assertThat(mainTypesModel.page).isEqualTo(testData.get("page"))
            assertThat(mainTypesModel.pageSize).isEqualTo(testData.get("pageSize"))
            assertThat(mainTypesModel.totalPageCount).isEqualTo(testData.get("totalPageCount"))
            assertThat(mainTypesModel.wkda.isEqual(testData.get("wkda") as JSONObject)).isTrue()
        }
    }

    @Test
    fun buildDatesTest() {
        val testData: JSONObject = JSONObject("{\n" +
                "    \"wkda\": {\n" +
                "        \"2001\": \"2001\",\n" +
                "        \"2002\": \"2002\",\n" +
                "        \"2003\": \"2003\",\n" +
                "        \"2004\": \"2004\",\n" +
                "        \"2005\": \"2005\",\n" +
                "        \"2006\": \"2006\",\n" +
                "        \"2007\": \"2007\",\n" +
                "        \"2008\": \"2008\",\n" +
                "        \"2009\": \"2009\",\n" +
                "        \"2010\": \"2010\"\n" +
                "    }\n" +
                "}")
        val adapter = moshi.adapter<BuiltDatesModel>(BuiltDatesModel::class.java)
        val builtdates: BuiltDatesModel? = adapter.fromJson(testData.toString())

        assertThat(builtdates?.wkda?.isEqual(testData.get("wkda") as JSONObject)).isTrue()
    }

}
