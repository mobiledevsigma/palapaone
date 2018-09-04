package co.id.telkomsigma.palapaone.controller.event;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import co.id.telkomsigma.palapaone.R;

public class ShowLayoutActivity extends AppCompatActivity {

    Typeface font,fontbold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_layout);

        fontbold = Typeface.createFromAsset(ShowLayoutActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(ShowLayoutActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        TextView daftarkios= (TextView) findViewById(R.id.text_judulgedung);
        daftarkios.setTypeface(fontbold);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
