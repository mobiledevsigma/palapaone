package co.id.telkomsigma.palapaone.controller.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
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

import java.util.HashMap;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.controller.event.EventActivity;
import co.id.telkomsigma.palapaone.controller.expo.ExpoActivity;
import co.id.telkomsigma.palapaone.controller.help.HelpActivity;
import co.id.telkomsigma.palapaone.controller.media.MediaCenterActivity;
import co.id.telkomsigma.palapaone.controller.partner.PartnersActivity;
import co.id.telkomsigma.palapaone.controller.speaker.SpeakerActivity;
import co.id.telkomsigma.palapaone.util.DictionaryManager;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class BeforeLoginActivity extends AppCompatActivity {

    private Typeface font, fontbold;
    private boolean doubleBackToExitPressedOnce = false;
    private DictionaryManager dictionary;
    private HashMap<String, String> listDict;

    CarouselView carouselView;
    String[] lisImage;
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.with(getApplicationContext())
                    .load(lisImage[position])
                    .error(R.drawable.icon_avatars)
                    .into(imageView);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_login);

        dictionary = new DictionaryManager(getApplicationContext());
        listDict = dictionary.getDictHome();

        fontbold = Typeface.createFromAsset(BeforeLoginActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(BeforeLoginActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        TextView presence = findViewById(R.id.text_presence);
        TextView speaker = findViewById(R.id.text_speaker);
        TextView document = findViewById(R.id.text_document);
        TextView event = findViewById(R.id.text_event);
        TextView media = findViewById(R.id.text_media);
        TextView galleries = findViewById(R.id.text_galleries);
        TextView partner = findViewById(R.id.text_partner);
        TextView help = findViewById(R.id.text_help);
        TextView expo = findViewById(R.id.text_expo);
        Button login = findViewById(R.id.button_login);
        carouselView = findViewById(R.id.carouselView);
        //
        presence.setTypeface(fontbold);
        speaker.setTypeface(fontbold);
        document.setTypeface(fontbold);
        event.setTypeface(fontbold);
        media.setTypeface(fontbold);
        galleries.setTypeface(fontbold);
        partner.setTypeface(fontbold);
        help.setTypeface(fontbold);
        expo.setTypeface(fontbold);
        login.setTypeface(fontbold);
        //
        presence.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_1));
        event.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_2));
        speaker.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_3));
        document.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_4));
        media.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_5));
        expo.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_6));
        galleries.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_7));
        partner.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_8));
        help.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_9));

        SessionManager session = new SessionManager(getApplicationContext());

        getBanner(session.getParentID());

        Button goto_login = findViewById(R.id.button_login);
        goto_login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        LinearLayout goto_presence = findViewById(R.id.layout_qr);
        goto_presence.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "You have to login first", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout goto_event = findViewById(R.id.layout_event);
        goto_event.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(i);
            }
        });

        LinearLayout goto_a = findViewById(R.id.layout_speaker);
        goto_a.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), SpeakerActivity.class);
                startActivity(i);
            }
        });

        LinearLayout goto_module = findViewById(R.id.layout_module);
        goto_module.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "You have to login first", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout goto_news = findViewById(R.id.layout_media);
        goto_news.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), MediaCenterActivity.class);
                startActivity(i);
            }
        });

        LinearLayout goto_partner = findViewById(R.id.layout_partner);
        goto_partner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), PartnersActivity.class);
                startActivity(i);
            }
        });

        LinearLayout goto_galeri = findViewById(R.id.layout_galleries);
        goto_galeri.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "You have to login first", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout goto_expo = findViewById(R.id.layout_expo);
        goto_expo.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), ExpoActivity.class);
                startActivity(i);
            }
        });
        LinearLayout goto_help = findViewById(R.id.layout_help);
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

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Klik lagi untuk ke menu utama", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}