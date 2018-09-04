package co.id.telkomsigma.palapaone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.model.PartnerModel;

public class PartnerAdapter extends BaseAdapter {
    private List<PartnerModel> mDataList;
    private PartnerModel model;
    private Context mContext;

    public PartnerAdapter(Context context, List<PartnerModel> dataList) {
        mContext = context;
        mDataList = dataList;
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
            view = inflater.inflate(R.layout.item_grid_partner, null);
        }

        ImageView mImageView = view.findViewById(R.id.iv_grid_partner);

        mImageView.setVisibility(View.VISIBLE);

        if (model.getPartner_logo() != null) {
            String imgURL = model.getPartner_logo();
            System.out.println("partner " + imgURL);
            downloadImage(mContext, imgURL, mImageView);
            mImageView.setBackground(null);
        } else {
            mImageView.setImageResource(R.drawable.icon_avatars);
        }

        return view;
    }

    private void downloadImage(Context context, String url, ImageView image) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.icon_avatars)
                .into(image);
    }
}
