package co.id.telkomsigma.palapaone.controller.event;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.controller.speaker.SpeakerDetailActivity;

public class DetailEventActivity extends AppCompatActivity {
    Typeface font,fontbold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        fontbold = Typeface.createFromAsset(DetailEventActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(DetailEventActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        Button map2 = (Button) findViewById(R.id.self);
        map2.setTypeface(fontbold);

        TextView a= (TextView) findViewById(R.id.acara);
        a.setTypeface(fontbold);
        TextView b= (TextView) findViewById(R.id.acara1);
        b.setTypeface(fontbold);
        TextView c= (TextView) findViewById(R.id.acara2);
        c.setTypeface(fontbold);
        TextView d= (TextView) findViewById(R.id.acara3);
        d.setTypeface(fontbold);
        TextView e= (TextView) findViewById(R.id.acara4);
        e.setTypeface(fontbold);
        TextView f= (TextView) findViewById(R.id.deskripsi);
        f.setTypeface(fontbold);

        LinearLayout goto_speaker= (LinearLayout) findViewById(R.id.pembicara);
        goto_speaker.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), SpeakerDetailActivity.class);
                startActivity(i);
            }
        });
        LinearLayout goto_ruang= (LinearLayout) findViewById(R.id.ruang);
        goto_ruang.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), ShowLayoutActivity.class);
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
