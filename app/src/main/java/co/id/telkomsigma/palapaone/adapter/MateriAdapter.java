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
import co.id.telkomsigma.palapaone.model.MateriModel;

public class MateriAdapter extends BaseAdapter {
    private Context mContext;
    private MateriModel model;
    private List<MateriModel> listModel;
    private TextView txt_materi, txt_author;
    private Typeface font, fontbold;

    public MateriAdapter(Context mContext, List<MateriModel> listModel) {
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
            view = inflater.inflate(R.layout.item_list_materi, null);
        }
        txt_materi = view.findViewById(R.id.txt_materi);
        txt_author = view.findViewById(R.id.txt_author);

        txt_materi.setTypeface(fontbold);
        txt_author.setTypeface(fontbold);

        txt_materi.setText(model.getMateri_title());
        txt_author.setText(model.getMateri_author());

        return view;
    }
}
