package mcc.group14.apiclientapp.api
import io.reactivex.Observable
import mcc.group14.apiclientapp.data.projects.ProjectDetail
import mcc.group14.apiclientapp.data.users.UserProject
import mcc.group14.apiclientapp.data.users.UserRegistration
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ProjectApiClient {
    companion object {

        @JvmStatic fun create(): ProjectApiClient {

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(AuthInterceptor())

            val REAL_API = "https://mcc-fall-2019-g14.appspot.com/mcc/"
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl(REAL_API)
                .build()

            return retrofit.create(ProjectApiClient::class.java)

        }
    }

    @POST("user/")
    fun createUser(@Body user: UserRegistration): Observable<UserRegistration>

    @GET("projects/{email}")
    fun getProjectsList(@Path("email") email: String):
            Call<ResponseWrapper<MutableList<ProjectDetail>>>
    //Observable<ResponseWrapper<MutableList<ProjectDetail>>>
            //Observable<ResponseWrapper<MutableList<UserProject>>>

    /* Get one article by it's id */
    @GET("project/{project_id}")
    fun getProjectDetail(@Path("project_id") project_id: String):
            Observable<ResponseWrapper<ProjectDetail>>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("project/")
    fun createProject(@Body project: ProjectDetail): Observable<ResponseWrapper<ProjectDetail>>

    @Headers("Content-Type: application/json;charset=utf-8")
    @PUT("project/")
    fun modifyProject(@Body project: UserProject): Observable<ResponseWrapper<ProjectDetail>>
}
