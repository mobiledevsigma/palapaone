package co.id.telkomsigma.palapaone.controller.event;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.adapter.Adapter_acara;
import co.id.telkomsigma.palapaone.adapter.Adapter_hari;
import co.id.telkomsigma.palapaone.controller.feedback.FeedbackActivity;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class IcwFragment extends Fragment {

    private List<String> listAgenda;

    String[] jam={
            "08.00-09.00",
            "09.00-10.00",
            "10.00-10.30",
    };
    String[] acara={
            "Opening",
            "Talkshow",
            "Closing",
    };
    String[] pembicara={
            "Speaker1",
            "Speaker2",
            "Speaker3",
    };
    String[] ruang={
            "Room 1",
            "Room 2",
            "Room 3",
    };
    ListView listmenu;
    Typeface font,fontbold;

    public IcwFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_icw, container, false);

        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        fontbold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AvenirLTStd-Book.otf");

        TextView daftarkios= (TextView) view.findViewById(R.id.tanggal);
        daftarkios.setTypeface(font);

//        Adapter_hari adapter = new Adapter_hari(getActivity(), judul);
//        RecyclerView list = view.findViewById(R.id.listView_time);
//        list.setHasFixedSize(true);
//        list.setAdapter(adapter);
//        list.setLayoutManager(MyLayoutManager);
//
//        listmenu=(ListView)view.findViewById(R.id.list_acara);
//
//        Adapter_acara adapter2=new Adapter_acara(this,jam,acara,pembicara,ruang);
//        listmenu.setAdapter(adapter2);
//
//        listmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Intent i = new Intent(getContext(), DetailEventMapActivity.class);
//
//                startActivity(i);
//            }
//        });

        Button goto_home= (Button) view.findViewById(R.id.button2);
        goto_home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}
