package co.id.telkomsigma.palapaone.controller.expo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class ExpoDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView txt_name, txt_desc;
    private Button button;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expo_detail);
        fontbold = Typeface.createFromAsset(ExpoDetailActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(ExpoDetailActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        imageView = findViewById(R.id.iv_expo_detail);
        txt_name = findViewById(R.id.txt_expo_name);
        txt_desc = findViewById(R.id.txt_expo_desc);
        button = findViewById(R.id.btn_expo_map);

        txt_name.setTypeface(fontbold);
        txt_desc.setTypeface(fontbold);
        button.setTypeface(fontbold);

        Intent intent = getIntent();
        downloadImage(getApplicationContext(), intent.getStringExtra(ConstantUtils.EXPO.TAG_PROD), imageView);
        txt_name.setText(intent.getStringExtra(ConstantUtils.EXPO.TAG_NAME));
        txt_desc.setText(intent.getStringExtra(ConstantUtils.EXPO.TAG_DESC));
    }

    private void downloadImage(Context context, String url, ImageView image) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.avatars)
                .into(image);
    }
}
