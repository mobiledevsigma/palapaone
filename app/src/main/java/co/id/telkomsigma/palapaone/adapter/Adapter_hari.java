package co.id.telkomsigma.palapaone.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.model.AgendaModel;
import co.id.telkomsigma.palapaone.util.OnItemClickListener;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;


/**
 * Created by Biting on 2/27/2018.
 */

public class Adapter_hari extends RecyclerView.Adapter<Adapter_hari.MyViewHolder> {

    private AgendaModel model;
    private List<AgendaModel> modelList;
    private List<String> dayList;
    private Context context;
    private String idAgenda;
    private OnItemClickListener onItemClickListener;
    MyViewHolder holder;

    public Adapter_hari(Context context, List<String> dayList, OnItemClickListener onItemClickListener) {
        this.dayList = dayList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
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

        holder.titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idAgenda = dayList.get(position).toString();
                onItemClickListener.onItemClick(idAgenda);
                for (int a = 0; a < dayList.size(); a++) {
                    System.out.println("pos "+position +" idx "+a);
                    if (a != position) {
                        System.out.println("masuk if");
                        holder.frameLayout.setBackground(context.getResources().getDrawable(R.drawable.bg_unselected_day));
                        holder.titleTextView.setTextColor(Color.BLACK);
                    } else {
                        System.out.println("masuk else");
                        holder.frameLayout.setBackground(context.getResources().getDrawable(R.drawable.bg_selected_day));
                        holder.titleTextView.setTextColor(Color.WHITE);
                    }
                }
            }
        });
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