package co.id.telkomsigma.palapaone.controller.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.util.DictionaryManager;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class BilingualActivity extends AppCompatActivity {

    private Button btn_english;
    private Button btn_indo;
    private DictionaryManager dictionary;
    private SessionManager session;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilingual);
        fontbold = Typeface.createFromAsset(BilingualActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(BilingualActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        AndroidNetworking.initialize(getApplicationContext());
        dictionary = new DictionaryManager(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        btn_english = findViewById(R.id.btn_english);
        btn_indo = findViewById(R.id.btn_indo);
        //
        btn_english.setTypeface(fontbold);
        btn_indo.setTypeface(fontbold);

        btn_english.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                getLanguage("en");
            }
        });

        btn_indo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                getLanguage("id");
            }
        });
    }

    private void getLanguage(String kode) {
        AndroidNetworking.get(ConstantUtils.URL.DICTIONARY + "{bahasa}")
                .addPathParameter("bahasa", kode)
                .setTag("bahasa")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String uname = response.getString(ConstantUtils.DICTIONARY.TAG_USERNAME);
                            String pwd = response.getString(ConstantUtils.DICTIONARY.TAG_PASSWORD);
                            String login = response.getString(ConstantUtils.DICTIONARY.TAG_LOGIN);
                            String msg = response.getString(ConstantUtils.DICTIONARY.TAG_MSG);
                            String menu1 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_1);
                            String menu2 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_2);
                            String menu3 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_3);
                            String menu4 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_4);
                            String menu5 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_5);
                            String menu6 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_6);
                            String menu7 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_7);
                            String menu8 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_8);
                            String menu9 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_9);
                            dictionary.setLanguageMain(uname, pwd, login, msg, menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9);

                            if (session.isLogin()) {
                                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getApplicationContext(), BeforeLoginActivity.class);
                                startActivity(intent);
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
}
