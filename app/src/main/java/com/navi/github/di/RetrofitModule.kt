package com.navi.github.di

import android.content.Context
import com.navi.github.model.api.GitHubAPI
import com.navi.github.repository.MainRepository
import com.navi.github.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val BASE_URL = "https://api.github.com/"


    @Singleton
    @Provides
    fun provideWeatherApi(client: OkHttpClient): GitHubAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(GitHubAPI::class.java)

    @Provides
    fun provideOkHttpClient(logger: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logger)
        .addNetworkInterceptor(Interceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .build()
            chain.proceed(request)
        })
        .build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }


    @Singleton
    @Provides
    fun provideRepository(githubAPI: GitHubAPI): MainRepository = MainRepository(githubAPI)

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper =
        NetworkHelper(context)

}