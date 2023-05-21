package task.clone.shorts.netework

import com.airbnb.lottie.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import task.clone.shorts.netework.dataClass.Video

interface ShortsApi{

    @GET("videos")
    suspend fun getVideos(@Query("page") page: Int): Video

    companion object {
        private const val BASE_URL = "https://internship-service.onrender.com/"

        operator fun invoke(): ShortsApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRetrofitClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(ShortsApi::class.java)
        }

        private fun getRetrofitClient(): OkHttpClient {
            return OkHttpClient.Builder()
               .build()
        }


    }

}

