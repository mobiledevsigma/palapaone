package co.id.telkomsigma.palapaone.controller.inbox;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import co.id.telkomsigma.palapaone.R;

public class InboxActivity extends AppCompatActivity {
    Typeface font,fontbold;
    String[] date={
            "29 Agustus 2018",
            "29 Agustus 2018",
            "29 Agustus 2018",
            "19 September 2018",
            "19 September 2018",
            "19 September 2018",
            "19 September 2018",
            "19 September 2018",
            "19 September 2018",
            "19 September 2018",
    };
    String[] judul={
            "New Booth !",
            "New Booth !",
            "New Booth !",
            "New Booth !",
            "New Booth !",
            "New Booth !",
            "New Booth !",
            "New Booth !",
            "New Booth !",
             "New Booth !",
    };
    String[] detail={
            "There are mew Booth today ..",
            "There are mew Booth today ..",
            "There are mew Booth today ..",
            "There are mew Booth today ..",
            "There are mew Booth today ..",
            "There are mew Booth today ..",
            "There are mew Booth today ..",
            "There are mew Booth today ..",
            "There are mew Booth today ..",
            "There are mew Booth today ..",

    };
    ListView listmenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        fontbold = Typeface.createFromAsset(InboxActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(InboxActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        TextView daftarkios= (TextView) findViewById(R.id.inbox);
        daftarkios.setTypeface(fontbold);

        listmenu=(ListView)findViewById(R.id.list_inbox);

        Adapter_isi_berita adapter=new Adapter_isi_berita (InboxActivity.this,judul,detail,date);
        listmenu.setAdapter(adapter);

        listmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(InboxActivity.this, DetailInboxActivity.class);

                startActivity(i);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
