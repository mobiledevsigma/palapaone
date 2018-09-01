package co.id.telkomsigma.palapaone.controller.inbox;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import co.id.telkomsigma.palapaone.R;


/**
 * Created by LENOVO on 29/09/2017.
 */

public class Adapter_isi_berita extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] judul;
    private final String[] tanggal;
    private final String[] konten;
    Typeface font,fontbold;


    public Adapter_isi_berita(Activity context, String[] judul, String[] tanggal, String[] konten) {
        super(context, R.layout.item_list_inbox, judul);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.judul=judul;
        this.tanggal=tanggal;
        this.konten=konten;

        font = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        fontbold = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Medium.otf");
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_list_inbox, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.judul);
        txtTitle.setText(judul[position]);
        txtTitle.setTypeface(fontbold);

        TextView txtTitle_dua = (TextView) rowView.findViewById(R.id.date);
        txtTitle_dua.setText(tanggal[position]);
        txtTitle_dua.setTypeface(font);

        TextView txtTitle_tiga = (TextView) rowView.findViewById(R.id.detail);
        txtTitle_tiga.setText(konten[position]);
        txtTitle_tiga.setTypeface(fontbold);


        return rowView;

    };


}
