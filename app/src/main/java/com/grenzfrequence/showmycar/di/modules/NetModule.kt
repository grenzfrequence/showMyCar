package com.grenzfrequence.githubviewerkotlin.di.modules

import android.app.Application
import android.support.compat.BuildConfig
import com.grenzfrequence.githubviewerkotlin.di.scopes.AppScope
import com.grenzfrequence.githubviewerkotlin.utils.UrlReference
import com.grenzfrequence.showmycar.webservice.ParamInterceptor
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by grenzfrequence on 21/01/17.
 */

@Module
class NetModule(baseUrl: String, private val maxCacheSize: Int, private val loggingLevel: Level) {

    private val baseUrlReference: UrlReference

    init {
        this.baseUrlReference = UrlReference(baseUrl)
    }

    @Provides
    @AppScope
    fun provideUrlReference(): UrlReference {
        return baseUrlReference
    }

    @Provides
    @AppScope
    fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrlReference.url())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .validateEagerly(BuildConfig.DEBUG)
                .build()
    }

    @Provides
    @AppScope
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
//                .add(CarTypesAdapter())
                .build()
    }

    @Provides
    @AppScope
    fun provideClient(cache: Cache, interceptors: MutableList<Interceptor>?): OkHttpClient {

        val httpBuilder = OkHttpClient.Builder()

        interceptors?.let {
            for (interceptor in interceptors) {
                httpBuilder.addInterceptor(interceptor)
            }
        }

        return httpBuilder
                .cache(cache)
                .build()
    }

    @Provides
    @AppScope
    fun provideInterceptors(): MutableList<Interceptor>? {
        return mutableListOf(
                ParamInterceptor(),
                HttpLoggingInterceptor().setLevel(loggingLevel))
    }

    @Provides
    @AppScope
    fun provideCache(application: Application): Cache {
        return Cache(application.cacheDir, maxCacheSize.toLong())
    }

}
