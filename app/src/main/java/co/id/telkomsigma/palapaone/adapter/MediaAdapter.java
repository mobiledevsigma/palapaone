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
import co.id.telkomsigma.palapaone.model.MateriModel;
import co.id.telkomsigma.palapaone.model.MediaModel;

public class MediaAdapter extends BaseAdapter {
    private Context mContext;
    private MediaModel model;
    private List<MediaModel> listModel;
    private TextView txt_title, txt_date;
    private Typeface font, fontbold;
    private ImageView imageView;

    public MediaAdapter(Context mContext, List<MediaModel> listModel) {
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
            view = inflater.inflate(R.layout.item_list_media, null);
        }
        imageView = view.findViewById(R.id.iv_media);
        txt_date = view.findViewById(R.id.txt_media_date);
        txt_title = view.findViewById(R.id.txt_media_title);

        txt_date.setTypeface(fontbold);
        txt_title.setTypeface(fontbold);

        downloadImage(mContext, model.getMedia_image(), imageView);
        txt_date.setText(model.getMedia_date());
        txt_title.setText(model.getMedia_title());

        return view;
    }

    private void downloadImage(Context context, String url, ImageView image) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.icon_logo_app)
                .into(image);
    }
}
