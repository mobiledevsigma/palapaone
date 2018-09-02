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
import co.id.telkomsigma.palapaone.model.AgendaModel;
import co.id.telkomsigma.palapaone.model.ExpoModel;

public class AgendaAdapter extends BaseAdapter {

    private Context mContext;
    private AgendaModel model;
    private List<AgendaModel> listModel;
    private TextView txt_expo, txt_cate;
    private Typeface font, fontbold;

    public AgendaAdapter(Context mContext, List<AgendaModel> listModel) {
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
            view = inflater.inflate(R.layout.item_listacara, null);
        }
        txt_expo = view.findViewById(R.id.txt_expo_name);
        txt_cate = view.findViewById(R.id.txt_expo_category);

        txt_expo.setTypeface(fontbold);
        txt_cate.setTypeface(fontbold);
//
//        txt_expo.setText(model.getExpo_name());
//        txt_cate.setText(model.getExpo_loca());

        return view;
    }
}
