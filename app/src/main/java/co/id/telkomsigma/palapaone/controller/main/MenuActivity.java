package co.id.telkomsigma.palapaone.controller.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import co.id.telkomsigma.palapaone.BuildConfig;
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

    String[] lisImage;
    private boolean doubleBackToExitPressedOnce = false;
    private CarouselView carouselView;
    private TextView txt_greet, txt_count, txt_name_menu, txt_msg, txt_presence, txt_event, txt_speaker, txt_modul, txt_media, txt_expo, txt_gallery, txt_partner, txt_help;
    private LinearLayout lay_presence, lay_event, lay_speaker, lay_modul, lay_media, lay_expo, lay_gallery, lay_partner, lay_help;
    private DictionaryManager dictionary;
    private HashMap<String, String> listDict;
    private SessionManager session;
    private LinearLayout lay_profile, lay_inbox;
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
        txt_count = findViewById(R.id.txt_count);
        txt_name_menu = findViewById(R.id.txt_name_menu);
        lay_inbox = findViewById(R.id.lay_inbox);
        carouselView = findViewById(R.id.carouselView);
        txt_greet = findViewById(R.id.txt_greeting);
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
        txt_count.setTypeface(fontbold);
        txt_greet.setTypeface(fontbold);
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
        getCountInbox(session.getEventID());
        getVersion("android");

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

        setGreeting();
    }

    private void setGreeting() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm");
        String tim = mdformat.format(calendar.getTime());
        try {
            Date current = mdformat.parse(tim);
            Date six = mdformat.parse("06:00");
            Date twelve = mdformat.parse("12:00");
            Date eighteen = mdformat.parse("18:00");
            Date twentyfour = mdformat.parse("24:00");

            if (dictionary.getDictKode().equals("en")) {
                if (current.after(six) && current.before(twelve)) {
                    //morning
                    txt_greet.setText("Good Morning");
                } else if (current.after(twelve) && current.before(eighteen)) {
                    //afternoon
                    txt_greet.setText("Good Afternoon");
                } else if (current.after(eighteen) && current.before(twentyfour)) {
                    //evening
                    txt_greet.setText("Good Evening");
                } else if (current.after(twentyfour) && current.before(six)) {
                    //morning
                    txt_greet.setText("Good Morning");
                }
            } else {
                if (current.after(six) && current.before(twelve)) {
                    //morning
                    txt_greet.setText("Selamat Pagi");
                } else if (current.after(twelve) && current.before(eighteen)) {
                    //afternoon
                    txt_greet.setText("Selamat Siang");
                } else if (current.after(eighteen) && current.before(twentyfour)) {
                    //evening
                    txt_greet.setText("Selamat Sore");
                } else if (current.after(twentyfour) && current.before(six)) {
                    //morning
                    txt_greet.setText("Selamat Pagi");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

    private void getCountInbox(String event_id) {
        AndroidNetworking.get(ConstantUtils.URL.AMOUNT_NOTIF + "{event_id}")
                .addPathParameter("event_id", event_id)
                .setTag("Count")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String count = response.getString("count");
                            txt_count.setText(count);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void getVersion(String flag) {
        AndroidNetworking.get(ConstantUtils.URL.VERSION + "{flag}")
                .addPathParameter("flag", flag)
                .setTag("Version")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString(ConstantUtils.VERSION.TAG_STATUS);
                            String data = response.getString(ConstantUtils.VERSION.TAG_TITLE);
                            if (status.equals("1")) {
                                JSONArray jsonArray = response.getJSONArray(ConstantUtils.VERSION.TAG_TITLE);
                                for (int a = 0; a < jsonArray.length(); a++) {
                                    JSONObject object = jsonArray.getJSONObject(a);
                                    String id = object.getString(ConstantUtils.VERSION.TAG_ID);
                                    String number = object.getString(ConstantUtils.VERSION.TAG_NUMBER);
                                    object.getString("name");
                                    object.getString("tanggal");
                                    object.getString("url");
                                    object.getString("url_iphone");
                                    object.getString("note");
                                    object.getString("flag");

                                    int angka = Integer.parseInt(number);

                                    if (angka != BuildConfig.VERSION_CODE) {
                                        System.out.println("version " + number + " " + BuildConfig.VERSION_CODE);
                                        new AlertDialog.Builder(MenuActivity.this)
                                                .setMessage("There is a new version, let's check it out")
                                                .setCancelable(false)
                                                .setPositiveButton("Go", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                                                    }
                                                })
                                                .setNegativeButton("Later", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.cancel();
                                                    }
                                                })
                                                .show();
                                    }
                                }
                            }
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
        if (dictionary.getDictKode().equals("en")) {
            Toast.makeText(this, "Tap again to exit", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Tekan lagi untuk keluar", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
