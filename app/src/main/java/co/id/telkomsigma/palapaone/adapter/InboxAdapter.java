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
import co.id.telkomsigma.palapaone.model.InboxModel;


/**
 * Created by LENOVO on 29/09/2017.
 */

public class InboxAdapter extends BaseAdapter {
    Typeface font, fontbold;
    private Context context;
    private List<InboxModel> modelList;
    private InboxModel model;

    public InboxAdapter(Context context, List<InboxModel> modelList) {
        this.context = context;
        this.modelList = modelList;

        font = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        fontbold = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Medium.otf");
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        model = modelList.get(position);
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_list_inbox, null);
        }

        TextView txtTitle = view.findViewById(R.id.txt_inbox_title);
        txtTitle.setText(model.getInboxTitle());
        txtTitle.setTypeface(fontbold);

        TextView txtTitle_dua = view.findViewById(R.id.txt_inbox_detail);
        txtTitle_dua.setText(model.getInboxText());
        txtTitle_dua.setTypeface(font);

        TextView txtTitle_tiga = view.findViewById(R.id.txt_inbox_date);
        txtTitle_tiga.setText(model.getCreateDate());
        txtTitle_tiga.setTypeface(fontbold);

        return view;
    }

    ;


}
