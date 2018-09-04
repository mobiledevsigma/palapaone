package co.id.telkomsigma.palapaone.controller.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.controller.event.EventActivity;
import co.id.telkomsigma.palapaone.controller.expo.ExpoActivity;
import co.id.telkomsigma.palapaone.controller.help.HelpActivity;
import co.id.telkomsigma.palapaone.controller.media.MediaCenterActivity;
import co.id.telkomsigma.palapaone.controller.partner.PartnersActivity;
import co.id.telkomsigma.palapaone.controller.speaker.SpeakerActivity;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class BeforeLoginActivity extends AppCompatActivity {
    Typeface font, fontbold;

    CarouselView carouselView;
    String[] lisImage;
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.with(getApplicationContext())
                    .load(lisImage[position])
                    .error(R.drawable.avatars)
                    .into(imageView);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);

        fontbold = Typeface.createFromAsset(BeforeLoginActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(BeforeLoginActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

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

        SessionManager session = new SessionManager(getApplicationContext());


        carouselView = findViewById(R.id.carouselView);
        getBanner(session.getParentID());


        //  CarouselView goto_details= (CarouselView) findViewById(R.id.carouselView);
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

        LinearLayout goto_event = (LinearLayout) findViewById(R.id.layout_event);
        goto_event.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(i);
            }
        });

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

    private void getBanner(String event_id) {
        AndroidNetworking.get(ConstantUtils.URL.BANNER + "{event_id}")
                .addPathParameter("event_id", event_id)
                .setTag("Banner")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.BANNER.TAG_TITLE);

                            System.out.println("coba" + jsonArray);
                            lisImage = new String[jsonArray.length()];
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.BANNER.TAG_ID);
                                String img = object.getString(ConstantUtils.BANNER.TAG_IMAGE);
                                String event = object.getString(ConstantUtils.BANNER.TAG_EVENT);
                                String url = object.getString(ConstantUtils.BANNER.TAG_URL);

                                lisImage[a] = img;
                            }
                            carouselView.setImageListener(imageListener);
                            carouselView.setPageCount(lisImage.length);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
