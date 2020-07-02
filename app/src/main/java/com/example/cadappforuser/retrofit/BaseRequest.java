package com.example.cadappforuser.retrofit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.cadappforuser.R;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by himanshu on 15/10/2018.
 */
public class BaseRequest<T> extends BaseRequestParser {
    public int TYPE_NOT_CONNECTED = 0;
    public int TYPE_WIFI = 1;
    public int TYPE_MOBILE = 2;
    String token = "";
  //  SessionParam sessionParam;
    String network_error_message = "Check internet connection";
    private Context mContext;
    private ApiInterface apiInterface;
    private RequestReciever requestReciever;
    private boolean runInBackground = false;
    private Dialog dialog;
    //    ProgressDialog progressDialog;
    private View loaderView = null;
    private int APINumber_ = 1;

    public Callback<JsonElement> responseCallback = new Callback<JsonElement>() {
        @Override
        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
            String responseServer = "";
            hideLoader();
            if (null != response.body()) {
                JsonElement jsonElement = response.body();
                if (null != jsonElement) {
                    responseServer = jsonElement.toString();
                }

            } else if (response.errorBody() != null) {
                responseServer = readStreamFully(response.errorBody().contentLength(),
                        response.errorBody().byteStream());
            }

            logFullResponse(responseServer, "OUTPUT");

            if (parseJson(responseServer)) {
                if (null != requestReciever) {
                    if (null != getDataArray()) {
                        requestReciever.onSuccess(APINumber_, responseServer, getDataArray());
                    } else if (null != getDataObject()) {
                        requestReciever.onSuccess(APINumber_, responseServer, getDataObject());
                    } else {
                        requestReciever.onSuccess(APINumber_, responseServer, message);
                    }
                }
            } else {
                if (null != requestReciever) {
                    requestReciever.onFailure(1, "" + mResponseCode, message);
                }
            }
        }

        @Override
        public void onFailure(Call<JsonElement> call, Throwable t) {
//            handler.removeCallbacksAndMessages(null);
//            handler.postDelayed(r, 1000);
           /* if (t.getMessage().startsWith("Unable to resolve")) {
               r.run();
            }*/
        }
    };

    public Callback<JsonElement> responseCallbackCustom = new Callback<JsonElement>() {
        @Override
        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
            String responseServer = "";
            hideLoader();

            if (response.raw().networkResponse() != null) {
                Log.d("DATATAAA", "onResponse: response is from NETWORK...");
            } else if (response.raw().cacheResponse() != null
                    && response.raw().networkResponse() == null) {
                Log.d("DATATAAA", "onResponse: response is from CACHE...");
            }

            if (null != response.body()) {
                JsonElement jsonElement = response.body();
                if (null != jsonElement) {
                    responseServer = jsonElement.toString();
                }

            } else if (response.errorBody() != null) {
                responseServer = readStreamFully(response.errorBody().contentLength(),
                        response.errorBody().byteStream());
            }
            logFullResponse(responseServer, "OUTPUT");
            requestReciever.onSuccess(APINumber_, responseServer, null);
        }

        @Override
        public void onFailure(Call<JsonElement> call, Throwable t) {
//            handler.removeCallbacksAndMessages(null);
//            handler.postDelayed(r, 1000);
            /*if (t.getMessage().startsWith("Unable to resolve")) {
               r.run();
            }*/
        }
    };



    public Callback<JsonElement> responseCallbackCustomchat = new Callback<JsonElement>() {
        @Override
        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
            String responseServer = "";

            if (null != response.body()) {
                JsonElement jsonElement = response.body();
                if (null != jsonElement) {
                    responseServer = jsonElement.toString();
                }

            } else if (response.errorBody() != null) {
                responseServer = readStreamFully(response.errorBody().contentLength(),
                        response.errorBody().byteStream());
            }

            logFullResponse(responseServer, "OUTPUT");
            requestReciever.onSuccess(APINumber_, responseServer, null);
        }

        @Override
        public void onFailure(Call<JsonElement> call, Throwable t) {
//            handler.removeCallbacksAndMessages(null);
//            handler.postDelayed(r, 1000);
            /*if (t.getMessage().startsWith("Unable to resolve")) {
               r.run();
            }*/
        }
    };
    private boolean showErrorDialog = true;
    private RequestType requestType = null;

    public BaseRequest(Context context) {
        mContext = context;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        dialog = getProgressesDialog(context);
        //sessionParam = new SessionParam(mContext);
//        token = sessionParam.token;

        //dialog.setTitle("Fetching details...");
    }

    public BaseRequest() {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

//        dialog.setTitle("Fetching details...");
    }


    public BaseRequest(Context context, Fragment fm) {
        mContext = context;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        dialog = getProgressesDialog(context);
    }

    public boolean isRunInBackground() {
        return runInBackground;
    }

    public void setRunInBackground(boolean runInBackground) {
        this.runInBackground = runInBackground;
    }

    public void setLoaderView(View loaderView_) {
        this.loaderView = loaderView_;
    }

    public void setBaseRequestListner(RequestReciever requestListner) {
        this.requestReciever = requestListner;

    }

    public void callAPIPostCustomURL(final int APINumber, JsonObject jsonObject, String remainingURL) {
        requestType = RequestType.Post;
        APINumber_ = APINumber;
        showLoader();

//        if (jsonObject == null) {
//            jsonObject = new JsonObject();
//        }

        //  String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;

        Log.i("BaseReq",
                "Url" + " : " + remainingURL);
        logFullResponse(jsonObject.toString(), "INPUT");

        Call<JsonElement> call = apiInterface.postDataCustomURL(remainingURL, jsonObject);

        call.enqueue(responseCallback);
    }


//    Handler handler = new Handler();
//    Runnable r = new Runnable() {
//        @Override
//        public void run() {
//            hideLoader();
//            if (null != requestReciever) {
//                requestReciever.onNetworkFailure(APINumber_, network_error_message);
//            }
//            if (showErrorDialog) {
//            }
//        }
//    };


    public ArrayList<Object> getDataList(JSONArray mainArray, Class<T> t) {
        Gson gsm = null;
        ArrayList<Object> list = null;
        list = new ArrayList<>();
        if (null != mainArray) {

            for (int i = 0; i < mainArray.length(); i++) {
                gsm = new Gson();
                Object object = gsm.fromJson(mainArray.optJSONObject(i).toString(), t);
                list.add(object);
            }
        }
        return list;
    }

    public ArrayList<Object> getDataListreverse(JSONArray mainArray, Class<T> t) {
        Gson gsm = null;
        ArrayList<Object> list = null;
        list = new ArrayList<>();
        if (null != mainArray) {

            for (int i = mainArray.length() - 1; i >= 0; i--) {
                gsm = new Gson();
                Object object = gsm.fromJson(mainArray.optJSONObject(i).toString(), t);
                list.add(object);
            }
        }
        return list;
    }

//    public void callAPIPostIMAGE(final int APINumber, JsonObject jsonObject, String remainingURL, MultipartBody.Part body, RequestBody dotId, RequestBody description, RequestBody section, RequestBody sectionId) {
//
//        APINumber_ = APINumber;
//        showLoader();
//
//        if (jsonObject == null) {
//            jsonObject = new JsonObject();
//        }
//
//        String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
//        Log.i("BaseReq",
//                "Url" + " : "
//                        + baseURL);
//        logFullResponse(jsonObject.toString(), "INPUT");
//        //Call<JsonElement> call = apiInterface.uploadImage(body, "Bearer " + token);
//        Call<JsonElement> call = apiInterface.uploadImage(body, dotId, description, section, sectionId, "Bearer " + token);
//        Log.d("Token", token);
//        call.enqueue(responseCallback);
//    }




    public void callAPIUploadImage(final int APINumber, String remainingURL,RequestBody encodeimage_, RequestBody user_id_) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postUploadImage(encodeimage_, user_id_);
        call.enqueue(responseCallback);
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void callAPIRegisterascompany(final int APINumber, String remainingURL, RequestBody companyname_,
                                         RequestBody aboutcompany_, RequestBody address_, RequestBody mobilenumber_,
                                         RequestBody email_, RequestBody password_, RequestBody register_no,
                                         RequestBody deviceid_, RequestBody staff_,RequestBody profile_) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postregistercompany(companyname_,aboutcompany_,address_,mobilenumber_,
                email_,password_,register_no,deviceid_,staff_,profile_);
        call.enqueue(responseCallback);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void CallApiPOstStaff(final int APINumber, String remainingURL, RequestBody userid_,
                                         RequestBody firstname_, RequestBody lastname_, RequestBody email_,
                                         RequestBody mobilenumber_, RequestBody gender_,RequestBody address_) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postsatff(userid_,firstname_,lastname_,email_,
                mobilenumber_,gender_,address_);
        call.enqueue(responseCallback);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void callAPIRegister(final int APINumber, String remainingURL, RequestBody firstname_, RequestBody lastname_,
                                RequestBody email_, RequestBody dob, RequestBody mobilenumber_, RequestBody gender_,
                                RequestBody address_, RequestBody deviceid_, RequestBody password_,RequestBody profile_pic) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postRegister(firstname_,lastname_,email_,dob,mobilenumber_,gender_,
                address_,deviceid_,password_,profile_pic);
        call.enqueue(responseCallback);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void CallUpdateprofileCustomer(final int APINumber, String remainingURL, RequestBody firstname_, RequestBody lastname_,
                                RequestBody email_, RequestBody dob, RequestBody mobilenumber_, RequestBody gender_,
                                RequestBody address_) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postupdateprofile(firstname_,lastname_,email_,dob,mobilenumber_,gender_,
                address_);
        call.enqueue(responseCallback);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void CallUpdateprofileFreelancer(final int APINumber, String remainingURL, RequestBody firstname_, RequestBody lastname_,
                                          RequestBody email_, RequestBody mobilenumber_, RequestBody gender_,
                                          RequestBody address_) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postupdateprofileFreelancer(firstname_,lastname_,email_,mobilenumber_,gender_,
                address_);
        call.enqueue(responseCallback);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void CallUpdateprofileComapay(final int APINumber, String remainingURL, RequestBody compnyanme, RequestBody background,
                                            RequestBody staff_, RequestBody registernumber, RequestBody mobile_,
                                            RequestBody address_,RequestBody email_) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postupdateprofileCompany(compnyanme,background,staff_,registernumber,mobile_,
                address_,email_);
        call.enqueue(responseCallback);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void callApiPOstBAckgroundfreelancer(final int APINumber, String remainingURL, RequestBody userid,
                                                RequestBody aboutyourself_,  RequestBody currentworkplace_, RequestBody previousworkplace_,
                                                RequestBody experience_)
                                               {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postbackgroundfreelancer(userid,aboutyourself_,currentworkplace_,previousworkplace_,experience_);
        call.enqueue(responseCallback);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void callApiRegisterfreelancer(final int APINumber, String remainingURL, RequestBody firstname_,
                                          RequestBody lastname_, RequestBody email_, RequestBody mobilenumber_,
                                          RequestBody gender_, RequestBody address_, RequestBody deviceid_,
                                          RequestBody password_,RequestBody profile_) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postregisterfreelancer(firstname_,lastname_,email_,
                mobilenumber_,gender_,address_,deviceid_,password_,profile_);
        call.enqueue(responseCallback);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void callApiAddservicefreelancer(final int APINumber,  String remainingURL,RequestBody userid_,RequestBody service_name_, RequestBody description_, RequestBody set_price_, RequestBody duration_) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postservicefreelancer(userid_,service_name_,description_,set_price_,duration_);
        call.enqueue(responseCallback);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void callApiAddservicecompany(final int APINumber,  String remainingURL,RequestBody userid_,RequestBody
            service_name_, RequestBody description_, RequestBody set_price_, RequestBody duration_,RequestBody gender_,
                                         RequestBody image_) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postservicecompany(userid_,service_name_,description_,set_price_,duration_,gender_,image_);
        call.enqueue(responseCallback);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void callgetservice(final int APINumber,  String remainingURL,RequestBody userid_) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.postserviceee(userid_);
        call.enqueue(responseCallbackCustom);
    }



    public void callAPIPost(final int APINumber, JsonObject jsonObject, String remainingURL) {
        requestType = RequestType.Post;
        APINumber_ = APINumber;
        showLoader();
        if (jsonObject == null) {
            jsonObject = new JsonObject();
        }
        String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        Log.i("BaseReq",
                "Url" + " : "
                        + baseURL);
        logFullResponse(jsonObject.toString(), "INPUT");
        Call<JsonElement> call = apiInterface.postData(remainingURL, jsonObject, "Bearer " + token);

        Log.d("Token", token);

        call.enqueue(responseCallback);
    }

    public void callAPIPostWOLoader(final int APINumber, JsonObject jsonObject, String remainingURL) {
        requestType = RequestType.Post;
        APINumber_ = APINumber;
        //showLoader();
        if (jsonObject == null) {
            jsonObject = new JsonObject();
        }
        String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        Log.i("BaseReq",
                "Url" + " : "
                        + baseURL);
        logFullResponse(jsonObject.toString(), "INPUT");
        Call<JsonElement> call = apiInterface.postData(remainingURL, jsonObject, "Bearer " + token);

        Log.d("Token", token);

        call.enqueue(responseCallback);
    }



    //user_type_,device_id_,user_id_,organization_id_

    public void callAPIGET(final int APINumber, Map<String, String> map, String remainingURL) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        if (!baseURL.endsWith("?")) {
            baseURL = baseURL + "?";
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            baseURL = baseURL + entry.getKey() + "=" + entry.getValue() + "&";
        }
        System.out.println("BaseReq INPUT URL : " + baseURL);
        //token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImVjYmE4Y2YyZjQyYzQxZWZmMTUwZTA0NWM5YmFmZDM3MTE2ODU0MDczMzQ4NTc4Y2ZlNGU1ZmEyZjQyZWMxNzBjYzM0NWMzM2NmZjIyYzY5In0";
        Call<JsonElement> call = apiInterface.postDataGET(remainingURL, map, "Bearer " + token);
        call.enqueue(responseCallback);
        Log.d("Token", token);
    }

    //                                                                               //name_,email_,mobile_,password_



    public void callApipostCertificatecompany(final int APINumber, String remainingURL, RequestBody userid, RequestBody certificate,RequestBody pic_work_perform) {//user_type_,device_id_,email_,password_,org_id_
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        Call<JsonElement> call = apiInterface_.postcertificatecompany( userid, certificate,pic_work_perform);
        call.enqueue(responseCallback);
    }


    public void callAPILogin(final int APINumber, String remainingURL, RequestBody email_, RequestBody password_) {//user_type_,device_id_,email_,password_,org_id_
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        Call<JsonElement> call = apiInterface_.postLogin( email_, password_);
        call.enqueue(responseCallback);
    }

    public void callApiPostCompanyBackground(final int APINumber, String remainingURL, RequestBody userid, RequestBody aboutcompany_, RequestBody totalyear_, RequestBody team_size_, RequestBody male_, RequestBody female_) {//user_type_,device_id_,email_,password_,org_id_
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        Call<JsonElement> call = apiInterface_.postbackgroundcompany( userid,aboutcompany_,totalyear_,team_size_,male_,female_);
        call.enqueue(responseCallback);
    }





    public void callAPILogin2(final int APINumber, String remainingURL, RequestBody email, RequestBody app_name, RequestBody deviceid, RequestBody fcm_token, RequestBody ssecrete) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.formData(email, app_name, deviceid, fcm_token, ssecrete);
        call.enqueue(responseCallback);
    }


    //
    public void callAPIpOSTbOOK(final int APINumber, String remainingURL, RequestBody mobile_, RequestBody action_, RequestBody item_id_, RequestBody user_id_, RequestBody desc_, RequestBody name_) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.pOSTbOOK(mobile_, action_, item_id_, user_id_, desc_, name_);
        call.enqueue(responseCallback);
    }






    public void callAPIgetUserAnswer(final int APINumber, String remainingURL, RequestBody email_id, RequestBody ques_id, RequestBody reply, RequestBody app_name) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        //showLoader();
        //String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        System.out.println("BaseReq INPUT URL : " + remainingURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(remainingURL).create(ApiInterface.class);
        //Call<JsonElement> call = apiInterface_.formData(images,latitude,fcm_token,msg_detail,app_name,email_id_to,ssecrete,device_id,longitude,location_detail);
        Call<JsonElement> call = apiInterface_.getUserAnswer(email_id, ques_id, reply, app_name);
        call.enqueue(responseCallback);
    }




    public void callAPIDELETE(final int APINumber, Map<String, String> map, String remainingURL, String id) {
        APINumber_ = APINumber;
        requestType = RequestType.Post;
        showLoader();
        String baseURL = ApiClient.getClient().baseUrl().toString() + remainingURL;
        if (!baseURL.endsWith("?")) {
            baseURL = baseURL + "?";
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            baseURL = baseURL;
        }
        System.out.println("BaseReq INPUT URL : " + baseURL);
        //token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImVjYmE4Y2YyZjQyYzQxZWZmMTUwZTA0NWM5YmFmZDM3MTE2ODU0MDczMzQ4NTc4Y2ZlNGU1ZmEyZjQyZWMxNzBjYzM0NWMzM2NmZjIyYzY5In0";
        Call<JsonElement> call = apiInterface.callAPIDELETE(remainingURL + id, map, "Bearer " + token);
        call.enqueue(responseCallback);
        Log.d("Token", token);
    }

/*

    public void callAPIGETData2(final int APINumber, String baseURL_) {
        APINumber_ = APINumber;

        String baseURL = baseURL_;
        if (!baseURL.endsWith("?")) {
            baseURL = baseURL + "?";
        }

        System.out.println("BaseReq INPUT URL : " + baseURL);
        ApiInterface apiInterface_ = ApiClient.getClientChatimage().create(ApiInterface.class);
        Call<JsonElement> call = apiInterface_.getImageUrl(baseURL_);
        call.enqueue(responseCallbackCustom);
    }
*/

    public void callAPIGETData(final int APINumber, String baseURL_) {
        APINumber_ = APINumber;
        showLoader();
        String baseURL = baseURL_;
        if (!baseURL.endsWith("?")) {
            baseURL = baseURL + "?";
        }
        System.out.println("BaseReq INPUT URL : " + baseURL);
        ApiInterface apiInterface_ = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonElement> call = apiInterface_.getDataWithoutMap(baseURL_);
        call.enqueue(responseCallbackCustom);
    }

    public void callAPIGETDatawdwd(final int APINumber, String baseURL_, Class<T> t) {
        APINumber_ = APINumber;

        showLoader();
        String baseURL = baseURL_;
        if (!baseURL.endsWith("?")) {
            baseURL = baseURL + "?";
        }
        System.out.println("BaseReq INPUT URL : " + baseURL);
        ApiInterface apiInterface_ = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonElement> call = apiInterface_.getDataWithoutMap(baseURL_);
        call.enqueue(responseCallbackCustom);
    }



    public void callAPIGETChat(final int APINumber, String baseURL_) {
        APINumber_ = APINumber;
        String baseURL = baseURL_;
        if (!baseURL.endsWith("?")) {
            baseURL = baseURL + "?";
        }
        System.out.println("BaseReq INPUT URL : " + baseURL);
        ApiInterface apiInterface_ = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonElement> call = apiInterface_.getDataWithoutMap(baseURL_);
        call.enqueue(responseCallbackCustomchat);
    }

    public void callAPIGETFeedback(final int APINumber, String baseURL_) {
        APINumber_ = APINumber;
        showLoader();
        String baseURL = baseURL_;
        if (!baseURL.endsWith("/")) {
            baseURL = baseURL + "/";
        }
        System.out.println("BaseReq INPUT URL : " + baseURL);
        ApiInterface apiInterface_ = ApiClient.getClient().create(ApiInterface.class);
        Call<JsonElement> call = apiInterface_.getDataWithoutMap(baseURL);
        call.enqueue(responseCallbackCustom);
    }

    public void callAPIGETCustomURL(final int APINumber, Map<String, String> map, String baseURL_) {
        APINumber_ = APINumber;
        showLoader();
        String baseURL = baseURL_;
        if (!baseURL.endsWith("?")) {
            baseURL = baseURL + "?";
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            baseURL = baseURL + entry.getKey() + "=" + entry.getValue() + "&";
        }
        System.out.println("BaseReq INPUT URL : " + baseURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(baseURL_).create(ApiInterface.class);
        Call<JsonElement> call = apiInterface_.postDataGET("", map, "Bearer " + token);
        call.enqueue(responseCallbackCustom);
    }

    public void callAPIGETCustomURLTellSid(final int APINumber, Map<String, String> map, String baseURL_) {
        APINumber_ = APINumber;
        showLoader();
        String baseURL = baseURL_;
        if (!baseURL.endsWith("?")) {
            baseURL = baseURL + "?";
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            baseURL = baseURL + entry.getKey() + "=" + entry.getValue() + "&";
        }
        System.out.println("BaseReq INPUT URL : " + baseURL);
        ApiInterface apiInterface_ = ApiClient.getCustomClient(baseURL_).create(ApiInterface.class);
        Call<JsonElement> call = apiInterface_.postDataGET(baseURL_, map);
        call.enqueue(responseCallback);
    }
/*

    progressDialog = new ProgressDialog(ChatActivity.this, R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
            progressDialog.setCancelable(false);
*/
//   public ProgressDialog getProgressesDialog(Context ct) {
//       ProgressDialog progressDialog = new ProgressDialog(mContext, R.style.AppTheme_Dark_Dialog);
//
//       progressDialog.setIndeterminate(true);
//       progressDialog.setMessage("Please Wait...");
//       progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//       progressDialog.show();
//       progressDialog.setCancelable(false);
//       return progressDialog;
//}

    public void logFullResponse(String response, String inout) {
        final int chunkSize = 3000;

        if (null != response && response.length() > chunkSize) {
            int chunks = (int) Math.ceil((double) response.length()
                    / (double) chunkSize);


            for (int i = 1; i <= chunks; i++) {
                if (i != chunks) {
                    Log.i("BaseReq",
                            inout + " : "
                                    + response.substring((i - 1) * chunkSize, i
                                    * chunkSize));
                } else {
                    Log.i("BaseReq",
                            inout + " : "
                                    + response.substring((i - 1) * chunkSize,
                                    response.length()));
                }
            }
        } else {

            try {
                JSONObject jsonObject = new JSONObject(response);
                Log.d("BaseReq", inout + " : " + jsonObject.toString(jsonObject.length()));

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("BaseReq", " logFullResponse=>  " + response);
            }

        }
    }

    private String readStreamFully(long len, InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public Dialog getProgressesDialog(Context ct) {
        Dialog dialog = new Dialog(ct);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setTitle("Fetching details...");
        dialog.setContentView(R.layout.progress_dialog_loader);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        return dialog;
    }

    public void showErrorDialog(Context ct, String msg, final int APINumber, final JsonObject jsonObject, String remainingURL) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ct);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    public void showLoader() {
        if (mContext != null && !((Activity) mContext).isDestroyed()) {
            if (!runInBackground) {
                if (null != loaderView) {
                    loaderView.setVisibility(View.VISIBLE);
                } else if (null != dialog) {
                    dialog.show();
                }
            }
        }
    }

    public void hideLoader() {
        if (mContext != null && !((Activity) mContext).isDestroyed()) {
            if (!runInBackground) {
                if (null != loaderView) {
                    loaderView.setVisibility(View.GONE);
                } else if (null != dialog) {
                    dialog.dismiss();
                }
            }
        }
    }

    public int getConnectivityStatus(Context context) {
        if (null == context) {
            return TYPE_NOT_CONNECTED;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        if (null != activeNetwork && activeNetwork.isConnected()) {

            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return TYPE_WIFI;
            }
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return TYPE_MOBILE;
            }
        }
        return TYPE_NOT_CONNECTED;
    }

    public enum RequestType {
        Post, Get
    }


}
