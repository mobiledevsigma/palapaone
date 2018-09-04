package co.id.telkomsigma.palapaone.controller.help;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import co.id.telkomsigma.palapaone.R;

public class HelpActivity extends AppCompatActivity {

    private LinearLayout lay_faq, lay_committee, lay_about, lay_policy, lay_contact;
    private TextView txt_faq, txt_committee, txt_about, txt_policy, txt_contact;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fontbold = Typeface.createFromAsset(HelpActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(HelpActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        setContentView(R.layout.activity_help);
        lay_faq = findViewById(R.id.lay_faq);
        lay_committee = findViewById(R.id.lay_committee);
        lay_about = findViewById(R.id.lay_about);
        lay_policy = findViewById(R.id.lay_policy);
        lay_contact = findViewById(R.id.lay_contacts);
        //
        txt_faq = findViewById(R.id.txt_faq);
        txt_committee = findViewById(R.id.txt_committee);
        txt_about = findViewById(R.id.txt_about);
        txt_policy = findViewById(R.id.txt_policy);
        txt_contact = findViewById(R.id.txt_contact);
        //
        txt_faq.setTypeface(fontbold);
        txt_committee.setTypeface(fontbold);
        txt_about.setTypeface(fontbold);
        txt_policy.setTypeface(fontbold);
        txt_contact.setTypeface(fontbold);

        lay_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, HelpWebActivity.class);
                intent.putExtra("link_url", "http://palapaone.id/icw/faq");
                intent.putExtra("title", "FAQ");
                startActivity(intent);
            }
        });

        lay_committee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, HelpWebActivity.class);
                intent.putExtra("link_url", "http://palapaone.id/icw/committee");
                intent.putExtra("title", "Committee");
                startActivity(intent);
            }
        });

        lay_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        lay_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, HelpWebActivity.class);
                intent.putExtra("link_url", "http://palapaone.id/icw/Privacy_policy");
                intent.putExtra("title", "Privacy Policy");
                startActivity(intent);
            }
        });

        lay_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Help");
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
