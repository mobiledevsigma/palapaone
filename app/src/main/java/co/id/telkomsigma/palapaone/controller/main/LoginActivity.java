package co.id.telkomsigma.palapaone.controller.main;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout txt_username;
    private TextInputLayout txt_pass;
    private EditText et_username;
    private EditText et_pass;
    private Button btn_login;
    private SessionManager session;
    private DictionaryManager dictionary;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fontbold = Typeface.createFromAsset(LoginActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(LoginActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        AndroidNetworking.initialize(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        dictionary = new DictionaryManager(getApplicationContext());

        txt_username = findViewById(R.id.txt_username);
        txt_pass = findViewById(R.id.txt_password);
        et_username = findViewById(R.id.et_username);
        et_pass = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        //
        txt_username.setTypeface(fontbold);
        txt_pass.setTypeface(fontbold);
        et_username.setTypeface(fontbold);
        et_pass.setTypeface(fontbold);
        btn_login.setTypeface(fontbold);

        txt_username.setHint(dictionary.getDictUname());
        txt_pass.setHint(dictionary.getDictPwd());
        btn_login.setText(dictionary.getDictLogin());

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_pass.getText().toString();
                if (!username.isEmpty() && !password.isEmpty()) {
                    sendAuth(username, password);
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill required field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendAuth(String user, String pass) {
        AndroidNetworking.post(ConstantUtils.URL.LOGIN)
                .addBodyParameter("username", user)
                .addBodyParameter("password", pass)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            if (response.getString(ConstantUtils.LOGIN.TAG_SUCCESS).equals("1")) {
                                String userid = response.getString(ConstantUtils.LOGIN.TAG_USERID);
                                String username = response.getString(ConstantUtils.LOGIN.TAG_USERNAME);
                                String name = response.getString(ConstantUtils.LOGIN.TAG_NAME);
                                String email = response.getString(ConstantUtils.LOGIN.TAG_EMAIL);
                                String phone = response.getString(ConstantUtils.LOGIN.TAG_PHONE);
                                String about = response.getString(ConstantUtils.LOGIN.TAG_ABOUT);
                                String quote = response.getString(ConstantUtils.LOGIN.TAG_QUOTE);
                                String job = response.getString(ConstantUtils.LOGIN.TAG_JOB);
                                String office = response.getString(ConstantUtils.LOGIN.TAG_OFFICE);
                                String role = response.getString(ConstantUtils.LOGIN.TAG_ROLE);
                                String eventid = response.getString(ConstantUtils.LOGIN.TAG_EVENTID);
                                String event = response.getString(ConstantUtils.LOGIN.TAG_EVENT);
                                String parent = response.getString(ConstantUtils.LOGIN.TAG_PARENT);
                                String nasID = response.getString(ConstantUtils.LOGIN.TAG_NATIONAL_ID);
                                String nasName = response.getString(ConstantUtils.LOGIN.TAG_NATIONAL_NAME);
                                session.setUserSession(userid, username, name, email, phone, about, quote, job, office, role, eventid, event, parent, nasID, nasName);

                                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
