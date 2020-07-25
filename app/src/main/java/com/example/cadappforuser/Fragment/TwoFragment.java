package com.example.cadappforuser.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cadappforuser.Act_Session;
import com.example.cadappforuser.R;
import com.example.cadappforuser.adapter.CancelBookingAdapter;
import com.example.cadappforuser.adapter.PreviousBookingAdapter;
import com.example.cadappforuser.model.CancelBookingModel;
import com.example.cadappforuser.model.PreviousBookingMOdel;
import com.example.cadappforuser.retrofit.BaseRequest;
import com.example.cadappforuser.retrofit.RequestReciever;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TwoFragment extends Fragment {

    Act_Session act_session ;
    BaseRequest baseRequest;
    RecyclerView recyclerView;
    ArrayList<CancelBookingModel> previousBookingMOdels = new ArrayList<>();
    CancelBookingAdapter previousBookingAdapter;


    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rowView = inflater.inflate(R.layout.fragment_two, null);
        recyclerView = rowView.findViewById(R.id.frag1_recycle);
        act_session = new Act_Session(getContext());
        Apigetorder();
        return rowView;
    }


    private void Apigetorder() {
        baseRequest = new BaseRequest();
        baseRequest.setBaseRequestListner(new RequestReciever() {
            @Override
            public void onSuccess(int requestCode, String Json, Object object) {
                try {
                    JSONObject jsonObject = new JSONObject(Json);
                    JSONArray jsonArray = jsonObject.optJSONArray("data");

                    previousBookingMOdels = baseRequest.getDataList(jsonArray, CancelBookingModel.class);

                    if (previousBookingMOdels.size() != 0) {


                        previousBookingAdapter =new CancelBookingAdapter(getContext(),previousBookingMOdels);
//                                LinearLayoutManager layoutManager1=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true);
//                                recyclerView1.setLayoutManager(layoutManager1);
                        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(previousBookingAdapter);


                        // imageUserLogo.setImageURI(fileget);

                        // tv_surname.setText(profile_list1.get(0).getLastname());


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int requestCode, String errorCode, String message) {

            }


            @Override
            public void onNetworkFailure(int requestCode, String message) {

            }
        });
        String remainingUrl2 = "http://aoneservice.net.in/salon/get-apis/freelancer_cancelledbooking_api.php?" + "id=" + act_session.userId + "&&flag=" + act_session.flag;
        baseRequest.callAPIGETData(1, remainingUrl2);
    }


}
