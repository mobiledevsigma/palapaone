package co.id.telkomsigma.palapaone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.controller.event.IcwFragment;
import co.id.telkomsigma.palapaone.model.AgendaModel;


/**
 * Created by Biting on 2/27/2018.
 */

public class Adapter_hari extends RecyclerView.Adapter<Adapter_hari.MyViewHolder> {

    private AgendaModel model;
    private List<AgendaModel> modelList;
    private Context context;

    public Adapter_hari(Context context, List<AgendaModel> modelList) {
        this.modelList = modelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        model = modelList.get(position);

        holder.titleTextView.setText(model.getDay_x());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;

        MyViewHolder(View v) {
            super(v);
            titleTextView = v.findViewById(R.id.text1);
        }
    }
}