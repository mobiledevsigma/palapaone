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
import co.id.telkomsigma.palapaone.model.GalleryModel;
import co.id.telkomsigma.palapaone.model.SpeakerModel;

public class GalleryAdapter extends BaseAdapter {
    private List<GalleryModel> mDataList;
    private GalleryModel model;
    private Context mContext;
    private Typeface font, fontbold;

    public GalleryAdapter(Context context, List<GalleryModel> dataList) {
        mContext = context;
        mDataList = dataList;

        fontbold = Typeface.createFromAsset(mContext.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(mContext.getAssets(), "fonts/AvenirLTStd-Book.otf");
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 5;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        model = mDataList.get(position);

        // inflating list view layout if null
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_grid_gallery, null);
        }

        ImageView mImageView = view.findViewById(R.id.iv_grid_gallery);
        TextView txt_date = view.findViewById(R.id.txt_gallery_date);
        TextView txt_caption = view.findViewById(R.id.txt_gallery_caption);

        txt_date.setTypeface(fontbold);
        txt_caption.setTypeface(fontbold);

        if (model.getGallery_file() != null) {
            String imgURL = model.getGallery_file();
            downloadImage(mContext, imgURL, mImageView);
            mImageView.setBackground(null);
            txt_date.setText(model.getGallery_date());
            txt_caption.setText(model.getGallery_caption());
        } else {
            mImageView.setImageResource(R.drawable.avatars);
        }
        return view;
    }

    private void downloadImage(Context context, String url, ImageView image) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.avatars)
                .into(image);
    }
}
