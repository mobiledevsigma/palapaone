package co.id.telkomsigma.palapaone.controller.inbox;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
import co.id.telkomsigma.palapaone.adapter.InboxAdapter;
import co.id.telkomsigma.palapaone.model.InboxModel;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class InboxActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Typeface font, fontbold;
    private ListView listmenu;
    private SessionManager sessionManager;
    private InboxModel inboxModel;
    private List<InboxModel> modelList;
    private InboxAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        fontbold = Typeface.createFromAsset(InboxActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(InboxActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        TextView daftarkios = findViewById(R.id.inbox);
        daftarkios.setTypeface(fontbold);
        listmenu = findViewById(R.id.list_inbox);

        sessionManager = new SessionManager(getApplicationContext());
        getInbox(sessionManager.getEventID());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Inbox");
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

    private void getInbox(String id) {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.get(ConstantUtils.URL.NOTIFICATION + "{event_id}")
                .addPathParameter("event_id", id)
                .setTag("Inbox")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            modelList = new ArrayList<InboxModel>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.NOTIF.TAG_HEAD);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.NOTIF.TAG_ID);
                                String title = object.getString(ConstantUtils.NOTIF.TAG_TITLE);
                                String text = object.getString(ConstantUtils.NOTIF.TAG_TEXT);
                                String type = object.getString(ConstantUtils.NOTIF.TAG_TYPE);
                                String att = object.getString(ConstantUtils.NOTIF.TAG_ATTACH);
                                String cat = object.getString(ConstantUtils.NOTIF.TAG_CATEGORY);
                                String eventid = object.getString(ConstantUtils.NOTIF.TAG_EVENTID);
                                String dates = object.getString(ConstantUtils.NOTIF.TAG_DATE);
                                inboxModel = new InboxModel(id, title, text, type, att, cat, eventid, dates);
                                modelList.add(inboxModel);
                            }
                            adapter = new InboxAdapter(getApplicationContext(), modelList);
                            listmenu.setAdapter(adapter);
                            listmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(InboxActivity.this, DetailInboxActivity.class);
                                    intent.putExtra(ConstantUtils.NOTIF.TAG_ID, modelList.get(position).getInboxID());
                                    intent.putExtra(ConstantUtils.NOTIF.TAG_TITLE, modelList.get(position).getInboxTitle());
                                    intent.putExtra(ConstantUtils.NOTIF.TAG_TEXT, modelList.get(position).getInboxText());
                                    intent.putExtra(ConstantUtils.NOTIF.TAG_DATE, modelList.get(position).getCreateDate());
                                    startActivity(intent);
                                }
                            });
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}
