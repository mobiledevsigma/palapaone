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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.adapter.AgendaAdapter;
import co.id.telkomsigma.palapaone.adapter.RundownAdapter;
import co.id.telkomsigma.palapaone.controller.feedback.FeedbackActivity;
import co.id.telkomsigma.palapaone.model.AgendaModel;
import co.id.telkomsigma.palapaone.model.RundownModel;
import co.id.telkomsigma.palapaone.util.DataSession;
import co.id.telkomsigma.palapaone.util.GPSHelper;
import co.id.telkomsigma.palapaone.util.OnItemClickListener;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    Typeface font, fontbold;
    private ProgressBar progressBar;
    private List<String> dayList;
    private List<String> dateList;
    private AgendaModel model;
    private List<AgendaModel> modelList;
    private RecyclerView lv_time;
    private ListView lv_rundown;
    private AgendaAdapter adapterHari;
    private String idAgenda = "", idEvent;
    private RundownModel rundownModel;
    private List<RundownModel> rundownModelList;
    private RundownAdapter adapterAcara;
    private GPSHelper gpsHelper;
    private DataSession dataSess;
    private SessionManager session;
    private TextView tanggal;
    private LinearLayout lay_btn;
    private int kode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        gpsHelper = new GPSHelper(getActivity());
        dataSess = new DataSession(getActivity(), "event" + idAgenda);
        session = new SessionManager(getActivity());

        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        fontbold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AvenirLTStd-Book.otf");

        progressBar = view.findViewById(R.id.progressBar);
        lv_time = view.findViewById(R.id.lv_time);
        lv_rundown = view.findViewById(R.id.lv_rundown);

        progressBar.setVisibility(View.GONE);

        tanggal = view.findViewById(R.id.tanggal);
        lay_btn = view.findViewById(R.id.lay_button);
        Button btn_fb = view.findViewById(R.id.button2);
        Button btn_map = view.findViewById(R.id.button3);

        tanggal.setTypeface(font);

        String myValue = this.getArguments().getString("name");
        String myValue2 = this.getArguments().getString("id");

        idEvent = myValue2;
        getAgenda(myValue2);

        if (session.isLogin()) {
            lay_btn.setVisibility(View.VISIBLE);
        } else {
            lay_btn.setVisibility(View.GONE);
        }

        btn_fb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(getActivity(), FeedbackActivity.class);
                i.putExtra(ConstantUtils.FEEDBACK.TAG_TYPE, "0");
                startActivity(i);
            }
        });

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

    public void getAgenda(final String id) {
        progressBar.setVisibility(View.VISIBLE);
        System.out.println("agenda " + id);
        AndroidNetworking.get(ConstantUtils.URL.AGENDA + "{sub_event_id}")
                .addPathParameter("sub_event_id", id)
                .setTag("Agenda")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
                            Date local = new Date();

                            modelList = new ArrayList<AgendaModel>();
                            dayList = new ArrayList<String>();
                            dateList = new ArrayList<String>();
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
                                dateList.add(date);

                                //convert date server
                                SimpleDateFormat fServer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                try {
                                    Date dateServer = fServer.parse(date);
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("id"));
                                    String theDay = sdf.format(dateServer);
                                    String today = formatter.format(local);

                                    if (idAgenda.isEmpty()) {
                                        getRundown(id);
                                        if (theDay.equals(today)) {
                                            tanggal.setText(theDay);
//                                            kode = Integer.parseInt(id);
//                                            adapterHari = new AgendaAdapter(getActivity(), dayList, 4);
                                            //getRundown(id);
                                        }
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }

                            adapterHari = new AgendaAdapter(getActivity(), dayList, new OnItemClickListener() {
                                @Override
                                public void onItemClick(String id) {
                                    String date = modelList.get(Integer.parseInt(id) - 1).getAgenda_date();
                                    SimpleDateFormat fServer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    try {
                                        Date dateServer = fServer.parse(date);
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("id"));
                                        String theDay = sdf.format(dateServer);
                                        tanggal.setText(theDay);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    idAgenda = modelList.get(Integer.parseInt(id) - 1).getAgenda_id();;
                                    getRundown(idAgenda);
                                }
                            });

                            LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getContext());
                            MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                            lv_time.setHasFixedSize(true);
                            lv_time.setAdapter(adapterHari);
                            lv_time.setLayoutManager(MyLayoutManager);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    public void getRundown(String id) {
        progressBar.setVisibility(View.VISIBLE);
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

                            adapterAcara = new RundownAdapter(getActivity().getApplicationContext(), rundownModelList, dataSess, idEvent, idAgenda);
                            lv_rundown.setAdapter(adapterAcara);

                            lv_rundown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    if (session.isLogin()) {
//                                        Intent intent = new Intent(getActivity().getApplicationContext(), DetailEventActivity.class);
//                                        intent.putExtra(ConstantUtils.RUNDOWN.TAG_ID, rundownModelList.get(position).getRundown_id());
//                                        intent.putExtra(ConstantUtils.RUNDOWN.TAG_NAME, rundownModelList.get(position).getRundown_name());
//                                        intent.putExtra(ConstantUtils.RUNDOWN.TAG_START, rundownModelList.get(position).getRundown_start());
//                                        intent.putExtra(ConstantUtils.RUNDOWN.TAG_END, rundownModelList.get(position).getRundown_end());
//                                        intent.putExtra(ConstantUtils.RUNDOWN.TAG_PLACE, rundownModelList.get(position).getRundown_place());
//                                        intent.putExtra(ConstantUtils.RUNDOWN.TAG_LAYOUT, rundownModelList.get(position).getRundown_layout());
//                                        startActivity(intent);
//                                    } else {
//                                        Toast.makeText(getActivity(), "You have to login first", Toast.LENGTH_SHORT).show();
//                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}