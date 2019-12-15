package mcc.group14.apiclientapp.api

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable
import mcc.group14.apiclientapp.data.users.UserCredentials
import mcc.group14.apiclientapp.data.users.UserSearch
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UsersApiClient{
    companion object {

        fun create(): UsersApiClient {

            val mAuth = FirebaseAuth.getInstance()

            val REAL_API = "https://mcc-fall-2019-g14.appspot.com/mcc/"
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(REAL_API)
                .build()

            return retrofit.create(UsersApiClient::class.java)

        }
    }

    @Headers("Content-Type: application/json;charset=utf-8")
    @PUT("user/")
    fun editPassword(@Body userCredentials: UserCredentials):
            Observable<ResponseWrapper<UserCredentials>>

    // get users from regex
    @GET("project/user/{search_path}")
    fun searchForUsers(@Path("search_path") search_path: String):
            Observable<ResponseWrapper<MutableList<UserSearch>>>
}
