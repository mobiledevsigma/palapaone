package co.id.telkomsigma.palapaone.controller.inbox;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import co.id.telkomsigma.palapaone.R;

public class DetailInboxActivity extends AppCompatActivity {

    Typeface font,fontbold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_detail);

        fontbold = Typeface.createFromAsset(DetailInboxActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(DetailInboxActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        TextView schedule = (TextView) findViewById(R.id.textView_scann3);
        schedule.setTypeface(fontbold);
        TextView schedulee = (TextView) findViewById(R.id.textView_scann5);
        schedulee.setTypeface(font);
        TextView schedullee = (TextView) findViewById(R.id.textView_scann6);
        schedullee.setTypeface(fontbold);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Inbox");
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
