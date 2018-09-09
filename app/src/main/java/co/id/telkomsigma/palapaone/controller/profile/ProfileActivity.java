package co.id.telkomsigma.palapaone.controller.profile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

        ImageView pict = findViewById(R.id.iv_profile);
        TextView nama = findViewById(R.id.text_uname);
        TextView uname = findViewById(R.id.text_bangsa);
        TextView alamat = findViewById(R.id.text_role);
        TextView email = findViewById(R.id.text_pekerjaan);
        TextView contact = findViewById(R.id.text_about);
        TextView emails = findViewById(R.id.text_email);
        TextView b = findViewById(R.id.no_telp);
        TextView c = findViewById(R.id.text_quotes);
        TextView logoutt = findViewById(R.id.text_logout);
        TextView logoutts = findViewById(R.id.text_kontak);
        //
        nama.setTypeface(fontbold);
        uname.setTypeface(fontbold);
        alamat.setTypeface(fontbold);
        email.setTypeface(fontbold);
        contact.setTypeface(fontbold);
        emails.setTypeface(fontbold);
        b.setTypeface(fontbold);
        c.setTypeface(fontbold);
        logoutt.setTypeface(fontbold);
        logoutts.setTypeface(fontbold);
        //
        if (session.getPhoto().isEmpty()) {
            pict.setImageResource(R.drawable.icon_avatars);
        } else {
            downloadImage(getApplicationContext(), session.getPhoto(), pict);
        }
        nama.setText(session.getName());
        uname.setText(session.getNationalName());
        alamat.setText(session.getEvent());
        email.setText(session.getJob());
        contact.setText(session.getAbout());
        emails.setText(session.getEmail());
        b.setText(session.getPhone());
        c.setText(session.getQuote());

        LinearLayout logout = findViewById(R.id.layout_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        LinearLayout goto_contact = findViewById(R.id.feedback);
        goto_contact.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), FeedbackActivity.class);
                startActivity(i);
            }
        });

        LinearLayout goto_edit = findViewById(R.id.edit);
        goto_edit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), ProfileEditActivity.class);
                startActivity(i);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Profile");
    }

    public void showAlertDialog() {
        new AlertDialog.Builder(this)
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

    private void downloadImage(Context context, String url, ImageView image) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.icon_avatars)
                .into(image);
    }
}