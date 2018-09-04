package co.id.telkomsigma.palapaone.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.model.RundownModel;

public class Adapter_acara extends BaseAdapter {

    private Context mContext;
    private RundownModel model;
    private List<RundownModel> listModel;
    private TextView txt_expo, txt_cate;
    private Typeface font, fontbold;

    public Adapter_acara(Context mContext, List<RundownModel> listModel) {
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


    public View getView(int position, View view, ViewGroup parent) {
        model = listModel.get(position);
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_listacara, null);
        }

        TextView txtTitle_tiga = (TextView) view.findViewById(R.id.txt_jam);
        txtTitle_tiga.setText(model.getRundown_start() + " - " + model.getRundown_end());
        txtTitle_tiga.setTypeface(fontbold);
        TextView txtTitle_empat = (TextView) view.findViewById(R.id.txt_acara);
        txtTitle_empat.setText(model.getRundown_name());
        txtTitle_empat.setTypeface(fontbold);
        TextView txtTitle_enam = (TextView) view.findViewById(R.id.txt_speaker);
        txtTitle_enam.setText(model.getRundown_name());
        txtTitle_enam.setVisibility(View.GONE);
        txtTitle_enam.setTypeface(fontbold);
        TextView txtTitle_lima = (TextView) view.findViewById(R.id.txt_room);
        txtTitle_lima.setText(model.getRundown_place());
        txtTitle_lima.setTypeface(fontbold);

        LinearLayout lay_reminder = view.findViewById(R.id.lay_reminder);
        lay_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getRootView().getContext())
                        .setMessage("Are you sure you want to set reminder?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(mContext, "Alarm has been set, see you soon!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        return view;
    }
}

