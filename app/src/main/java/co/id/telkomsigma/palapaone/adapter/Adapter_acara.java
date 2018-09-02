package co.id.telkomsigma.palapaone.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
        TextView txtTitle_lima = (TextView) view.findViewById(R.id.txt_room);
        txtTitle_lima.setText(model.getRundown_place());
        txtTitle_lima.setTypeface(fontbold);

        return view;

    }

    ;
}

