package co.id.telkomsigma.palapaone.controller.event;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.controller.speaker.SpeakerDetailActivity;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class DetailEventActivity extends AppCompatActivity {
    private Typeface font, fontbold;
    private ProgressBar progressBar;
    private TextView txt_day;
    private TextView txt_date;
    private TextView txt_speaker;
    private TextView txt_place;
    private TextView txt_acara;
    private TextView txt_desc;
    private LinearLayout goto_speaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        goto_speaker = findViewById(R.id.pembicara);
        progressBar = findViewById(R.id.progressBar);
        txt_day = findViewById(R.id.acara);
        txt_date = findViewById(R.id.acara1);
        txt_speaker = findViewById(R.id.acara2);
        txt_place = findViewById(R.id.acara3);
        txt_acara = findViewById(R.id.acara4);
        txt_desc = findViewById(R.id.deskripsi);

        Intent intent = getIntent();
        String runID = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_ID);
        String runName = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_NAME);
        String runStart = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_START);
        String runEnd = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_END);
        String runPlace = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_PLACE);
        String runLayout = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_LAYOUT);
        String runDesc = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_DESC);
        String runDate = intent.getStringExtra(ConstantUtils.AGENDA.TAG_DATE);
        String runAgenda = intent.getStringExtra(ConstantUtils.AGENDA.TAG_NAME);
        getDetail(runID);

        fontbold = Typeface.createFromAsset(DetailEventActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(DetailEventActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        Button map2 = findViewById(R.id.self);
        map2.setTypeface(fontbold);
        //
        progressBar.setVisibility(View.GONE);
        txt_day.setTypeface(fontbold);
        txt_date.setTypeface(fontbold);
        txt_acara.setTypeface(fontbold);
        txt_desc.setTypeface(fontbold);
        txt_place.setTypeface(fontbold);
        txt_speaker.setTypeface(fontbold);
        //
        txt_day.setText(runAgenda);
        txt_date.setText(runDate);
        txt_acara.setText(runName);
        txt_place.setText(runPlace);
        txt_desc.setText(runDesc);

        LinearLayout goto_ruang = (LinearLayout) findViewById(R.id.ruang);
        goto_ruang.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), ShowLayoutActivity.class);
                startActivity(i);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Detail Events");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getDetail(String id) {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.get(ConstantUtils.URL.DETAIL_RUNDOWN + "{rundown_id}")
                .addPathParameter("rundown_id", id)
                .setTag("RundownDetail")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.RUNDOWN.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.DETAIL_RUNDOWN.TAG_ID);
                                String runID = object.getString(ConstantUtils.RUNDOWN.TAG_ID);
                                final String speakID = object.getString(ConstantUtils.SPEAKER.TAG_ID);
                                final String speakPhoto = object.getString(ConstantUtils.SPEAKER.TAG_PHOTO);
                                final String speakName = object.getString(ConstantUtils.SPEAKER.TAG_NAME);
                                final String nation = object.getString(ConstantUtils.SPEAKER.TAG_NATIONAL);
                                final String speakEmail = object.getString(ConstantUtils.SPEAKER.TAG_EMAIL);
                                final String speakPhone = object.getString(ConstantUtils.SPEAKER.TAG_PHONE);
                                final String speakDesc = object.getString(ConstantUtils.SPEAKER.TAG_DESC);
                                final String speakQuote = object.getString(ConstantUtils.SPEAKER.TAG_QUOTE);
                                final String speakJob = object.getString(ConstantUtils.SPEAKER.TAG_JOB);

                                txt_speaker.setText(speakName);

                                goto_speaker.setOnClickListener(new View.OnClickListener() {

                                    public void onClick(View arg0) {
                                        Intent intent = new Intent(getApplicationContext(), SpeakerDetailActivity.class);
                                        intent.putExtra(ConstantUtils.SPEAKER.TAG_ID, speakID);
                                        intent.putExtra(ConstantUtils.SPEAKER.TAG_NAME, speakName);
                                        intent.putExtra(ConstantUtils.SPEAKER.TAG_PHOTO, speakPhoto);
                                        intent.putExtra(ConstantUtils.SPEAKER.TAG_EMAIL, speakEmail);
                                        intent.putExtra(ConstantUtils.SPEAKER.TAG_PHONE, speakPhone);
                                        intent.putExtra(ConstantUtils.SPEAKER.TAG_QUOTE, speakQuote);
                                        intent.putExtra(ConstantUtils.SPEAKER.TAG_NATIONAL, nation);
                                        intent.putExtra(ConstantUtils.SPEAKER.TAG_JOB, speakJob);
                                        intent.putExtra(ConstantUtils.SPEAKER.TAG_DESC, speakDesc);
                                        startActivity(intent);
                                    }
                                });
                            }
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
