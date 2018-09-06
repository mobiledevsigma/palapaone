package co.id.telkomsigma.palapaone.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.model.FeedbackModel;
import co.id.telkomsigma.palapaone.model.MateriModel;

public class FeedbackAdapter extends BaseAdapter {
    private Context mContext;
    private FeedbackModel model;
    private List<FeedbackModel> listModel;
    private TextView txt_quest;
    private RatingBar ratingBar;
    private Typeface font, fontbold;

    public FeedbackAdapter(Context mContext, List<FeedbackModel> listModel) {
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
            view = inflater.inflate(R.layout.item_list_feedback, null);
        }
        txt_quest = view.findViewById(R.id.txt_fb_quest);
        ratingBar = view.findViewById(R.id.rate_fb_quest);

        txt_quest.setTypeface(fontbold);
        txt_quest.setText(model.getFeedback_question());

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Integer pos = (Integer) ratingBar.getTag();
                FeedbackModel feedbackModel = listModel.get(pos);
                int value = Math.round(rating);
                ratingBar.setRating(value);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, model.getFeedback_id() + "  " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
