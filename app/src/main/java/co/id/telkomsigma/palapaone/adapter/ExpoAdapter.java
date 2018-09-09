package co.id.telkomsigma.palapaone.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.model.ExpoModel;
import co.id.telkomsigma.palapaone.model.MateriModel;

public class ExpoAdapter extends BaseAdapter {
    private Context mContext;
    private ExpoModel model;
    private List<ExpoModel> listModel;
    private TextView txt_expo, txt_cate;
    private Typeface font, fontbold;
    private ImageView imageView;

    public ExpoAdapter(Context mContext, List<ExpoModel> listModel) {
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
            view = inflater.inflate(R.layout.item_list_expo, null);
        }
        imageView = view.findViewById(R.id.iv_expo);
        txt_expo = view.findViewById(R.id.txt_expo_name);
        txt_cate = view.findViewById(R.id.txt_expo_category);

        txt_expo.setTypeface(fontbold);
        txt_cate.setTypeface(fontbold);

        txt_expo.setText(model.getExpo_name());
        txt_cate.setText(model.getExpo_loca());
        downloadImage(mContext, model.getExpo_prod(), imageView);

        return view;
    }

    private void downloadImage(Context context, String url, ImageView image) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.icon_expo)
                .into(image);
    }
}
