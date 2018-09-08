package co.id.telkomsigma.palapaone.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.model.AgendaModel;
import co.id.telkomsigma.palapaone.util.OnItemClickListener;


/**
 * Created by Biting on 2/27/2018.
 */

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.MyViewHolder> {

    MyViewHolder holder;
    private AgendaModel model;
    private List<AgendaModel> modelList;
    private List<String> dayList;
    private Context context;
    private String idAgenda;
    private OnItemClickListener onItemClickListener;
    private int row_index = -1;

    public AgendaAdapter(Context context, List<String> dayList, OnItemClickListener onItemClickListener) {
        this.dayList = dayList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public AgendaAdapter(Context context, List<String> dayList) {
        this.dayList = dayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_dayx, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder hold, final int position) {
        holder = hold;
        holder.titleTextView.setText(dayList.get(position).toString());

        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idAgenda = dayList.get(position).toString();
                onItemClickListener.onItemClick(idAgenda);
                row_index = position;
                notifyDataSetChanged();
            }
        });

        if (row_index == position) {
            holder.frameLayout.setBackgroundColor(Color.RED);
            holder.titleTextView.setTextColor(Color.WHITE);
        } else {
            holder.frameLayout.setBackgroundColor(Color.GRAY);
            holder.titleTextView.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        FrameLayout frameLayout;

        MyViewHolder(View v) {
            super(v);
            frameLayout = v.findViewById(R.id.lay_kotak);
            titleTextView = v.findViewById(R.id.textTime);
        }
    }
}