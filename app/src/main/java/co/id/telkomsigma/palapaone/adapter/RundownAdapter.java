package co.id.telkomsigma.palapaone.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.model.RundownModel;
import co.id.telkomsigma.palapaone.util.DataSession;

public class RundownAdapter extends BaseAdapter {

    private Context mContext;
    private RundownModel model;
    private List<RundownModel> listModel;
    private TextView txt_expo, txt_cate;
    private Typeface font, fontbold;
    private DataSession dataSess;
    private String idAgenda;

    public RundownAdapter(Context mContext, List<RundownModel> listModel, DataSession dataSess, String idAgenda) {
        this.mContext = mContext;
        this.listModel = listModel;
        this.dataSess = dataSess;
        this.idAgenda = idAgenda;

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


    public View getView(final int position, View view, ViewGroup parent) {
        model = listModel.get(position);
        final ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_list_rundown, null);
            holder = new ViewHolder();
            holder.txtTitle_tiga = view.findViewById(R.id.txt_jam);
            holder.txtTitle_empat = view.findViewById(R.id.txt_acara);
            holder.txtTitle_enam = view.findViewById(R.id.txt_speaker);
            holder.txtTitle_lima = view.findViewById(R.id.txt_room);
            holder.lay_reminder = view.findViewById(R.id.lay_reminder);
            holder.imageView = view.findViewById(R.id.iv_reminder);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtTitle_tiga.setText(model.getRundown_start() + " - " + model.getRundown_end());
        holder.txtTitle_tiga.setTypeface(fontbold);
        holder.txtTitle_empat.setText(model.getRundown_name());
        holder.txtTitle_empat.setTypeface(fontbold);
        holder.txtTitle_enam.setText(model.getRundown_name());
        holder.txtTitle_enam.setVisibility(View.GONE);
        holder.txtTitle_enam.setTypeface(fontbold);
        holder.txtTitle_lima.setText(model.getRundown_place());
        holder.txtTitle_lima.setTypeface(fontbold);

        String lonceng = dataSess.getData("lonceng" + (position) + idAgenda);
        if (lonceng.equals("")) {
            holder.imageView.setImageResource(R.drawable.icon_bell_off);
            dataSess.setData("lonceng" + (position) + idAgenda, "off");
        } else {
            if (lonceng.equals("off")) {
                holder.imageView.setImageResource(R.drawable.icon_bell_off);
            } else {
                holder.imageView.setImageResource(R.drawable.icon_bell_on);
            }
        }
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(System.currentTimeMillis());
//        cal.clear();
//        cal.set(2012, 2, 8, 18, 16);

        holder.lay_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getRootView().getContext())
                        .setMessage("Are you sure you want to set reminder?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(mContext, "Alarm has been set, see you soon!", Toast.LENGTH_SHORT).show();
                                holder.imageView.setImageResource(R.drawable.icon_bell_on);
                                holder.imageView.setTag(position);
                                dataSess.setData("lonceng" + (position) + idAgenda, "on");
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        return view;
    }

    class ViewHolder {
        TextView txtTitle_tiga;
        TextView txtTitle_empat;
        TextView txtTitle_enam;
        TextView txtTitle_lima;
        LinearLayout lay_reminder;
        ImageView imageView;
    }
}

