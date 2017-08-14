package com.grenzfrequence.githubviewerkotlin.utils


import okhttp3.HttpUrl
import java.util.concurrent.atomic.AtomicReference

/**
 * Created by grenzfrequence on 24/02/17.
 */

class UrlReference(url: String) {

    private val urlReference: AtomicReference<HttpUrl>

    init {
        this.urlReference = AtomicReference(HttpUrl.parse(url))
    }

    fun setUrl(url: HttpUrl) {
        urlReference.set(url)
    }

    fun url(): HttpUrl {
        return urlReference.get()
    }
}
