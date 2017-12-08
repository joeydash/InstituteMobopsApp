package com.joeydash.institutemobopsapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.joeydash.institutemobopsapp.Adapters.FragmentTwoRecyclerViewAdapter;
import com.joeydash.institutemobopsapp.Helper.Manager;
import com.joeydash.institutemobopsapp.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentTwo extends Fragment {

    private ArrayList<String> data;
    private String prefStingName = "data";

    RecyclerView rv_fragment_two;
    FragmentTwoRecyclerViewAdapter fragmentTwoRecyclerViewAdapter;
    ItemTouchHelper itemTouchHelper;
    Manager manager;

    public FragmentTwo() {

    }
    public static FragmentTwo newInstance(){
        FragmentTwo fragmentTwo = new FragmentTwo();
        return fragmentTwo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        manager = new Manager(getContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_fragment_two = view.findViewById(R.id.rv_fragment_two);
        itemTouchHelper= new ItemTouchHelper(createHelperCallback());
        updateMyView();
    }


    private void updateMyView() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        TypeToken<List<String>> token = new TypeToken<List<String>>() {};
        data  = gson.fromJson(manager.getPrefString(prefStingName, "[\"No Internet Connection\",]"),token.getType());
        fragmentTwoRecyclerViewAdapter = new FragmentTwoRecyclerViewAdapter(getContext(),data);
        rv_fragment_two.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_fragment_two.setItemAnimator(new DefaultItemAnimator());
        itemTouchHelper.attachToRecyclerView(rv_fragment_two);
        rv_fragment_two.setAdapter(fragmentTwoRecyclerViewAdapter);
    }
    public ItemTouchHelper.Callback createHelperCallback(){
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                moveItem(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem(viewHolder.getAdapterPosition());
            }
        };
        return simpleCallback;
    }

    public void removeItem(int position) {
        data.remove(position);
        fragmentTwoRecyclerViewAdapter.notifyItemRemoved(position);
    }
    public void moveItem(int currentPosition,int targetPosition){
        String item = data.get(currentPosition);
        data.remove(currentPosition);
        data.add(targetPosition,item);
        fragmentTwoRecyclerViewAdapter.notifyItemMoved(currentPosition,targetPosition);
    }
}
