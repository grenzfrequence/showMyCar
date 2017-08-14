package com.grenzfrequence.showmycar.webservice

import com.grenzfrequence.githubviewerkotlin.webservice.WebServiceConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by grenzfrequence on 11.08.17.
 */

class ParamInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {

        chain?.let {
            val request: Request = chain.request()
            val url = request.url().newBuilder()
                    .addQueryParameter(WebServiceConfig.KEY.first, WebServiceConfig.KEY.second)
                    .build()
            val newRequest = request.newBuilder()
                    .url(url)
                    .build()
            return chain.proceed(newRequest)

        } ?: throw NullPointerException()
    }
}