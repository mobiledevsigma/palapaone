package co.id.telkomsigma.palapaone.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.model.ContactModel;

public class ContactGeneralAdapter extends BaseAdapter {
    private Context mContext;
    private ContactModel model;
    private List<ContactModel> listModel;
    private TextView txt_title, txt_number;
    private LinearLayout layout_call;
    private Typeface font, fontbold;


    public ContactGeneralAdapter(Context mContext, List<ContactModel> listModel) {
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
        model = listModel.get(position);
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_list_contact_committee, null);
        }
        txt_title = view.findViewById(R.id.txt_contact_name);
        txt_number = view.findViewById(R.id.txt_contact_number);
        layout_call = view.findViewById(R.id.lay_call);

        txt_title.setTypeface(fontbold);
        txt_number.setTypeface(fontbold);

        if (model.getContact_type().equals("0")) {
            txt_title.setText(model.getContact_name());
            txt_number.setText(model.getContact_number());
            layout_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+model.getContact_number()));
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mContext.startActivity(callIntent);
                }
            });
        }
        return view;
    }
}
