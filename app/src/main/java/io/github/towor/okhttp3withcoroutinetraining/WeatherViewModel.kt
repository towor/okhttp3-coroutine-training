package io.github.towor.okhttp3withcoroutinetraining

import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class WeatherViewModel: ViewModel() {
    val telop : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getTelop(v: View){
        viewModelScope.launch {
            val bt = v as Button

            val area = bt.text.toString()

            val id = bt.tag.toString()
            val urlStr = "http://weather.livedoor.com/forecast/webservice/json/v1?city=${id}"

            val result = withContext(Dispatchers.IO){
                val client = OkHttpClient()
                val request = Request.Builder().url(urlStr).build()
                val call = client.newCall(request)

                try {
                    val response = call.execute()
                    response.body?.string() //レスポンスからbodyを文字列として取得
                } catch (e: IOException){
                    null
                }

            }

            if (result != null){
                val rootJSON = JSONObject(result) //JSONオブジェクトを生成して以降でデータを取り出す
                val forecasts = rootJSON.getJSONArray("forecasts")
                val forecastsNow = forecasts.getJSONObject(0)
                val telopData = forecastsNow.getString("telop")

                telop.value = "${area}: $telopData"
            }
            else{
                telop.value = "取得エラー"
            }

        }
    }
}