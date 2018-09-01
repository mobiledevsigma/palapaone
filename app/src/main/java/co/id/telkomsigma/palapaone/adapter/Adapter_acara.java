package co.id.telkomsigma.palapaone.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.controller.event.IcwFragment;

public class Adapter_acara extends ArrayAdapter<String> {

    private final IcwFragment context;
    private final String[] acara;
    private final String[] pembicara;
    private final String[] ruangan;
    private final String[] jam;
    Typeface font,fontbold;


    public Adapter_acara(IcwFragment context, String[] jam, String[] acara, String[] pembicara, String[]ruangan) {
        super(context.getActivity(), R.layout.item_listacara, jam);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.acara=acara;
        this.pembicara=pembicara;
        this.ruangan=ruangan;
        this.jam=jam;


        fontbold = Typeface.createFromAsset(context.getActivity().getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(context.getActivity().getAssets(), "fonts/AvenirLTStd-Book.otf");
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_listacara, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.pembicara);
        txtTitle.setText(pembicara[position]);
        txtTitle.setTypeface(fontbold);

        TextView txtTitle_tiga = (TextView) rowView.findViewById(R.id.jam);
        txtTitle_tiga.setText(jam[position]);
        txtTitle_tiga.setTypeface(fontbold);
        TextView txtTitle_empat= (TextView) rowView.findViewById(R.id.nm_acara);
        txtTitle_empat.setText(acara[position]);
        txtTitle_empat.setTypeface(fontbold);
        TextView txtTitle_lima= (TextView) rowView.findViewById(R.id.ruangan);
        txtTitle_lima.setText(ruangan[position]);
        txtTitle_lima.setTypeface(fontbold);



        return rowView;

    };
}

