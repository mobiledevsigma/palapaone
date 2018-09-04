package co.id.telkomsigma.palapaone.controller.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class GalleryDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView txtCaption, txtDate;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);
        fontbold = Typeface.createFromAsset(GalleryDetailActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(GalleryDetailActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        imageView = findViewById(R.id.iv_gall_detail);
        txtCaption = findViewById(R.id.txt_gall_caption);
        txtDate = findViewById(R.id.txt_gall_date);

        txtCaption.setTypeface(fontbold);
        txtDate.setTypeface(fontbold);

        Intent intent = getIntent();
        String link = intent.getStringExtra(ConstantUtils.GALLERY.TAG_URL);
        String capt = intent.getStringExtra(ConstantUtils.GALLERY.TAG_CAPTION);
        String dates = intent.getStringExtra(ConstantUtils.GALLERY.TAG_DATE);

        downloadImage(getApplicationContext(), link, imageView);
        txtCaption.setText(capt);
        txtDate.setText(dates);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Galleries");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void downloadImage(Context context, String url, ImageView image) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.avatars)
                .into(image);
    }
}
