package com.grenzfrequence.githubviewerkotlin.webservice

import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

/**
 * Created by grenzfrequence on 21/01/17.
 */

object WebServiceConfig {

    val BASE_URL = "http://api-aws-eu-qa-1.auto1-test.com/"
    val KEY = Pair("wa_key", "coding-puzzle-client-449cc9d")
    val MAX_CACHE_SIZE = 10 * 1024 * 1024
    val LOGGING_LEVEL = BODY

    val CARTYPES_PAGE_SIZE = 15
}
