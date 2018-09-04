package co.id.telkomsigma.palapaone.controller.gallery;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import co.id.telkomsigma.palapaone.R;

public class TakePhotoActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btn_choose;
    private EditText editText;
    private Button btn_upload;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        fontbold = Typeface.createFromAsset(TakePhotoActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(TakePhotoActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        imageView = findViewById(R.id.iv_take_photo);
        btn_choose = findViewById(R.id.btn_choose);
        editText = findViewById(R.id.et_caption);
        btn_upload = findViewById(R.id.btn_upload);

        btn_choose.setTypeface(fontbold);
        editText.setTypeface(fontbold);
        btn_upload.setTypeface(fontbold);

        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This feature is currently unavailable", Toast.LENGTH_SHORT).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Take a Photo");
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
}
