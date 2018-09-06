package co.id.telkomsigma.palapaone.controller.speaker;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
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
import co.id.telkomsigma.palapaone.adapter.SpeakerAdapter;
import co.id.telkomsigma.palapaone.model.SpeakerModel;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class SpeakerActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView txt_speaker_title;
    private GridView gv_speaker;
    private SpeakerAdapter adapter;
    private SpeakerModel model;
    private List<String> listPhoto;
    private List<SpeakerModel> listModel;
    private SessionManager session;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fontbold = Typeface.createFromAsset(SpeakerActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(SpeakerActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");
        session = new SessionManager(getApplicationContext());

        setContentView(R.layout.activity_speaker);
        progressBar = findViewById(R.id.progressBar);
        txt_speaker_title = findViewById(R.id.txt_speaker_title);
        gv_speaker = findViewById(R.id.gv_speaker);
        //
        progressBar.setVisibility(View.GONE);
        txt_speaker_title.setTypeface(fontbold);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Speakers");

        if (session.getEventID().isEmpty()) {
            allSpeaker();
        } else {
            showSpeaker(session.getEventID());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void allSpeaker() {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.get(ConstantUtils.URL.ALLSPEAKER)
                .setTag("speaker")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("hasil " + response);
                        try {
                            listModel = new ArrayList<SpeakerModel>();
                            listPhoto = new ArrayList<String>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.SPEAKER.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.SPEAKER.TAG_ID);
                                String name = object.getString(ConstantUtils.SPEAKER.TAG_NAME);
                                String photo = object.getString(ConstantUtils.SPEAKER.TAG_PHOTO);
                                String email = object.getString(ConstantUtils.SPEAKER.TAG_EMAIL);
                                String phone = object.getString(ConstantUtils.SPEAKER.TAG_PHONE);
                                String quotes = object.getString(ConstantUtils.SPEAKER.TAG_QUOTE);
                                String topic = object.getString(ConstantUtils.SPEAKER.TAG_TOPIC);
                                String national = object.getString(ConstantUtils.SPEAKER.TAG_NATIONAL);
                                String event = object.getString(ConstantUtils.SPEAKER.TAG_EVENT);
                                String job = object.getString(ConstantUtils.SPEAKER.TAG_JOB);
                                String desc = object.getString(ConstantUtils.SPEAKER.TAG_DESC);
                                String about = object.getString(ConstantUtils.SPEAKER.TAG_ABOUT);
                                model = new SpeakerModel(id, name, photo, email, phone, quotes, topic, national, event, job, desc, about);
                                listPhoto.add(photo);
                                listModel.add(model);

                                System.out.println("speaker " + name);
                            }

                            adapter = new SpeakerAdapter(getApplicationContext(), listModel);
                            gv_speaker.setAdapter(adapter);
                            gv_speaker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getApplicationContext(), SpeakerDetailActivity.class);
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_ID, listModel.get(position).getSpeaker_id());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_NAME, listModel.get(position).getSpeaker_name());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_PHOTO, listModel.get(position).getSpeaker_photo());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_EMAIL, listModel.get(position).getSpeaker_email());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_PHONE, listModel.get(position).getSpeaker_phone());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_QUOTE, listModel.get(position).getSpeaker_quote());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_TOPIC, listModel.get(position).getSpeaker_topic());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_NATIONAL, listModel.get(position).getSpeaker_nationality());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_EVENT, listModel.get(position).getSpeaker_event());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_JOB, listModel.get(position).getSpeaker_job());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_DESC, listModel.get(position).getSpeaker_desc());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_ABOUT, listModel.get(position).getSpeaker_about());
                                    startActivity(intent);
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

    private void showSpeaker(String id) {
        AndroidNetworking.get(ConstantUtils.URL.SPEAKER + "{event_id}")
                .addPathParameter("event_id", id)
                .setTag("speaker")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("hasil " + response);
                        try {
                            listModel = new ArrayList<SpeakerModel>();
                            listPhoto = new ArrayList<String>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.SPEAKER.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.SPEAKER.TAG_ID);
                                String name = object.getString(ConstantUtils.SPEAKER.TAG_NAME);
                                String photo = object.getString(ConstantUtils.SPEAKER.TAG_PHOTO);
                                String email = object.getString(ConstantUtils.SPEAKER.TAG_EMAIL);
                                String phone = object.getString(ConstantUtils.SPEAKER.TAG_PHONE);
                                String quotes = object.getString(ConstantUtils.SPEAKER.TAG_QUOTE);
                                String topic = object.getString(ConstantUtils.SPEAKER.TAG_TOPIC);
                                String national = object.getString(ConstantUtils.SPEAKER.TAG_NATIONAL);
                                String event = object.getString(ConstantUtils.SPEAKER.TAG_EVENT);
                                String job = object.getString(ConstantUtils.SPEAKER.TAG_JOB);
                                String desc = object.getString(ConstantUtils.SPEAKER.TAG_DESC);
                                String about = object.getString(ConstantUtils.SPEAKER.TAG_ABOUT);
                                model = new SpeakerModel(id, name, photo, email, phone, quotes, topic, national, event, job, desc, about);
                                listPhoto.add(photo);
                                listModel.add(model);

                                System.out.println("speaker " + name);
                            }

                            adapter = new SpeakerAdapter(getApplicationContext(), listModel);
                            gv_speaker.setAdapter(adapter);
                            gv_speaker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getApplicationContext(), SpeakerDetailActivity.class);
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_ID, listModel.get(position).getSpeaker_id());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_NAME, listModel.get(position).getSpeaker_name());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_PHOTO, listModel.get(position).getSpeaker_photo());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_EMAIL, listModel.get(position).getSpeaker_email());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_PHONE, listModel.get(position).getSpeaker_phone());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_QUOTE, listModel.get(position).getSpeaker_quote());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_TOPIC, listModel.get(position).getSpeaker_topic());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_NATIONAL, listModel.get(position).getSpeaker_nationality());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_EVENT, listModel.get(position).getSpeaker_event());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_JOB, listModel.get(position).getSpeaker_job());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_DESC, listModel.get(position).getSpeaker_desc());
                                    intent.putExtra(ConstantUtils.SPEAKER.TAG_ABOUT, listModel.get(position).getSpeaker_about());
                                    startActivity(intent);
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
