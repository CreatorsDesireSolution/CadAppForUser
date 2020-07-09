package com.example.cadappforuser.retrofit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

import static com.example.cadappforuser.retrofit.Constants.LOGIN;
import static com.example.cadappforuser.retrofit.Constants.METHOD_EDIT_FEATURE;
import static com.example.cadappforuser.retrofit.Constants.METHOD_UPLOAD;
import static com.example.cadappforuser.retrofit.Constants.METHOD_UPLOAD_FEATURE;


/**
 * Created by Himanshu choudhary on 15/10/2018
 */

public interface ApiInterface {

//    @GET("Kampus/Api2.php?apicall=student_list")
//    Call<ModelResponse> getUserList(
//            @Query("&organization_id=") int organisation_id, @Query("&currentpage=") int page
//
//    );

    @GET()
    @Streaming
    Call<ResponseBody> downloadImage(@Url String fileUrl);


    @POST
    Call<JsonElement> postData(@Url String remainingURL, @Body JsonObject jsonObject, @Header("Authorization") String token);
    //Map<String, String> params

    @GET
    Call<JsonElement> postDataGET(@Url String remainingURL, @QueryMap Map<String, String> map, @Header("Authorization") String token);

    @GET
    Call<JsonElement> postDataTellSid(@Url String remainingURL, @QueryMap Map<String, String> map);

    @GET
    Call<JsonElement> getDataWithoutMap(@Url String remainingURL);

    @GET
    Call<JsonElement> getImageUrl(@Url String remainingURL);


    @DELETE
    Call<JsonElement> callAPIDELETE(@Url String remainingURL, @QueryMap Map<String, String> map, @Header("Authorization") String token);


    @POST
    Call<JsonElement> postDataCustomURL(@Url String remainingURL, @Body JsonObject jsonObject);

    @POST
    Call<JsonElement> postDataCustomURL1(@Url String remainingURL, @Body JsonObject jsonObject);

    @GET
    Call<JsonElement> postDataGET(@Url String remainingURL, @QueryMap Map<String, String> map);


//    name_,action_,mobile_no_,title_,body,story_desc_,user_id_

    @Multipart
    @POST("salon/freelancer_background_api.php")
    Call<JsonElement> postbackgroundfreelancer( @Part("id") RequestBody userid_,
                                    @Part("about_yourself") RequestBody aboutyourself_,
                                    @Part("curr_workplace") RequestBody currentworkplace_,
                                    @Part("prev_workplace") RequestBody previousworkplace_,
                                    @Part("experience") RequestBody experience_

    );

    @Multipart
    @POST("salon/register_api.php")
    Call<JsonElement> postRegister( @Part("firstname") RequestBody name_,
                                   @Part("lastname") RequestBody lastname,
                                   @Part("email") RequestBody email,
                                   @Part("dob") RequestBody dob_,
                                   @Part("mobilenumber") RequestBody mobilenumber,
                                   @Part("gender") RequestBody gender,
                                   @Part("address") RequestBody address,
                                   @Part("deviceid") RequestBody deviceid,
                                   @Part("password") RequestBody password,
                                    @Part ("profile_image")RequestBody profile_pic

    );




    @Multipart
    @POST("salon/customer_editdata_api.php")
    Call<JsonElement> postupdateprofile( @Part("firstname") RequestBody name_,
                                    @Part("lastname") RequestBody lastname,
                                    @Part("email") RequestBody email,
                                    @Part("dob") RequestBody dob_,
                                    @Part("mobilenumber") RequestBody mobilenumber,
                                    @Part("gender") RequestBody gender,
                                    @Part("address") RequestBody address


    );


    @Multipart
    @POST("salon/freelancer_editdata_api.php")
    Call<JsonElement> postupdateprofileFreelancer( @Part("firstname") RequestBody name_,
                                         @Part("lastname") RequestBody lastname,
                                         @Part("email") RequestBody email,
                                         @Part("mobilenumber") RequestBody mobilenumber,
                                         @Part("gender") RequestBody gender,
                                         @Part("address") RequestBody address


    );

    @Multipart
    @POST("salon/company_editdata_api.php")
    Call<JsonElement> postupdateprofileCompany( @Part("companyname") RequestBody company_name,
                                                   @Part("about_company") RequestBody bacvkground,
                                                   @Part("no_of_staff") RequestBody staff,
                                                   @Part("regnumber") RequestBody reginumber,
                                                   @Part("mobilenumber") RequestBody mobile_,
                                                   @Part("address") RequestBody address,
                                                   @Part("email") RequestBody email,
                                                   @Part("profile_pic") RequestBody profile_pic


    );


    @Multipart
    @POST("salon/freelancer_registration.php")
    Call<JsonElement> postregisterfreelancer( @Part("firstname") RequestBody name_,
                                    @Part("lastname") RequestBody lastname,
                                    @Part("email") RequestBody email,
                                    @Part("mobilenumber") RequestBody mobilenumber,
                                    @Part("gender") RequestBody gender,
                                    @Part("address") RequestBody address,
                                    @Part("deviceid") RequestBody deviceid,
                                    @Part("password") RequestBody password,
                                    @Part("latitude") RequestBody latitute_,
                                    @Part("longitude") RequestBody longitute,
                                    @Part("profile_pic") RequestBody profile_pic
    );



    @Multipart
    @POST("salon/freelancer_addservice_api.php")
    Call<JsonElement> postservicefreelancer( @Part("id") RequestBody userid_,
                                              @Part("service_name") RequestBody service_name_,
                                              @Part("description") RequestBody description_,
                                              @Part("set_price") RequestBody set_price_,
                                              @Part("duration") RequestBody duration_

    );



    @Multipart
    @POST("salon/company_addservice_api.php")
    Call<JsonElement> postservicecompany( @Part("id") RequestBody userid_,
                                             @Part("service_name") RequestBody service_name_,
                                             @Part("description") RequestBody description_,
                                             @Part("set_price") RequestBody set_price_,
                                             @Part("duration") RequestBody duration_,
                                             @Part("service_gender") RequestBody gender_,
                                             @Part("service_image") RequestBody imgae_

    );

    @Multipart
    @POST("salon/get-apis/company_servicedata_api.php")
    Call<JsonElement> postserviceee( @Part("id") RequestBody userid_


    );



    @Multipart
    @POST("salon/company_registration.php")
    Call<JsonElement> postregistercompany( @Part("companyname") RequestBody companyname_,
                                           @Part("about_company") RequestBody aboutcompany_,
                                           @Part("address") RequestBody address_,
                                    @Part("mobilenumber") RequestBody mobilenumber_,
                                    @Part("email") RequestBody email_,
                                    @Part("password") RequestBody password_,
                                           @Part("regnumber") RequestBody regnumber_,
                                           @Part("deviceid") RequestBody deviceid_,
                                           @Part("no_of_staff") RequestBody staff_,
                                           @Part("profile_pic") RequestBody profile_pic,
                                           @Part("latitude") RequestBody latitude_,
                                           @Part("longitude") RequestBody longitude_
    );



    @Multipart
    @POST("salon/company_staffregister_api.php")
    Call<JsonElement> postsatff( @Part("id") RequestBody userid_,
                                           @Part("firstname") RequestBody firstname_,
                                           @Part("lastname") RequestBody lastname_,
                                           @Part("email") RequestBody email_,
                                           @Part("mobilenumber") RequestBody mobilenumber_,
                                           @Part("gender") RequestBody gender_,
                                           @Part("address") RequestBody address_

    );

    @Multipart
    @POST("signup/")
    Call<JsonElement> formData(@Part("email_id_to") RequestBody email,
                               @Part("app_name") RequestBody app_name,
                               @Part("device_id") RequestBody deviceid,
                               @Part("fcm_token") RequestBody fcm_token,
                               @Part("ssecrete") RequestBody ssecrete);


    @Multipart
    @POST("getuseranser/")
//@Part("items[]") List<String> items)
    Call<JsonElement> getUserAnswer(@Part("email_id") RequestBody email_id,
                                    @Part("ques_id") RequestBody ques_id,
                                    @Part("reply") RequestBody reply,
                                    @Part("app_name") RequestBody app_name);

    @Multipart
    @POST("SOH/PHP/Api2.php?apicall=book_item")
    Call<JsonElement> pOSTbOOK(@Part("mob_no") RequestBody mobile_,
                               @Part("action") RequestBody action_,
                               @Part("item_id") RequestBody item_id_,
                               @Part("user_id") RequestBody user_id_,
                               @Part("description") RequestBody desc_,
                               @Part("full_name") RequestBody name_);




    @Multipart
    @POST("salon/freelancer_upload_api.php")
    Call<JsonElement> postUploadImage(@Part("certificate") RequestBody  encodeimage_,
                                      @Part("id") RequestBody user_id_);

    @Multipart
    @POST(METHOD_UPLOAD_FEATURE)
    Call<JsonElement> postUploadFeature(@Part MultipartBody.Part file,
                                        @Part("user_id") RequestBody user_id_);

    @Multipart
    @POST(METHOD_EDIT_FEATURE)
    Call<JsonElement> postEditFeature(@Part MultipartBody.Part file,
                                      @Part("user_id") RequestBody user_id_,
                                      @Part("image_id") RequestBody image_id_);


    @Multipart
    @POST("salon/login_api.php")
    Call<JsonElement> postLogin(
            @Part("email") RequestBody email_,
            @Part("password") RequestBody password_);


    @Multipart
    @POST("salon/company_upload_api.php")
    Call<JsonElement> postcertificatecompany(
            @Part("id") RequestBody userid_,
            @Part("certificate") RequestBody certificate_,
            @Part("pic_work_performed") RequestBody pic_work_perform
            );



    @Multipart
    @POST("salon/company_background_api.php")
    Call<JsonElement> postbackgroundcompany(
            @Part("id") RequestBody userid,
            @Part("about_your_company") RequestBody password_,
            @Part("total_year_establishment") RequestBody totalyear_,
            @Part("team_size") RequestBody team_,
            @Part("no_of_men") RequestBody male_,
            @Part("no_of_women") RequestBody female_
            );




/*

    @Multipart
    @POST("addDotEvidence")
    Call<MultipartAddEvidence> addEvidence(@Header("Authorization") String token, @Part("file\"; filename=\"pp.png\" ") RequestBody file, @Part("dotId") RequestBody dotId, @Part("description") RequestBody description);
*/

}

