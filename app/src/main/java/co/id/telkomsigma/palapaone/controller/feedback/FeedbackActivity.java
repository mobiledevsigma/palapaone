package co.id.telkomsigma.palapaone.controller.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class FeedbackActivity extends AppCompatActivity {

    private TextView textView;
    private ListView listView;
    private Button button;
    private FeedbackModel model;
    private List<FeedbackModel> modelList;
    private FeedbackAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        textView = findViewById(R.id.txt_feedback);
        listView = findViewById(R.id.lv_feedback);
        button = findViewById(R.id.btn_submit_fb);

        getData("2");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Thankyou for your feedback", Toast.LENGTH_SHORT).show();
                onBackPressed();
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
                //NavUtils.navigateUpFromSameTask(this);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getData(String id) {
        AndroidNetworking.get(ConstantUtils.URL.FEEDBACK + "{type_id}")
                .addPathParameter("type_id", id)
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
                                String id = object.getString(ConstantUtils.FEEDBACK.TAG_ID);
                                String type = object.getString(ConstantUtils.FEEDBACK.TAG_TYPE);
                                String quest = object.getString(ConstantUtils.FEEDBACK.TAG_QUESTION);
                                String event = object.getString(ConstantUtils.FEEDBACK.TAG_EVENTID);
                                String dates = object.getString(ConstantUtils.FEEDBACK.TAG_DATE);
                                model = new FeedbackModel(id, type, quest, event, dates);
                                modelList.add(model);
                            }
                            adapter = new FeedbackAdapter(getApplicationContext(), modelList);
                            listView.setAdapter(adapter);
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
