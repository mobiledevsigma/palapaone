package co.id.telkomsigma.palapaone.controller.help;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
import co.id.telkomsigma.palapaone.adapter.ContactGeneralAdapter;
import co.id.telkomsigma.palapaone.adapter.ContactUrgentAdapter;
import co.id.telkomsigma.palapaone.adapter.MediaAdapter;
import co.id.telkomsigma.palapaone.controller.media.MediaCenterDetailActivity;
import co.id.telkomsigma.palapaone.model.ContactModel;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class ContactActivity extends AppCompatActivity {

    private ListView listView_general;
    private ListView listView_urgent;
    private ContactGeneralAdapter generalAdapter;
    private ContactUrgentAdapter urgentAdapter;
    private ContactModel model;
    private List<ContactModel> modelListGeneral;
    private List<ContactModel> modelListUrgent;
    private SessionManager session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        session = new SessionManager(getApplicationContext());

        listView_general = findViewById(R.id.lv_general);
        listView_urgent = findViewById(R.id.lv_urgent);

        getData(session.getEventID());
    }

    private void getData(String event_id) {
        AndroidNetworking.get(ConstantUtils.URL.CALL_CENTER + "{event_id}")
                .addPathParameter("event_id", event_id)
                .setTag("Call Center")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("tot " + response);
                            modelListGeneral = new ArrayList<ContactModel>();
                            modelListUrgent = new ArrayList<ContactModel>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.CALL_CENTER.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String type = object.getString(ConstantUtils.CALL_CENTER.TAG_TYPE);
                                if (type.equals("0")) {
                                    String id = object.getString(ConstantUtils.CALL_CENTER.TAG_ID);
                                    String number = object.getString(ConstantUtils.CALL_CENTER.TAG_NUMBER);
                                    String name = object.getString(ConstantUtils.CALL_CENTER.TAG_NAME);
                                    String event = object.getString(ConstantUtils.CALL_CENTER.TAG_EVENTID);
                                    String image = object.getString(ConstantUtils.CALL_CENTER.TAG_IMAGE);
                                    String layout = object.getString(ConstantUtils.CALL_CENTER.TAG_LAYOUT);
                                    String longi = object.getString(ConstantUtils.CALL_CENTER.TAG_LONGITUDE);
                                    String latit = object.getString(ConstantUtils.CALL_CENTER.TAG_LATITUDE);
                                    model = new ContactModel(id, number, name, event, type, image, layout, longi, latit);
                                    modelListGeneral.add(model);
                                } else {
                                    String id = object.getString(ConstantUtils.CALL_CENTER.TAG_ID);
                                    String number = object.getString(ConstantUtils.CALL_CENTER.TAG_NUMBER);
                                    String name = object.getString(ConstantUtils.CALL_CENTER.TAG_NAME);
                                    String event = object.getString(ConstantUtils.CALL_CENTER.TAG_EVENTID);
                                    String image = object.getString(ConstantUtils.CALL_CENTER.TAG_IMAGE);
                                    String layout = object.getString(ConstantUtils.CALL_CENTER.TAG_LAYOUT);
                                    String longi = object.getString(ConstantUtils.CALL_CENTER.TAG_LONGITUDE);
                                    String latit = object.getString(ConstantUtils.CALL_CENTER.TAG_LATITUDE);
                                    model = new ContactModel(id, number, name, event, type, image, layout, longi, latit);
                                    modelListUrgent.add(model);
                                }
                            }
                            generalAdapter = new ContactGeneralAdapter(getApplicationContext(), modelListGeneral);
                            listView_general.setAdapter(generalAdapter);

                            urgentAdapter = new ContactUrgentAdapter(getApplicationContext(), modelListUrgent);
                            listView_urgent.setAdapter(urgentAdapter);
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
