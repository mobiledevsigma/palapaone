package co.id.telkomsigma.palapaone.controller.asking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class AskingActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textView;
    private EditText editText;
    private Button button;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asking);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.txt_asking);
        editText = findViewById(R.id.et_asking);
        button = findViewById(R.id.btn_submit_asking);
        //
        Intent intent = getIntent();
        final String materiID = intent.getStringExtra(ConstantUtils.MATERI.TAG_ID);
        sessionManager = new SessionManager(getApplicationContext());
        progressBar.setVisibility(View.GONE);
        final String edit = editText.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edit.isEmpty()) {
                    sendAsking(sessionManager.getId(), edit, sessionManager.getEventID(), materiID);
                } else {
                    Toast.makeText(getApplicationContext(), "Please, fill the required field", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Asking");
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

    private void sendAsking(String userID, String question, String eventID, String materi) {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.post(ConstantUtils.URL.SEND_ASKING)
                .addBodyParameter(ConstantUtils.ASKING.TAG_BY, userID)
                .addBodyParameter(ConstantUtils.ASKING.TAG_QUESTION, question)
                .addBodyParameter(ConstantUtils.ASKING.TAG_STATUS, "1")
                .addBodyParameter(ConstantUtils.ASKING.TAG_EVENTID, eventID)
                .addBodyParameter(ConstantUtils.ASKING.TAG_MATERIID, materi)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            if (response.getString(ConstantUtils.ASKING.TAG_STAT).equals("1")) {
                                progressBar.setVisibility(View.GONE);
                            } else {
                                progressBar.setVisibility(View.GONE);
                            }
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}