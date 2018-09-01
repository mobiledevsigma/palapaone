package co.id.telkomsigma.palapaone.controller.event;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.id.telkomsigma.palapaone.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TemnasFragment extends Fragment {

    String[] judul={
            "Booth 1",
            "Booth 2",
            "Booth 3",
    };
    String[] isi={
            "Building no 2A",
            "Building no 2B",
            "Building no 2C",
    };


    public TemnasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temnas, container, false);

//        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getBaseContext());
//        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        timeAdapter adapter = new timeAdapter(getActivity(), judul,isi);
////        timeAdapter = new TimeAdapter(this, listTime);
////        RecyclerView listView_time.setHasFixedSize(true);
//        RecyclerView list = view.findViewById(R.id.listView_time);
//        list.setAdapter(adapter);
//        list.setLayoutManager(MyLayoutManager);

        return view;
    }

}
