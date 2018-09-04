package co.id.telkomsigma.palapaone.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.controller.help.ContactActivity;
import co.id.telkomsigma.palapaone.model.ContactModel;
import co.id.telkomsigma.palapaone.util.GPSHelper;

public class ContactUrgentAdapter extends BaseAdapter {
    private Context mContext;
    private ContactModel model;
    private List<ContactModel> listModel;
    private TextView txt_title, txt_number;
    private Button btn_map, btn_layout;
    private GPSHelper gpsHelper;
    private final static int REQUEST_CODE = 1;
    private Typeface font, fontbold;

    public ContactUrgentAdapter(Context mContext, List<ContactModel> listModel) {
        this.mContext = mContext;
        this.listModel = listModel;

        fontbold = Typeface.createFromAsset(mContext.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(mContext.getAssets(), "fonts/AvenirLTStd-Book.otf");
    }

    @Override
    public int getCount() {
        return listModel.size();
    }

    @Override
    public Object getItem(int position) {
        return listModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        gpsHelper = new GPSHelper(mContext);
        model = listModel.get(position);
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_list_contact_desk, null);
        }
        txt_title = view.findViewById(R.id.txt_contact_name);
        txt_number = view.findViewById(R.id.txt_contact_number);
        btn_map = view.findViewById(R.id.btn_map);
        btn_layout = view.findViewById(R.id.btn_layout);

        txt_title.setTypeface(fontbold);
        txt_number.setTypeface(fontbold);
        btn_map.setTypeface(fontbold);
        btn_layout.setTypeface(fontbold);

        if (model.getContact_type().equals("1")) {
            txt_title.setText(model.getContact_name());
            txt_number.setText(model.getContact_number());
            btn_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDirection();
                }
            });

            btn_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mContext, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return view;
    }

    private void getDirection() {
        if (gpsHelper.canGetLocation()) {
            double latitude = gpsHelper.getLatitude();
            double longitude = gpsHelper.getLongitude();
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=" + latitude + "," + longitude + "&daddr=" + model.getContact_latitude() + "," + model.getContact_longitude()));
            mContext.startActivity(intent);
        }
    }

}
