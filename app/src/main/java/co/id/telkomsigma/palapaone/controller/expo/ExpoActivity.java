package co.id.telkomsigma.palapaone.controller.expo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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
import co.id.telkomsigma.palapaone.adapter.ExpoAdapter;
import co.id.telkomsigma.palapaone.model.ExpoModel;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class ExpoActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView txt_expo;
    private LinearLayout lay_expo_cat;
    private TextView txt_choose, txt_expo2;
    private ListView lv_expo;
    private SpinnerDialog spinnerCategory;
    private ArrayList<String> listCatID = new ArrayList<String>();
    private ArrayList<String> listCatName = new ArrayList<String>();
    private SessionManager session;
    private ExpoModel model;
    private List<ExpoModel> modelList;
    private ExpoAdapter adapter;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expo);

        fontbold = Typeface.createFromAsset(ExpoActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(ExpoActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        progressBar = findViewById(R.id.progressBar);
        txt_expo = findViewById(R.id.txt_expo_name);
        lay_expo_cat = findViewById(R.id.lay_expo_cat);
        txt_choose = findViewById(R.id.txt_spin_expo);
        txt_expo2 = findViewById(R.id.txt_expo_2);
        lv_expo = findViewById(R.id.lv_expo);

        progressBar.setVisibility(View.GONE);
        session = new SessionManager(getApplicationContext());

        if (session.getParentID().isEmpty()) {
            getExpoCat("1");
        } else {
            getExpoCat(session.getParentID());
        }

        getAllExpo();
        spinnerCategory = new SpinnerDialog(ExpoActivity.this, listCatName, "Choose Expo");
        lay_expo_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerCategory.showSpinerDialog();
            }
        });

        spinnerCategory.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String lantai, int i) {
                txt_choose.setTypeface(fontbold);
                txt_choose.setText(lantai);
                txt_expo2.setText("Choosen Expo : " + lantai);
                String idCategory = listCatID.get(i);
                getDataExpo(idCategory, session.getParentID());
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Exhibition");
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

    private void getExpoCat(String id) {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.get(ConstantUtils.URL.CAT_EXPO + "{event_id}")
                .addPathParameter("event_id", id)
                .setTag("Expo")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.CAT_EXPO.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.CAT_EXPO.TAG_ID);
                                String name = object.getString(ConstantUtils.CAT_EXPO.TAG_NAME);
                                listCatID.add(id);
                                listCatName.add(name);
                            }
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

    private void getDataExpo(String idCat, String idEvent) {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.get(ConstantUtils.URL.EXPO + "{category_id}/{event_id}")
                .addPathParameter("category_id", idCat)
                .addPathParameter("event_id", idEvent)
                .setTag("Expo")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("tot " + response);
                            modelList = new ArrayList<ExpoModel>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.EXPO.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.EXPO.TAG_ID);
                                String name = object.getString(ConstantUtils.EXPO.TAG_NAME);
                                String desc = object.getString(ConstantUtils.EXPO.TAG_DESC);
                                String prod = object.getString(ConstantUtils.EXPO.TAG_PROD);
                                String map = object.getString(ConstantUtils.EXPO.TAG_MAP);
                                String loca = object.getString(ConstantUtils.EXPO.TAG_LOC);
                                model = new ExpoModel(id, name, desc, prod, map, loca);
                                modelList.add(model);
                            }
                            adapter = new ExpoAdapter(getApplicationContext(), modelList);
                            lv_expo.setAdapter(adapter);
                            lv_expo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getApplicationContext(), ExpoDetailActivity.class);
                                    intent.putExtra(ConstantUtils.EXPO.TAG_NAME, modelList.get(position).getExpo_name());
                                    intent.putExtra(ConstantUtils.EXPO.TAG_DESC, modelList.get(position).getExpo_desc());
                                    intent.putExtra(ConstantUtils.EXPO.TAG_PROD, modelList.get(position).getExpo_prod());
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

                    }
                });
    }

    private void getAllExpo() {
        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.get(ConstantUtils.URL.ALL_EXPO)
                .setTag("Expo")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("expo all " + response);
                        try {
                            modelList = new ArrayList<ExpoModel>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.EXPO.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.EXPO.TAG_ID);
                                String name = object.getString(ConstantUtils.EXPO.TAG_NAME);
                                String desc = object.getString(ConstantUtils.EXPO.TAG_DESC);
                                String prod = object.getString(ConstantUtils.EXPO.TAG_PROD);
                                String map = object.getString(ConstantUtils.EXPO.TAG_MAP);
                                String loca = object.getString(ConstantUtils.EXPO.TAG_LOC);
                                model = new ExpoModel(id, name, desc, prod, map, loca);
                                modelList.add(model);
                            }
                            adapter = new ExpoAdapter(getApplicationContext(), modelList);
                            lv_expo.setAdapter(adapter);
                            lv_expo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getApplicationContext(), ExpoDetailActivity.class);
                                    intent.putExtra(ConstantUtils.EXPO.TAG_NAME, modelList.get(position).getExpo_name());
                                    intent.putExtra(ConstantUtils.EXPO.TAG_DESC, modelList.get(position).getExpo_desc());
                                    intent.putExtra(ConstantUtils.EXPO.TAG_PROD, modelList.get(position).getExpo_prod());
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