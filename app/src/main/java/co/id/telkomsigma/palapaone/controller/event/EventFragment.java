package co.id.telkomsigma.palapaone.controller.event;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import co.id.telkomsigma.palapaone.adapter.RundownAdapter;

import co.id.telkomsigma.palapaone.adapter.AgendaAdapter;
import co.id.telkomsigma.palapaone.controller.feedback.FeedbackActivity;
import co.id.telkomsigma.palapaone.model.AgendaModel;
import co.id.telkomsigma.palapaone.model.RundownModel;
import co.id.telkomsigma.palapaone.util.GPSHelper;
import co.id.telkomsigma.palapaone.util.OnItemClickListener;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    Typeface font, fontbold;
    private List<String> dayList;
    private AgendaModel model;
    private List<AgendaModel> modelList;
    private RecyclerView lv_time;
    private ListView lv_rundown;
    private AgendaAdapter adapterHari;
    private String idAgenda;
    private RundownModel rundownModel;
    private List<RundownModel> rundownModelList;
    private RundownAdapter adapterAcara;
    private GPSHelper gpsHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        gpsHelper = new GPSHelper(getActivity());

        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        fontbold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AvenirLTStd-Book.otf");

        lv_time = view.findViewById(R.id.lv_time);
        lv_rundown = view.findViewById(R.id.lv_rundown);

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

        Button btn_map = view.findViewById(R.id.button3);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDirection();
            }
        });

        return view;
    }

    private void getDirection() {
        if (gpsHelper.canGetLocation()) {
            double latitude = gpsHelper.getLatitude();
            double longitude = gpsHelper.getLongitude();
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=" + latitude + "," + longitude + "&daddr=" + "-7.790592" + "," + "110.366662"));
            startActivity(intent);
        }
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
                            adapterHari = new AgendaAdapter(getActivity(), dayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(String id) {
                                    idAgenda = id;
                                    getRundown(idAgenda);
                                }
                            });
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
                            rundownModelList = new ArrayList<RundownModel>();
                            dayList = new ArrayList<String>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.RUNDOWN.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.RUNDOWN.TAG_ID);
                                String name = object.getString(ConstantUtils.RUNDOWN.TAG_NAME);
                                String desc = object.getString(ConstantUtils.RUNDOWN.TAG_DESC);
                                String start = object.getString(ConstantUtils.RUNDOWN.TAG_START);
                                String end = object.getString(ConstantUtils.RUNDOWN.TAG_END);
                                String place = object.getString(ConstantUtils.RUNDOWN.TAG_PLACE);
                                String layout = object.getString(ConstantUtils.RUNDOWN.TAG_LAYOUT);
                                rundownModel = new RundownModel(id, name, start, end, place, layout);
                                rundownModelList.add(rundownModel);
                            }

                            adapterAcara = new RundownAdapter(getActivity().getApplicationContext(), rundownModelList);
                            lv_rundown.setAdapter(adapterAcara);
                            lv_rundown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                }
                            });

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
