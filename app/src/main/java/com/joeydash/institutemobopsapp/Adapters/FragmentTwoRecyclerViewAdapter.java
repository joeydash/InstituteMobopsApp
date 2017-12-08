package com.joeydash.institutemobopsapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joeydash.institutemobopsapp.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by joeydash on 7/12/17.
 */

public class FragmentTwoRecyclerViewAdapter extends RecyclerView.Adapter<FragmentTwoRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private ArrayList<String> data;

    public FragmentTwoRecyclerViewAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_style_layout,parent,false );
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_card.setText(data.get(position));
        Random rand = new Random();
        int r = rand.nextInt(200);
        int g = rand.nextInt(200);
        int b = rand.nextInt(200);
        int randomColor = Color.rgb(r,g,b);
        holder.rl_card.setBackgroundColor(randomColor);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv_rv;
        TextView tv_card;
        RelativeLayout rl_card;


        public ViewHolder(View itemView) {
            super(itemView);
            cv_rv =itemView.findViewById(R.id.cv_rv);
            tv_card = itemView.findViewById(R.id.tv_card);
            rl_card = itemView.findViewById(R.id.rl_card);
        }
    }
}
