package co.id.telkomsigma.palapaone.controller.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.controller.expo.ExpoActivity;
import co.id.telkomsigma.palapaone.controller.help.HelpActivity;
import co.id.telkomsigma.palapaone.controller.media.MediaCenterActivity;
import co.id.telkomsigma.palapaone.controller.partner.PartnersActivity;
import co.id.telkomsigma.palapaone.controller.speaker.SpeakerActivity;

public class BeforeLoginActivity extends AppCompatActivity {
    Typeface font, fontbold;

    CarouselView carouselView;
    //int[] sampleImages = {R.drawable.bannerku};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);

        fontbold = Typeface.createFromAsset(BeforeLoginActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(BeforeLoginActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

//        TextView toolbar = (TextView) findViewById(R.id.toolbartext);
//        toolbar.setTypeface(fontbold);
        TextView schedule = (TextView) findViewById(R.id.text_presence);
        schedule.setTypeface(fontbold);
        TextView speaker = (TextView) findViewById(R.id.text_speaker);
        speaker.setTypeface(fontbold);
        TextView sspeaker = (TextView) findViewById(R.id.text_schedule);
        sspeaker.setTypeface(fontbold);
        TextView speakers = (TextView) findViewById(R.id.text_event);
        speakers.setTypeface(fontbold);
        TextView committee = (TextView) findViewById(R.id.text_committee);
        committee.setTypeface(fontbold);
        TextView galleries = (TextView) findViewById(R.id.text_galleries);
        galleries.setTypeface(fontbold);
        TextView kios = (TextView) findViewById(R.id.text_partner);
        kios.setTypeface(fontbold);
        TextView kontak = (TextView) findViewById(R.id.text_help);
        kontak.setTypeface(fontbold);
        TextView kkontak = (TextView) findViewById(R.id.text_expo);
        kkontak.setTypeface(fontbold);
        Button login = (Button) findViewById(R.id.button_login);
        login.setTypeface(fontbold);

        carouselView = findViewById(R.id.carouselView);
//        carouselView.setPageCount(sampleImages.length);
//        carouselView.setImageListener(imageListener);
//        CarouselView goto_details= (CarouselView) findViewById(R.id.carouselView);
//        goto_details.setImageClickListener(new ImageClickListener() {
//            @Override
//            public void onClick(int position) {
//                Intent i = new Intent(BeforeLoginActivity.this, DetailBannerActivity.class);
//                startActivity(i);
//            }
//        });


        Button goto_login = (Button) findViewById(R.id.button_login);
        goto_login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });


//        LinearLayout goto_event= (LinearLayout) findViewById(R.id.layout_event);
//        goto_event.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                Intent i = new Intent(getApplicationContext(), EventMapActivity.class);
//                startActivity(i);
//            }
//        });

        LinearLayout goto_presence = (LinearLayout) findViewById(R.id.layout_qr);
        goto_presence.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "You have to login first", Toast.LENGTH_SHORT).show();
            }
        });

//        LinearLayout goto_event = (LinearLayout) findViewById(R.id.layout_event);
//        goto_presence.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View arg0) {
//                Intent i = new Intent(getApplicationContext(), EventActivity.class);
//                startActivity(i);
//            }
//        });

        LinearLayout goto_a = (LinearLayout) findViewById(R.id.layout_speaker);
        goto_a.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), SpeakerActivity.class);
                startActivity(i);
            }
        });

        LinearLayout goto_module = (LinearLayout) findViewById(R.id.layout_module);
        goto_module.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "You have to login first", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout goto_news = (LinearLayout) findViewById(R.id.layout_media);
        goto_news.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), MediaCenterActivity.class);
                startActivity(i);
            }
        });

        LinearLayout goto_partner = (LinearLayout) findViewById(R.id.layout_partner);
        goto_partner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), PartnersActivity.class);
                startActivity(i);
            }
        });

        LinearLayout goto_galeri = (LinearLayout) findViewById(R.id.layout_galleries);
        goto_galeri.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "You have to login first", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout goto_expo = (LinearLayout) findViewById(R.id.layout_expo);
        goto_expo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), ExpoActivity.class);
                startActivity(i);
            }
        });
        LinearLayout goto_help = (LinearLayout) findViewById(R.id.layout_help);
        goto_help.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(i);
            }
        });
    }
//    ImageListener imageListener = new ImageListener() {
//        @Override
//        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(sampleImages[position]);
//        }
//    };
}
