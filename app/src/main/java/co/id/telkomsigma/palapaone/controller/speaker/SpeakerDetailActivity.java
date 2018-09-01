package co.id.telkomsigma.palapaone.controller.speaker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.adapter.MateriAdapter;
import co.id.telkomsigma.palapaone.adapter.SpeakerAdapter;
import co.id.telkomsigma.palapaone.controller.asking.AskingActivity;
import co.id.telkomsigma.palapaone.controller.feedback.FeedbackActivity;
import co.id.telkomsigma.palapaone.model.MateriModel;
import co.id.telkomsigma.palapaone.model.SpeakerModel;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class SpeakerDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView txt_name, txt_national, txt_email, txt_phone, txt_about, txt_quote, txt_job;
    private ListView listView;
    private Button btn_fb, btn_ask;
    private MateriModel model;
    private List<MateriModel> listModel;
    private MateriAdapter adapter;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fontbold = Typeface.createFromAsset(SpeakerDetailActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(SpeakerDetailActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        setContentView(R.layout.activity_speaker_detail);
        imageView = findViewById(R.id.iv_speaker);
        txt_name = findViewById(R.id.txt_speaker_name);
        txt_national = findViewById(R.id.txt_speaker_national);
        txt_email = findViewById(R.id.txt_speaker_email);
        txt_phone = findViewById(R.id.txt_speaker_phone);
        txt_about = findViewById(R.id.txt_speaker_about);
        txt_quote = findViewById(R.id.txt_speaker_quote);
        txt_job = findViewById(R.id.txt_speaker_job);
        listView = findViewById(R.id.lv_speaker_materi);
        btn_fb = findViewById(R.id.btn_speaker_fb);
        btn_ask = findViewById(R.id.btn_speaker_ask);
        //
        txt_name.setTypeface(fontbold);
        txt_national.setTypeface(fontbold);
        txt_email.setTypeface(fontbold);
        txt_phone.setTypeface(fontbold);
        txt_about.setTypeface(fontbold);
        txt_quote.setTypeface(fontbold);
        txt_job.setTypeface(fontbold);
        btn_fb.setTypeface(fontbold);
        btn_ask.setTypeface(fontbold);

        getDetail();

        btn_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedbackActivity.class);
                startActivity(intent);
            }
        });

        btn_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AskingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDetail() {
        Intent intent = getIntent();
        downloadImage(getApplicationContext(), intent.getStringExtra(ConstantUtils.SPEAKER.TAG_PHOTO), imageView);
        txt_name.setText(intent.getStringExtra(ConstantUtils.SPEAKER.TAG_NAME));
        txt_national.setText(intent.getStringExtra(ConstantUtils.SPEAKER.TAG_NATIONAL));
        txt_email.setText(intent.getStringExtra(ConstantUtils.SPEAKER.TAG_EMAIL));
        txt_phone.setText(intent.getStringExtra(ConstantUtils.SPEAKER.TAG_PHONE));
        txt_about.setText(intent.getStringExtra(ConstantUtils.SPEAKER.TAG_ABOUT));
        txt_quote.setText(intent.getStringExtra(ConstantUtils.SPEAKER.TAG_QUOTE));
        txt_job.setText(intent.getStringExtra(ConstantUtils.SPEAKER.TAG_JOB));
        String id = intent.getStringExtra(ConstantUtils.SPEAKER.TAG_ID);
        intent.getStringExtra(ConstantUtils.SPEAKER.TAG_EVENT);
        intent.getStringExtra(ConstantUtils.SPEAKER.TAG_TOPIC);

        AndroidNetworking.get(ConstantUtils.URL.MATERIBYSPEAKER + "{speaker_id}")
                .addPathParameter("speaker_id", id)
                .setTag("Materi")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listModel = new ArrayList<MateriModel>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.MATERI.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.MATERI.TAG_ID);
                                String name = object.getString(ConstantUtils.MATERI.TAG_NAME);
                                String desc = object.getString(ConstantUtils.MATERI.TAG_DESC);
                                String file = object.getString(ConstantUtils.MATERI.TAG_FILE);
                                String speakID = object.getString(ConstantUtils.MATERI.TAG_SPEAK_ID);
                                String speakName = object.getString(ConstantUtils.MATERI.TAG_SPEAK_NAME);
                                model = new MateriModel(id, name, speakName, desc, file, speakID);
                                listModel.add(model);
                            }

                            adapter = new MateriAdapter(getApplicationContext(), listModel);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    private void downloadImage(Context context, String url, ImageView image) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.avatars)
                .into(image);
    }
}
