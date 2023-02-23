package com.example.codetest.network

import com.example.codetest.utils.References
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(References.BASE_API_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val timeout = 10000L
        val unit = TimeUnit.MILLISECONDS
        return OkHttpClient.Builder()
            .connectTimeout(timeout, unit)
            .readTimeout(timeout, unit)
            .writeTimeout(timeout, unit)
            .build()
    }
}