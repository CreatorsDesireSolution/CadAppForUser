package com.example.cadappforuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BackgoundOfFreelancerActivity extends AppCompatActivity {

    Button btnBackground;
    EditText et_aboutfreelancer,et_currentplacefreelancer,et_priviouswork,et_experience;
    String aboutyourself,experience,previousworkplace,currentworkplace;
    BaseRequest baseRequest;
    Act_Session act_session;
   Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgound_of_freelancer);

       context = this;
        et_aboutfreelancer = findViewById(R.id.et_aboutfreelancer);
        et_currentplacefreelancer = findViewById(R.id.et_currentplacefreelancer);
        et_priviouswork = findViewById(R.id.et_priviouswork);
        et_experience = findViewById(R.id.et_experience);



        act_session = new Act_Session(getApplicationContext());
        btnBackground=findViewById(R.id.btnBackground);
        btnBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aboutyourself= et_aboutfreelancer.getText().toString();
                currentworkplace= et_currentplacefreelancer.getText().toString();
                previousworkplace= et_priviouswork.getText().toString();
                experience= et_experience.getText().toString();

                if (aboutyourself.equals("")){
                    Toast.makeText(BackgoundOfFreelancerActivity.this, "Enter ABout Your self", Toast.LENGTH_SHORT).show();
                }else if(currentworkplace.equals("")){
                    Toast.makeText(BackgoundOfFreelancerActivity.this, "Enter Current workplace", Toast.LENGTH_SHORT).show();

                }else if(previousworkplace.equals("")){
                    Toast.makeText(BackgoundOfFreelancerActivity.this, "Enter Previous work place", Toast.LENGTH_SHORT).show();

                }else  if (experience.equals("")){
                    Toast.makeText(BackgoundOfFreelancerActivity.this, "Enter Experience", Toast.LENGTH_SHORT).show();

                }else {
                    ApiPostbackground();
                }


            }
        });
    }
    private void ApiPostbackground() {
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                //  act_session.loginSession(context);
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                   // act_session = new Act_Session(getApplicationContext(), jsonObject1);

                    Toast.makeText(getApplicationContext(), "Send Successfully", Toast.LENGTH_SHORT).show();
                    finish();




                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            }

            @Override

            public void onNetworkFailure(int requestCode, String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

            }
        });
        RequestBody userid = RequestBody.create(MediaType.parse("text/plain"),act_session.userId );
        RequestBody aboutyourselef_ = RequestBody.create(MediaType.parse("text/plain"),aboutyourself );
        RequestBody experience_ = RequestBody.create(MediaType.parse("text/plain"), experience);
        RequestBody currentworkplace_ = RequestBody.create(MediaType.parse("text/plain"), currentworkplace);
        RequestBody previousworkplace_ = RequestBody.create(MediaType.parse("text/plain"), previousworkplace);


        baseRequest.callApiPOstBAckgroundfreelancer(1,"https://aoneservice.net.in/" , userid,
                                                                 aboutyourselef_,currentworkplace_,previousworkplace_,experience_);

    }

}
