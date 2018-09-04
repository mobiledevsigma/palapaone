package co.id.telkomsigma.palapaone.controller.media;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
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
import co.id.telkomsigma.palapaone.adapter.MediaAdapter;
import co.id.telkomsigma.palapaone.model.MediaModel;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class MediaCenterActivity extends AppCompatActivity {

    private TextView txt_media;
    private ListView listView;
    private SessionManager session;
    private MediaModel model;
    private List<MediaModel> listModel;
    private MediaAdapter adapter;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fontbold = Typeface.createFromAsset(MediaCenterActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(MediaCenterActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        setContentView(R.layout.activity_media);
        session = new SessionManager(getApplicationContext());
        txt_media = findViewById(R.id.txt_media);
        listView = findViewById(R.id.lv_media);


        //
        txt_media.setTypeface(fontbold);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Media Center");

        if (session.getParentID().isEmpty()) {
            getData("1");
        } else {
            getData(session.getParentID());
        }

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

    private void getData(String event_id) {
        AndroidNetworking.get(ConstantUtils.URL.MEDIA + "{event_id}")
                .addPathParameter("event_id", event_id)
                .setTag("Media")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("tot " + response);
                            listModel = new ArrayList<MediaModel>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.MEDIA.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.MEDIA.TAG_ID);
                                String title = object.getString(ConstantUtils.MEDIA.TAG_NAME);
                                String file = object.getString(ConstantUtils.MEDIA.TAG_FILE);
                                String date = object.getString(ConstantUtils.MEDIA.TAG_DATE);
                                String author = object.getString(ConstantUtils.MEDIA.TAG_USERID);
                                model = new MediaModel(id, title, file, date, author);
                                listModel.add(model);
                            }
                            adapter = new MediaAdapter(getApplicationContext(), listModel);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getApplicationContext(), MediaCenterDetailActivity.class);
                                    intent.putExtra(ConstantUtils.MEDIA.TAG_ID, listModel.get(position).getMedia_id());
                                    intent.putExtra(ConstantUtils.MEDIA.TAG_NAME, listModel.get(position).getMedia_title());
                                    intent.putExtra(ConstantUtils.MEDIA.TAG_FILE, listModel.get(position).getMedia_file());
                                    intent.putExtra(ConstantUtils.MEDIA.TAG_DATE, listModel.get(position).getMedia_date());
                                    intent.putExtra(ConstantUtils.MEDIA.TAG_USERID, listModel.get(position).getMedia_author());
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
