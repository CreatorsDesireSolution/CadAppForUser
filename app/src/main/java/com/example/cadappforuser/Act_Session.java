package com.example.cadappforuser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

public class Act_Session {

    public String signupStage = "0";
    public String userId = "", name = "", login = "";
    public  String usr_name ="",mobile_verified="",email="",usr_code = "", usr_mobile = "",otp="",usr_age ="",flag="",usr_gender="",usr_country="",device_id ="",token="";
    String PREF_NAME = "MyPref";
    Context _context;

    public static String USER_ID;


    public Act_Session(Context context, String signupStage) {
        this.signupStage = signupStage;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("signupStage", signupStage);
        prefsEditor.commit();
    }






    @SuppressLint("LongLogTag")
    public Act_Session(Context context, JSONObject jsonObject) {
        if (jsonObject != null) {
            flag = (jsonObject.optString("flag"));
            userId = (jsonObject.optString("id"));
            email=(jsonObject.optString("email"));
            otp =(jsonObject.optString("otp"));


            // session_id = jsonObject.optString("id");


            USER_ID = userId;

            userId(context,userId);
            userName(context, usr_name);
            emilid(context,email);

            userDevice(context, device_id);
            userCode(context, usr_code);
            userflag(context,flag);
            userotp(context,otp);




        }
    }


    public Act_Session(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        this.usr_name = sharedPreferences.getString("usr_name", "");
        this.usr_mobile = sharedPreferences.getString("usr_mobile", "");
        this.userId = sharedPreferences.getString("id", "");
        this.flag = sharedPreferences.getString("flag", "");
        this.login = sharedPreferences.getString("login", "");
        this.email = sharedPreferences.getString("email", "");
        this.otp = sharedPreferences.getString("otp", "");



        // this.session_id = sharedPreferences.getString("id", "");


    }

    public void userId(Context context, String userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("id", userId);
        prefsEditor.commit();
    }
    public void Reciver_id(Context context, String receiver_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("user_id", receiver_id);
        prefsEditor.commit();
    }



    public void Login_Status(Context context, String login_status) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("login_status", login_status);
        prefsEditor.commit();
    }

    public void userotp(Context context, String otp) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("otp", otp);
        prefsEditor.commit();
    }

    public void emilid(Context context, String email) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("login_status", email);
        prefsEditor.commit();
    }


    public void userlogin(Context context, String login_start) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("login_start", login_start);
        prefsEditor.commit();
    }





    public void LoginStart(Context context, String login_start) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("login_start", login_start);
        prefsEditor.commit();
    }

    public void LoginEnd(Context context, String login_end) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("login_end", login_end);
        prefsEditor.commit();
    }

    public void Creation_Date(Context context, String creation_date) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("creation_date", creation_date);
        prefsEditor.commit();
    }


    public void usertoken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("token", token);
        prefsEditor.commit();
    }


    public void profileImage(Context context, String profileImage) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("Pimage", profileImage);
        prefsEditor.commit();
    }



    public void usercountry(Context context, String unqId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("usr_country", unqId);
        prefsEditor.commit();
    }
    public void usermobileverified(Context context, String unqId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("mobile_verified", unqId);
        prefsEditor.commit();
    }
    public void userage(Context context, String age) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("usr_age", age);
        prefsEditor.commit();
    }

    public void userName(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("usr_name", name);
        prefsEditor.commit();
    }

    public void usergender(Context context, String email) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("usr_gender", email);
        prefsEditor.commit();
    }

    public void userDevice(Context context, String device_id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("device_id", device_id);
        prefsEditor.commit();
    }

    public void userMobile(Context context,String mobile) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("usr_mobile", mobile);
        prefsEditor.commit();
    }

    public void userCode(Context context, String code) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("usr_code", code);
        prefsEditor.commit();
    }

    public void userflag(Context context, String flag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("flag", flag);
        prefsEditor.commit();
    }


    public void clearPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        /*AccessToken.setCurrentAccessToken(null);
        LoginManager.getInstance().logOut();*/
        prefsEditor.clear();
        prefsEditor.commit();
    }

    public void removePreference(Context context, String login_start) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.remove(login_start);
        editor.apply();
    }


    @Override
    public String toString() {
        return "SessionParam [name=" + "]";
    }


    public void loginSession(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString("login", "yes");
        prefsEditor.commit();
    }


    public void editPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        /*AccessToken.setCurrentAccessToken(null);
        LoginManager.getInstance().logOut();*/
        prefsEditor.clear();
        prefsEditor.commit();
    }
}
