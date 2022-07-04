package com.example.freedomfighter.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.freedomfighter.R;
import com.example.freedomfighter.adapter.CustomAdapter;
import com.example.freedomfighter.modal.BioModal;
import com.example.freedomfighter.util.ConnectionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    RecyclerView recyclerView;
    TextView bioText;
    AlertDialog.Builder dialog;

    private ArrayList<BioModal> bioModalArrayList;
    private CustomAdapter adapter;

    String url ="https://api.jsonserve.com/KBK5Yk";

    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        bioText = rootView.findViewById(R.id.textView);
        //dialog = new AlertDialog.Builder(this);
        progressBar = rootView.findViewById(R.id.idProgressBar);

        //ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);

        bioModalArrayList = new ArrayList<>();


        buildRecyclerView();

        if(new ConnectionManager().checkConnectivity(rootView.getContext()))
        {
            getData();
        }

        return rootView;
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                for (int i=0;i< response.length();i++){

                    try {

                        JSONObject responseObj = response.getJSONObject(i);
                        String bioName = responseObj.getString("name");
                        String description = responseObj.getString("description");
                        String bioBirth = responseObj.getString("birth");
                        String bioImage = responseObj.getString("image");

                        bioModalArrayList.add(new BioModal(bioName,description,bioBirth,bioImage));
                        buildRecyclerView();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void buildRecyclerView() {

        adapter = new CustomAdapter(bioModalArrayList,getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
    }

}