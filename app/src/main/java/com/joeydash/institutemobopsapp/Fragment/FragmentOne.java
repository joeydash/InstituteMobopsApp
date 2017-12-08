package com.joeydash.institutemobopsapp.Fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.joeydash.institutemobopsapp.Activities.MainActivity;
import com.joeydash.institutemobopsapp.Adapters.FragmentOneRecyclerViewAdapter;
import com.joeydash.institutemobopsapp.Helper.Manager;
import com.joeydash.institutemobopsapp.R;

import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {


    private RequestQueue requestQueue;
    private String url = "https://students.iitm.ac.in/studentsapp/test/newEmptyPHP.php";
    private ArrayList<String> data;
    private String prefStingName = "data";

    RecyclerView rv_fragment_one;
    FragmentOneRecyclerViewAdapter fragmentOneRecyclerViewAdapter;

    Manager manager;

    public FragmentOne() {
    }

    public static FragmentOne newInstance(){
        FragmentOne fragmentOne = new FragmentOne();
        return fragmentOne;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        manager = new Manager(getContext());
        requestQueue = Volley.newRequestQueue(getContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_fragment_one = view.findViewById(R.id.rv_fragment_one);

        doStringRequest();
        updateMyView();
    }

    public void doStringRequest(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        manager.setPreString(prefStingName, response);
                        updateMyView();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(MainActivity.LOG_TAG, String.valueOf(error));
                    }
                }
        );
        requestQueue.add(stringRequest);
    }

    private void updateMyView() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        TypeToken<List<String>> token = new TypeToken<List<String>>() {};
        data  = gson.fromJson(manager.getPrefString(prefStingName, "[\"No Internet Connection\",]"),token.getType());
        fragmentOneRecyclerViewAdapter = new FragmentOneRecyclerViewAdapter(getContext(),data);
        rv_fragment_one.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_fragment_one.setItemAnimator(new DefaultItemAnimator());
        rv_fragment_one.setAdapter(fragmentOneRecyclerViewAdapter);

    }

}
