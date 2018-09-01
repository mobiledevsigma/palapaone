package co.id.telkomsigma.palapaone.controller.partner;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
import co.id.telkomsigma.palapaone.adapter.PartnerAdapter;
import co.id.telkomsigma.palapaone.model.PartnerModel;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class PartnersActivity extends AppCompatActivity {

    private TextView txt_partner;
    private GridView gridView;
    private PartnerModel model;
    private List<PartnerModel> modelList;
    private PartnerAdapter adapter;
    private SessionManager session;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fontbold = Typeface.createFromAsset(PartnersActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(PartnersActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        setContentView(R.layout.activity_partner);
        session = new SessionManager(getApplicationContext());
        txt_partner = findViewById(R.id.txt_partner);
        gridView = findViewById(R.id.gv_partner);
        //
        txt_partner.setTypeface(fontbold);

        getData(session.getParentID());
    }

    private void getData(String event_id) {
        AndroidNetworking.get(ConstantUtils.URL.PARTNER + "{event_id}")
                .addPathParameter("event_id", event_id)
                .setTag("Media")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("tot " + response);
                            modelList = new ArrayList<PartnerModel>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.PARTNER.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.PARTNER.TAG_ID);
                                String name = object.getString(ConstantUtils.PARTNER.TAG_NAME);
                                String logo = object.getString(ConstantUtils.PARTNER.TAG_LOGO);
                                String desc = object.getString(ConstantUtils.PARTNER.TAG_DESC);
                                String url = object.getString(ConstantUtils.PARTNER.TAG_URL);
                                String eventid = object.getString(ConstantUtils.PARTNER.TAG_EVENTID);
                                String address = object.getString(ConstantUtils.PARTNER.TAG_ADDRESS);
                                String phone = object.getString(ConstantUtils.PARTNER.TAG_PHONE);
                                model = new PartnerModel(id, name, logo, desc, url, eventid, address, phone);
                                modelList.add(model);
                            }
                            adapter = new PartnerAdapter(getApplicationContext(), modelList);
                            gridView.setAdapter(adapter);
                            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getApplicationContext(), PartnersDetailActivity.class);
                                    intent.putExtra(ConstantUtils.PARTNER.TAG_ID, modelList.get(position).getPartner_id());
                                    intent.putExtra(ConstantUtils.PARTNER.TAG_NAME, modelList.get(position).getPartner_name());
                                    intent.putExtra(ConstantUtils.PARTNER.TAG_LOGO, modelList.get(position).getPartner_logo());
                                    intent.putExtra(ConstantUtils.PARTNER.TAG_DESC, modelList.get(position).getPartner_desc());
                                    intent.putExtra(ConstantUtils.PARTNER.TAG_URL, modelList.get(position).getPartner_url());
                                    intent.putExtra(ConstantUtils.PARTNER.TAG_EVENTID, modelList.get(position).getEvent_id());
                                    intent.putExtra(ConstantUtils.PARTNER.TAG_ADDRESS, modelList.get(position).getPartner_address());
                                    intent.putExtra(ConstantUtils.PARTNER.TAG_PHONE, modelList.get(position).getPartner_phone());
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
