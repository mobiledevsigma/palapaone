package co.id.telkomsigma.palapaone.controller.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.controller.feedback.FeedbackActivity;
import co.id.telkomsigma.palapaone.controller.main.MainActivity;
import co.id.telkomsigma.palapaone.util.SessionManager;

public class ProfileActivity extends AppCompatActivity {
    private Typeface font, fontbold;
    private SessionManager session;
    private LinearLayout lay_edit, lay_feed, lay_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        session = new SessionManager(getApplicationContext());

        fontbold = Typeface.createFromAsset(ProfileActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(ProfileActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        TextView nama = (TextView) findViewById(R.id.text_uname);
        nama.setTypeface(fontbold);
        nama.setText(session.getName());
        TextView uname = (TextView) findViewById(R.id.text_bangsa);
        uname.setTypeface(fontbold);
        uname.setText(session.getNationalName());
        TextView alamat = (TextView) findViewById(R.id.text_role);
        alamat.setTypeface(fontbold);
        alamat.setText(session.getEvent());
        TextView email = (TextView) findViewById(R.id.text_pekerjaan);
        email.setTypeface(fontbold);
        email.setText(session.getJob());
        TextView contact = (TextView) findViewById(R.id.text_about);
        contact.setTypeface(fontbold);
        contact.setText(session.getAbout());
        TextView emails = (TextView) findViewById(R.id.text_email);
        emails.setTypeface(fontbold);
        emails.setText(session.getEmail());
        TextView b = (TextView) findViewById(R.id.no_telp);
        b.setTypeface(fontbold);
        b.setText(session.getPhone());
        TextView c = (TextView) findViewById(R.id.text_quotes);
        c.setTypeface(fontbold);
        c.setText(session.getQuote());
        TextView logoutt = (TextView) findViewById(R.id.text_logout);
        logoutt.setTypeface(fontbold);
        TextView logoutts = (TextView) findViewById(R.id.text_kontak);
        logoutts.setTypeface(fontbold);

        LinearLayout logout = (LinearLayout) findViewById(R.id.layout_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        LinearLayout goto_contact = (LinearLayout) findViewById(R.id.feedback);
        goto_contact.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), FeedbackActivity.class);
                startActivity(i);
            }
        });

        LinearLayout goto_edit = (LinearLayout) findViewById(R.id.edit);
        goto_edit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), ProfileEditActivity.class);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void showAlertDialog() {
        AlertDialog show = new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        session.logoutUser();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}