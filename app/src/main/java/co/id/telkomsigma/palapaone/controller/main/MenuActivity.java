package co.id.telkomsigma.palapaone.controller.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
import co.id.telkomsigma.palapaone.controller.gallery.GalleryActivity;
import co.id.telkomsigma.palapaone.controller.help.HelpActivity;
import co.id.telkomsigma.palapaone.controller.inbox.InboxActivity;
import co.id.telkomsigma.palapaone.controller.materi.MateriActivity;
import co.id.telkomsigma.palapaone.controller.media.MediaCenterActivity;
import co.id.telkomsigma.palapaone.controller.partner.PartnersActivity;
import co.id.telkomsigma.palapaone.controller.presence.PresenceActivity;
import co.id.telkomsigma.palapaone.controller.profile.ProfileActivity;
import co.id.telkomsigma.palapaone.controller.speaker.SpeakerActivity;
import co.id.telkomsigma.palapaone.util.DictionaryManager;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class MenuActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;
    private CarouselView carouselView;
    String[] lisImage ;
    private TextView txt_name_menu, txt_msg, txt_presence, txt_event, txt_speaker, txt_modul, txt_media, txt_expo, txt_gallery, txt_partner, txt_help;
    private LinearLayout lay_presence, lay_event, lay_speaker, lay_modul, lay_media, lay_expo, lay_gallery, lay_partner, lay_help;
    private DictionaryManager dictionary;
    private HashMap<String, String> listDict;
    private LinearLayout lay_profile, lay_inbox;
    private SessionManager session;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        fontbold = Typeface.createFromAsset(MenuActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(MenuActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        session = new SessionManager(getApplicationContext());
        dictionary = new DictionaryManager(getApplicationContext());
        listDict = dictionary.getDictHome();

        lay_profile = findViewById(R.id.lay_profile);
        txt_name_menu = findViewById(R.id.txt_name_menu);
        lay_inbox = findViewById(R.id.lay_inbox);
        carouselView = findViewById(R.id.carouselView);
        txt_msg = findViewById(R.id.txt_msg);
        txt_presence = findViewById(R.id.txt_menu_presence);
        txt_event = findViewById(R.id.txt_menu_event);
        txt_speaker = findViewById(R.id.txt_menu_speaker);
        txt_modul = findViewById(R.id.txt_menu_modul);
        txt_media = findViewById(R.id.txt_menu_media);
        txt_expo = findViewById(R.id.txt_menu_expo);
        txt_gallery = findViewById(R.id.txt_menu_gallery);
        txt_partner = findViewById(R.id.txt_menu_partners);
        txt_help = findViewById(R.id.txt_menu_help);

        lay_presence = findViewById(R.id.layout_presence);
        lay_event = findViewById(R.id.layout_event);
        lay_speaker = findViewById(R.id.layout_speaker);
        lay_modul = findViewById(R.id.layout_module);
        lay_media = findViewById(R.id.layout_media);
        lay_expo = findViewById(R.id.layout_expo);
        lay_gallery = findViewById(R.id.layout_galleries);
        lay_partner = findViewById(R.id.layout_partners);
        lay_help = findViewById(R.id.layout_help);
        //
        txt_msg.setTypeface(fontbold);
        txt_presence.setTypeface(fontbold);
        txt_event.setTypeface(fontbold);
        txt_speaker.setTypeface(fontbold);
        txt_modul.setTypeface(fontbold);
        txt_media.setTypeface(fontbold);
        txt_expo.setTypeface(fontbold);
        txt_gallery.setTypeface(fontbold);
        txt_partner.setTypeface(fontbold);
        txt_help.setTypeface(fontbold);

        getBanner(session.getEventID());

        txt_name_menu.setText(session.getName());
        txt_msg.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MSG));
        txt_presence.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_1));
        txt_event.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_2));
        txt_speaker.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_3));
        txt_modul.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_4));
        txt_media.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_5));
        txt_expo.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_6));
        txt_gallery.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_7));
        txt_partner.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_8));
        txt_help.setText(listDict.get(ConstantUtils.DICTIONARY.TAG_MENU_9));

        lay_inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InboxActivity.class);
                startActivity(intent);
            }
        });

        lay_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        lay_presence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PresenceActivity.class);
                startActivity(intent);
            }
        });

        lay_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EventActivity.class);
                startActivity(intent);
            }
        });

        lay_speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SpeakerActivity.class);
                startActivity(intent);
            }
        });

        lay_modul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MateriActivity.class);
                startActivity(intent);
            }
        });

        lay_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MediaCenterActivity.class);
                startActivity(intent);
            }
        });

        lay_expo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpoActivity.class);
                startActivity(intent);
            }
        });

        lay_partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PartnersActivity.class);
                startActivity(intent);
            }
        });

        lay_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
                startActivity(intent);
            }
        });

        lay_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
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

                            System.out.println("coba"+jsonArray);
                            lisImage = new String[jsonArray.length()];
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.BANNER.TAG_ID);
                                String img = object.getString(ConstantUtils.BANNER.TAG_IMAGE);
                                String event = object.getString(ConstantUtils.BANNER.TAG_EVENT);
                                String url = object.getString(ConstantUtils.BANNER.TAG_URL);
                                lisImage[a] = img;
                            }

                            ImageListener imageListener = new ImageListener() {
                                @Override
                                public void setImageForPosition(int position, ImageView imageView) {
                                    Picasso.with(getApplicationContext())
                                            .load(lisImage[position])
                                            .error(R.drawable.icon_avatars)
                                            .into(imageView);
                                }
                            };

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
