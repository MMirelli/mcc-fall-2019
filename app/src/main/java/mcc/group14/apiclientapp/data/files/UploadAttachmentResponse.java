package mcc.group14.apiclientapp.data.files;

import com.google.gson.annotations.SerializedName;

public class UploadAttachmentResponse {
    @SerializedName("success")
    public String success;

    @SerializedName("url")
    public String url;

}
