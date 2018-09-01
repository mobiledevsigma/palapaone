package co.id.telkomsigma.palapaone.controller.gallery;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
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
import co.id.telkomsigma.palapaone.adapter.GalleryAdapter;
import co.id.telkomsigma.palapaone.model.GalleryModel;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class GalleryActivity extends AppCompatActivity {

    private TextView textView;
    private GridView gridView;
    private GalleryModel model;
    private List<GalleryModel> modelList;
    private GalleryAdapter adapter;
    private SessionManager session;
    private Button button;
    private Typeface font, fontbold;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        fontbold = Typeface.createFromAsset(GalleryActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(GalleryActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        textView = findViewById(R.id.txt_gallery);
        gridView = findViewById(R.id.gv_gallery);
        button = findViewById(R.id.btn_photo);
        session = new SessionManager(getApplicationContext());

        button.setTypeface(fontbold);

        getData(session.getEventID());
    }

    private void getData(final String event_id) {
        AndroidNetworking.get(ConstantUtils.URL.GALLERY + "{event_id}")
                .addPathParameter("event_id", event_id)
                .setTag("Gallery")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("tot " + response);
                            modelList = new ArrayList<GalleryModel>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.GALLERY.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.GALLERY.TAG_ID);
                                String url = object.getString(ConstantUtils.GALLERY.TAG_URL);
                                String testi = object.getString(ConstantUtils.GALLERY.TAG_TESTI);
                                String capt = object.getString(ConstantUtils.GALLERY.TAG_CAPTION);
                                String status = object.getString(ConstantUtils.GALLERY.TAG_STATUS);
                                String by = object.getString(ConstantUtils.GALLERY.TAG_BY);
                                String date = object.getString(ConstantUtils.GALLERY.TAG_DATE);
                                String eventid = object.getString(ConstantUtils.GALLERY.TAG_EVENTID);
                                model = new GalleryModel(id, url, testi, capt, status, by, date, eventid);
                                modelList.add(model);
                            }
                            adapter = new GalleryAdapter(getApplicationContext(), modelList);
                            gridView.setAdapter(adapter);
                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getApplicationContext(), GalleryDetailActivity.class);
                                    intent.putExtra(ConstantUtils.GALLERY.TAG_ID, modelList.get(position).getGallery_id());
                                    intent.putExtra(ConstantUtils.GALLERY.TAG_URL, modelList.get(position).getGallery_file());
                                    intent.putExtra(ConstantUtils.GALLERY.TAG_TESTI, modelList.get(position).getGallery_testimoni());
                                    intent.putExtra(ConstantUtils.GALLERY.TAG_CAPTION, modelList.get(position).getGallery_caption());
                                    intent.putExtra(ConstantUtils.GALLERY.TAG_STATUS, modelList.get(position).getGallery_status());
                                    intent.putExtra(ConstantUtils.GALLERY.TAG_BY, modelList.get(position).getGallery_by());
                                    intent.putExtra(ConstantUtils.GALLERY.TAG_DATE, modelList.get(position).getGallery_date());
                                    intent.putExtra(ConstantUtils.GALLERY.TAG_EVENTID, modelList.get(position).getEvent_id());
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