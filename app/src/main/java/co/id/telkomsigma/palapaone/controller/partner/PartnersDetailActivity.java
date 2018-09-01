package co.id.telkomsigma.palapaone.controller.partner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class PartnersDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView txt_desc;
    private TextView txt_desc_detail;
    private TextView txt_contact;
    private TextView txt_address;
    private TextView txt_phone;
    private TextView txt_web;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fontbold = Typeface.createFromAsset(PartnersDetailActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(PartnersDetailActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        setContentView(R.layout.activity_detail_partner);
        imageView = findViewById(R.id.iv_partner_detail);
        txt_desc =findViewById(R.id.txt_desc);
        txt_desc_detail = findViewById(R.id.txt_desc_detail);
        txt_contact = findViewById(R.id.contactinfo);
        txt_address = findViewById(R.id.txt_address);
        txt_phone = findViewById(R.id.txt_partner_phone);
        txt_web = findViewById(R.id.txt_partner_web);
        //
        txt_desc.setTypeface(fontbold);
        txt_desc_detail.setTypeface(fontbold);
        txt_contact.setTypeface(fontbold);
        txt_address.setTypeface(fontbold);
        txt_phone.setTypeface(fontbold);
        txt_web.setTypeface(fontbold);

        Intent intent = getIntent();
        String logo = intent.getStringExtra(ConstantUtils.PARTNER.TAG_LOGO);
        String desc_detail = intent.getStringExtra(ConstantUtils.PARTNER.TAG_DESC);
        String address = intent.getStringExtra(ConstantUtils.PARTNER.TAG_ADDRESS);
        String phone = intent.getStringExtra(ConstantUtils.PARTNER.TAG_PHONE);
        String web = intent.getStringExtra(ConstantUtils.PARTNER.TAG_URL);

        downloadImage(getApplicationContext(), logo, imageView);
        txt_desc_detail.setText(desc_detail);
        txt_desc_detail.setMovementMethod(new ScrollingMovementMethod());
        txt_address.setText(address);
        txt_phone.setText(phone);
        txt_web.setText(web);
    }

    private void downloadImage(Context context, String url, ImageView image) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.avatars)
                .into(image);
    }
}
