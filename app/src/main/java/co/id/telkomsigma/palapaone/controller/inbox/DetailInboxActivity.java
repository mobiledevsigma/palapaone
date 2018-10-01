package co.id.telkomsigma.palapaone.controller.inbox;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class DetailInboxActivity extends AppCompatActivity {

    Typeface font,fontbold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_detail);

        fontbold = Typeface.createFromAsset(DetailInboxActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(DetailInboxActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        TextView title = findViewById(R.id.textView_scann3);
        TextView date = findViewById(R.id.textView_scann5);
        TextView isi = findViewById(R.id.textView_scann6);
        title.setTypeface(fontbold);
        date.setTypeface(font);
        isi.setTypeface(fontbold);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra(ConstantUtils.NOTIF.TAG_TITLE));
        date.setText(intent.getStringExtra(ConstantUtils.NOTIF.TAG_DATE));
        isi.setText(intent.getStringExtra(ConstantUtils.NOTIF.TAG_TEXT));

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
