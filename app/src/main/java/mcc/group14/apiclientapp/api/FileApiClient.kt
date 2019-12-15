package mcc.group14.apiclientapp.api

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface FileApiClient {

        companion object {

            fun create(): FileApiClient {

                val REAL_API = "https://mcc-fall-2019-g14.appspot.com/mcc/"

                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(AuthInterceptor())

                val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .baseUrl(REAL_API)
                    .build()

                return retrofit.create(FileApiClient::class.java)

            }
        }

    @Multipart
    @PUT("project/")
    fun uploadBadge (@Part("bodyParams") user_email: RequestBody?,
                       @Part image: MultipartBody.Part?): Call<ResponseBody>

    @Multipart
    @PUT("user/")
    fun uploadUserPictureAndParams (@Part("bodyParams") json: RequestBody?,
                           @Part image: MultipartBody.Part?): Call<ResponseBody>

}