package co.id.telkomsigma.palapaone.controller.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class ProfileEditActivity extends AppCompatActivity {
    Typeface font, fontbold;
    EditText a3;
    EditText a5;
    EditText role;
    EditText job;
    EditText about;
    EditText email;
    EditText phone;
    EditText quote;
    LinearLayout selesai;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        session = new SessionManager(getApplicationContext());
        Typeface fontbold = Typeface.createFromAsset(ProfileEditActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        Typeface font = Typeface.createFromAsset(ProfileEditActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        TextView ax = (TextView) findViewById(R.id.q);
        ax.setTypeface(fontbold);
        TextView a1 = (TextView) findViewById(R.id.w);
        a1.setTypeface(fontbold);
        TextView a2 = (TextView) findViewById(R.id.e);
        a2.setTypeface(fontbold);
        TextView a4 = (TextView) findViewById(R.id.t);
        a4.setTypeface(fontbold);
        TextView a6 = (TextView) findViewById(R.id.u);
        a6.setTypeface(fontbold);
        TextView a7 = (TextView) findViewById(R.id.o);
        a7.setTypeface(fontbold);
        TextView a8 = (TextView) findViewById(R.id.a);
        a8.setTypeface(fontbold);

        LinearLayout goto_image = (LinearLayout) findViewById(R.id.image);
        goto_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 11);
            }
        });

        a3 = (EditText) findViewById(R.id.r);
        a5 = (EditText) findViewById(R.id.y);
        role = (EditText) findViewById(R.id.i);
        job = (EditText) findViewById(R.id.p);
        about = (EditText) findViewById(R.id.s);
        email = (EditText) findViewById(R.id.f);
        phone = (EditText) findViewById(R.id.h);
        quote = (EditText) findViewById(R.id.k);

        a3.setTypeface(fontbold);
        a3.setText(session.getName());
        a5.setTypeface(fontbold);
        a5.setHint(session.getNationalName());
        role.setTypeface(fontbold);
        role.setHint(session.getRole());
        job.setTypeface(fontbold);
        job.setText(session.getJob());
        about.setTypeface(fontbold);
        about.setText(session.getAbout());
        email.setTypeface(fontbold);
        email.setHint(session.getEmail());
        phone.setTypeface(fontbold);
        phone.setHint(session.getPhone());
        quote.setTypeface(fontbold);
        quote.setText(session.getQuote());

        selesai = findViewById(R.id.done);
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getApplicationContext())
                        .setMessage("Are you sure you want to update your profile?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                saveEdit(a3.getText().toString(), email.getText().toString(), phone.getText().toString(), about.getText().toString(), quote.getText().toString(),
                                        job.getText().toString(), session.getOffice(), session.getNationalID(), session.getId());
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void saveEdit(String name, String email, String phone, String about, String quote, String job, String office, String nation, String user) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(ConstantUtils.LOGIN.TAG_NAME, name);
            jsonObject.put(ConstantUtils.LOGIN.TAG_EMAIL, email);
            jsonObject.put(ConstantUtils.LOGIN.TAG_PHONE, phone);
            jsonObject.put(ConstantUtils.LOGIN.TAG_ABOUT, about);
            jsonObject.put(ConstantUtils.LOGIN.TAG_QUOTE, quote);
            jsonObject.put(ConstantUtils.LOGIN.TAG_JOB, job);
            jsonObject.put(ConstantUtils.LOGIN.TAG_OFFICE, office);
            jsonObject.put(ConstantUtils.LOGIN.TAG_NATIONAL_ID, nation);
            jsonObject.put(ConstantUtils.LOGIN.TAG_USERID, user);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post(ConstantUtils.URL.PROFILE_EDIT)
                .addJSONObjectBody(jsonObject) // posting json
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
