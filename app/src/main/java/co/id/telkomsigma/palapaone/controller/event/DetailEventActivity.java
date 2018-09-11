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
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class DetailEventActivity extends AppCompatActivity {
    Typeface font,fontbold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        Intent intent = getIntent();
        String runID = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_ID);
        String runName = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_NAME);
        String runStart = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_START);
        String runEnd = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_END);
        String runPlace = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_PLACE);
        String runLayout = intent.getStringExtra(ConstantUtils.RUNDOWN.TAG_LAYOUT);

        fontbold = Typeface.createFromAsset(DetailEventActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(DetailEventActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        Button map2 = (Button) findViewById(R.id.self);
        map2.setTypeface(fontbold);

        TextView a= findViewById(R.id.acara);
        TextView b= findViewById(R.id.acara1);
        TextView c= findViewById(R.id.acara2);
        TextView d= findViewById(R.id.acara3);
        TextView e= findViewById(R.id.acara4);
        TextView f= findViewById(R.id.deskripsi);
        //
        a.setTypeface(fontbold);
        b.setTypeface(fontbold);
        c.setTypeface(fontbold);
        d.setTypeface(fontbold);
        e.setTypeface(fontbold);
        f.setTypeface(fontbold);
        //
        e.setText(runName);
        c.setText("speaker");
        d.setText(runPlace);
        f.setText("desc");

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
