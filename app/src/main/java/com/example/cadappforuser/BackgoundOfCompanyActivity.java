package com.example.cadappforuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cadappforuser.adapter.FreelancerAddServiceAdapter;
import com.example.cadappforuser.model.CompanyGetbackGround;
import com.example.cadappforuser.model.FreelancerServiceListModel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.example.cadappforuser.retrofit.Constants.BASE_URL;

public class BackgoundOfCompanyActivity extends AppCompatActivity {

    Spinner sp_male,sp_female,sp_team_size;
    String team,male,female;
    EditText et_aboutcmpny,et_establishyear;
    Button btn_next;
    BaseRequest baseRequest;
    Act_Session act_session;
    Context context;
    String aboutcompany,totalyear;
    ArrayList<CompanyGetbackGround> companyGetbackGrounds= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgound_of_company);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Background");

        context = this;
        act_session = new Act_Session(getApplicationContext());
        sp_team_size = findViewById(R.id.sp_team_size);
        sp_male = findViewById(R.id.sp_male);
        sp_female = findViewById(R.id.sp_female);
        et_aboutcmpny = findViewById(R.id.et_aboutcmpny);
        et_establishyear = findViewById(R.id.et_establishyear);
        btn_next = findViewById(R.id.btn_next);



        ArrayAdapter<String> teamadapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.team));
        teamadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_team_size.setAdapter(teamadapter);



        final ArrayAdapter<String> male_adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.male));
        male_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_male.setAdapter(male_adapter);


        final ArrayAdapter<String> female_adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.female));
        female_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_female.setAdapter(female_adapter);




        sp_team_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                team = parentView.getSelectedItem().toString();
                if (team.equals("team")){
                    Intent intent = getIntent();
//                   Spinner_item = intent.getStringExtra("SPINNER_ITEM");
//                   sp_country.setText(Spinner_item);
                    Toast.makeText(getApplicationContext(), " Please Choose Team", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });



        sp_male.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                male = parentView.getSelectedItem().toString();
                if (male.equals("male")){
                    Intent intent = getIntent();
//                   Spinner_item = intent.getStringExtra("SPINNER_ITEM");
//                   sp_country.setText(Spinner_item);
                    Toast.makeText(getApplicationContext(), " Please select Numbers", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        sp_female.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                female = parentView.getSelectedItem().toString();
                if (female.equals("female")){
                    Intent intent = getIntent();
//                   Spinner_item = intent.getStringExtra("SPINNER_ITEM");
//                   sp_country.setText(Spinner_item);
                    Toast.makeText(getApplicationContext(), " Please Choose numbers", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        ApiGetList();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               aboutcompany = et_aboutcmpny.getText().toString();
               totalyear= et_establishyear.getText().toString();

               if (aboutcompany.equals("")){
                   Toast.makeText(context, "please enter about company", Toast.LENGTH_SHORT).show();
               }else  if (totalyear.equals("")){
                   Toast.makeText(context, "please enter established year", Toast.LENGTH_SHORT).show();
               }else  if (team.equals("")){
                   Toast.makeText(context, "please  select team size", Toast.LENGTH_SHORT).show();

               }else  if (male.equals("")){
                   Toast.makeText(context, "please select no.of male", Toast.LENGTH_SHORT).show();

               }else  if (female.equals("")){
                   Toast.makeText(context, "please select no.of female", Toast.LENGTH_SHORT).show();

               }else {
                   ApiPostcompanybackground();
               }

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    private void ApiGetList() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);

                    if (!jsonObject.getString("message").equals("Failed")) {

                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        companyGetbackGrounds = baseRequest.getDataList(jsonArray, CompanyGetbackGround.class);

                        for (int i = 0; i < companyGetbackGrounds.size(); i++) {
                            if (companyGetbackGrounds != null)

                            {

                             et_aboutcmpny.setText(companyGetbackGrounds.get(0).getAboutYourCompany());
                             et_establishyear.setText(companyGetbackGrounds.get(0).getTotalYearEstablishment());
                              male= companyGetbackGrounds.get(0).getNoOfMen();
                              female = companyGetbackGrounds.get(0).getNoOfWomenr();
                              team = companyGetbackGrounds.get(0).getTeamSize();

                            } else {
                                Toast.makeText(BackgoundOfCompanyActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                                et_aboutcmpny.setText("");
                                et_establishyear.setText("");

                            }
                        }
                    } else {
                        Toast.makeText(BackgoundOfCompanyActivity.this, "No Data", Toast.LENGTH_SHORT).show();

                    }

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

            }
        });
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/company_backgrounddata_api.php?" + "id=" + act_session.userId;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }

    public  void ApiPostcompanybackground(){
        baseRequest = new BaseRequest(context);
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                act_session.loginSession(context);

                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");

                    Intent intent = new Intent(getApplicationContext(),CertificationActivity.class);
                    startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {

                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNetworkFailure(int requestCode, String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        });
        RequestBody userid = RequestBody.create(MediaType.parse("text/plain"), act_session.userId);
        RequestBody aboutcompany_ = RequestBody.create(MediaType.parse("text/plain"), aboutcompany);
        RequestBody totalyear_ = RequestBody.create(MediaType.parse("text/plain"), totalyear);
        RequestBody team_size_ = RequestBody.create(MediaType.parse("text/plain"), team);
        RequestBody male_ = RequestBody.create(MediaType.parse("text/plain"), male);
        RequestBody female_ = RequestBody.create(MediaType.parse("text/plain"), female);

        baseRequest.callApiPostCompanyBackground(1, BASE_URL, userid, aboutcompany_,totalyear_,team_size_,male_,female_);

    }

}
