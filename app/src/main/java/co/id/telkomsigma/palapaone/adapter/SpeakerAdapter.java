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
import co.id.telkomsigma.palapaone.model.SpeakerModel;

public class SpeakerAdapter extends BaseAdapter {
    private List<SpeakerModel> mDataList;
    private SpeakerModel model;
    private Context mContext;
    private Typeface font, fontbold;

    public SpeakerAdapter(Context context, List<SpeakerModel> dataList) {
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
            view = inflater.inflate(R.layout.item_grid_speaker, null);
        }

        ImageView mImageView = view.findViewById(R.id.iv_grid_speaker);
        TextView txt_title = view.findViewById(R.id.txt_speaker_title);
        TextView txt_job = view.findViewById(R.id.txt_speaker_job);

        txt_title.setTypeface(fontbold);
        txt_job.setTypeface(fontbold);

        if (model.getSpeaker_photo() != null) {
            String imgURL = model.getSpeaker_photo();
            downloadImage(mContext, imgURL, mImageView);
            mImageView.setBackground(null);
            txt_title.setText(model.getSpeaker_name());
            txt_job.setText(model.getSpeaker_job());
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
