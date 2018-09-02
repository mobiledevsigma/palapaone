package co.id.telkomsigma.palapaone.controller.event;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
import co.id.telkomsigma.palapaone.adapter.Adapter_hari;
import co.id.telkomsigma.palapaone.adapter.AgendaAdapter;
import co.id.telkomsigma.palapaone.controller.feedback.FeedbackActivity;
import co.id.telkomsigma.palapaone.model.AgendaModel;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    ListView listmenu;
    Typeface font, fontbold;
    private List<String> dayList;
    private Bundle bundle;
    private String data;
    private AgendaModel model;
    private List<AgendaModel> modelList;
    private AgendaAdapter adapter;
    private RecyclerView lv_time;
    private Adapter_hari adapterHari;
    private String idAgenda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        fontbold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AvenirLTStd-Book.otf");

        lv_time = view.findViewById(R.id.lv_time);

        TextView daftarkios = (TextView) view.findViewById(R.id.tanggal);
        daftarkios.setTypeface(font);

        String myValue = this.getArguments().getString("name");
        String myValue2 = this.getArguments().getString("id");

        getAgenda(myValue2);
        daftarkios.setText(myValue);

        Button goto_home = (Button) view.findViewById(R.id.button2);
        goto_home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    public void getAgenda(String id) {
        System.out.println("nila " + id);
        AndroidNetworking.get(ConstantUtils.URL.AGENDA + "{sub_event_id}")
                .addPathParameter("sub_event_id", id)
                .setTag("Agenda")
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
                            LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getContext());
                            MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            adapterHari = new Adapter_hari(getActivity(), dayList, idAgenda);
                            lv_time.setHasFixedSize(true);
                            lv_time.setAdapter(adapterHari);
                            lv_time.setLayoutManager(MyLayoutManager);

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
