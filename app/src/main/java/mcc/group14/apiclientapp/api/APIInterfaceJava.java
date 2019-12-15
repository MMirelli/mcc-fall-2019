package mcc.group14.apiclientapp.api;


import java.util.Map;

import mcc.group14.apiclientapp.data.users.MemberAutocompleteResponse;
import mcc.group14.apiclientapp.data.projects.ProjectAttachmentsResponse;
import mcc.group14.apiclientapp.data.projects.ProjectCreateResponse;
import mcc.group14.apiclientapp.data.projects.ProjectImagesResponse;
import mcc.group14.apiclientapp.data.projects.ProjectsDeleteResponse;
import mcc.group14.apiclientapp.data.projects.ProjectsResponse;
import mcc.group14.apiclientapp.data.users.RegistrationToken;
import mcc.group14.apiclientapp.data.tasks.Task;
import mcc.group14.apiclientapp.data.tasks.TaskComplete;
import mcc.group14.apiclientapp.data.tasks.TaskCreateResponse;
import mcc.group14.apiclientapp.data.tasks.TaskMembers;
import mcc.group14.apiclientapp.data.tasks.TaskResponse;
import mcc.group14.apiclientapp.data.files.UploadAttachmentResponse;
import mcc.group14.apiclientapp.data.files.UploadImageResponse;
import mcc.group14.apiclientapp.data.users.UserUpdateResponse;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface APIInterfaceJava {

    @GET("projects/{email}")
    Call<ProjectsResponse> doGetListProjects(@Path("email") String email);

    @GET("project/report/{project_id}")
    Call<ResponseBody> doGetProjectsReportPDF(@Path("project_id") String project_id);

    @DELETE("project/{project_id}")
    Call<ProjectsDeleteResponse> doDeleteProject(@Path("project_id") String project_id);


    @GET("project/attachment/{project_id}")
    Call<ProjectAttachmentsResponse> getAllAttachments(@Path("project_id") String project_id);

    @GET("project/image/{project_id}")
    Call<ProjectImagesResponse> getAllImages(@Path("project_id") String project_id);


    @GET("project/user/{search_path}")
    Call<MemberAutocompleteResponse> getMemberSuggestions(@Path("search_path") String search_path);


    @GET
    Call<ResponseBody> downloadAttachMent(@Url String fileUrl);

    @Multipart
    @PUT("user/")
    Call<UserUpdateResponse> updateUserInfo(@PartMap Map<String,RequestBody> params);

    @Multipart
    @POST("project/")
    Call<ProjectCreateResponse> createProjectWithBadge(@PartMap Map<String,RequestBody> params);

    @Multipart
    @PUT("project/")
    Call<ProjectCreateResponse> updateProjectWithBadge(@PartMap Map<String,RequestBody> params);

    @Multipart
    @POST("project/attachment/")
    Call<UploadAttachmentResponse> uploadAttachment(@PartMap Map<String,RequestBody> params);

    @Multipart
    @POST("project/image/")
    Call<UploadImageResponse> uploadImage(@PartMap Map<String,RequestBody> params);

  

    @GET("tasks/{project_id}/{email_id}")
    Call<TaskResponse> doGetListTasks(@Path("project_id") String project_id, @Path("email_id") String email_id);

    @POST("task/")
    Call<TaskCreateResponse> createTask(@Body Task task);


    @POST("task/member/")
    Call<ResponseBody> createTaskMember(@Body TaskMembers task);

    @POST("task/complete/")
    Call<ResponseBody> completeTask(@Body TaskComplete task);

    @POST("token/")
    Call<ResponseBody> sendResgistrationTokenToServer(@Body RegistrationToken token);


}