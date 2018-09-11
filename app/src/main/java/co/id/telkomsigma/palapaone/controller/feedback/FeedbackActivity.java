package co.id.telkomsigma.palapaone.controller.feedback;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.adapter.FeedbackAdapter;
import co.id.telkomsigma.palapaone.model.FeedbackModel;
import co.id.telkomsigma.palapaone.util.DataSession;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class FeedbackActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textView;
    private ListView listView;
    private Button button;
    private FeedbackModel model;
    private List<FeedbackModel> modelList;
    private FeedbackAdapter adapter;
    private String typeFeedback, idUser;
    private SessionManager sessionManager;
    private DataSession dataSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        sessionManager = new SessionManager(getApplicationContext());
        dataSession = new DataSession(getApplicationContext());

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.txt_feedback);
        listView = findViewById(R.id.lv_feedback);
        button = findViewById(R.id.btn_submit_fb);

        idUser = sessionManager.getId();
        Intent intent = getIntent();
        typeFeedback = intent.getStringExtra(ConstantUtils.FEEDBACK.TAG_TYPE);
        String eventID = sessionManager.getEventID();
        getData(eventID);

        progressBar.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modelList.size() > 0) {
                    int check = 0;
                    int listSize = 0;
                    for (int a = 0; a < modelList.size(); a++) {
                        String data = dataSession.getData("feedbcak" + a + typeFeedback);
                        if (!data.equals("")) {
                            check++;
                        }
                    }

                    if (check == listSize) {
                        new AlertDialog.Builder(FeedbackActivity.this)
                                .setTitle("Perhatian !!")
                                .setMessage("Apakah anda yakin akan mengirim data?")
                                .setCancelable(false)
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        sendData();
                                    }
                                })
                                .setNegativeButton("Tidak", null)
                                .show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Harap lengkapi semua data..", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Harap lengkapi semua data..", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Feedback");
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

    private void getData(String id) {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.get(ConstantUtils.URL.FEEDBACK + "{event_id}")
                .addPathParameter("event_id", id)
                .setTag("Feedback")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            modelList = new ArrayList<FeedbackModel>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.FEEDBACK.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String type = object.getString(ConstantUtils.FEEDBACK.TAG_TYPE);
                                if (type.equals(typeFeedback)) {
                                    String id = object.getString(ConstantUtils.FEEDBACK.TAG_ID);
                                    String quest = object.getString(ConstantUtils.FEEDBACK.TAG_QUESTION);
                                    String event = object.getString(ConstantUtils.FEEDBACK.TAG_EVENTID);
                                    String dates = object.getString(ConstantUtils.FEEDBACK.TAG_DATE);
                                    model = new FeedbackModel(id, type, quest, event, dates);
                                    modelList.add(model);
                                }
                            }
                            adapter = new FeedbackAdapter(getApplicationContext(), modelList, dataSession, typeFeedback);
                            listView.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void sendData() {
        progressBar.setVisibility(View.VISIBLE);
        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonTitle = new JSONObject();

            for (int a = 0; a < modelList.size(); a++) {
                FeedbackModel feedbackModel = modelList.get(a);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(ConstantUtils.SUBMIT_FB.TAG_SCORE, dataSession.getData("feedback" + a + typeFeedback));
                jsonObject.put(ConstantUtils.SUBMIT_FB.TAG_TEXT, "-");
                jsonObject.put(ConstantUtils.SUBMIT_FB.TAG_BY, idUser);
                jsonObject.put(ConstantUtils.SUBMIT_FB.TAG_ID, feedbackModel.getFeedback_id());

                jsonArray.put(jsonObject);
            }
            jsonTitle.put(ConstantUtils.FEEDBACK.TAG_TITLE, jsonArray);
            System.out.println("feeedback " + jsonTitle);

            AndroidNetworking.post(ConstantUtils.URL.SEND_FEEDBACK)
                    .addJSONObjectBody(jsonTitle) // posting json
                    .setTag("SubmitFB")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getString("status").equals("1")) {
                                    progressBar.setVisibility(View.GONE);
                                    dataSession.destroySession();
                                    onBackPressed();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}