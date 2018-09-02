package co.id.telkomsigma.palapaone.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.model.AgendaModel;
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

    public Adapter_hari(Context context, List<String> dayList, String idAgenda) {
        this.dayList = dayList;
        this.context = context;
        this.idAgenda = idAgenda;
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.titleTextView.setText(dayList.get(position).toString());

        holder.titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, dayList.get(position).toString(),Toast.LENGTH_SHORT).show();
//                getRundown(dayList.get(position).toString());
                idAgenda = dayList.get(position).toString();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;

        MyViewHolder(View v) {
            super(v);
            titleTextView = v.findViewById(R.id.textTime);
        }
    }

    public void getRundown(String id) {
        AndroidNetworking.get(ConstantUtils.URL.RUNDOWN + "{agenda_id}")
                .addPathParameter("agenda_id", id)
                .setTag("Rundwon")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            modelList = new ArrayList<AgendaModel>();
                            dayList = new ArrayList<String>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.AGENDA.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.AGENDA.TAG_ID);
                                String name = object.getString(ConstantUtils.AGENDA.TAG_NAME);
                                String event = object.getString(ConstantUtils.AGENDA.TAG_EVENT);
                                String date = object.getString(ConstantUtils.AGENDA.TAG_DATE);
                                String day = object.getString(ConstantUtils.AGENDA.TAG_DAY);
                                model = new AgendaModel(id, name, date, event, day);
                                modelList.add(model);
                                dayList.add(day);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}