package com.emreakcadag.architecturecomponents_hilt.di

import com.emreakcadag.architecturecomponents_hilt.BuildConfig.BASE_URL
import com.emreakcadag.architecturecomponents_hilt.base.network.BaseNetworkInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Emre Akçadağ on 15.08.2020
 */
@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideGson() = Gson()
        .newBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(baseNetworkInterceptor: BaseNetworkInterceptor) = OkHttpClient.Builder()
        .addInterceptor(baseNetworkInterceptor.apply { setLevel(BaseNetworkInterceptor.Level.BODY) }) // todo: emreakcadag Düzenle
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    // Burası daha sonra düzenlenecek.
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .build()
}