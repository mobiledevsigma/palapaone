package co.id.telkomsigma.palapaone.controller.presence;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.qrcode.Contents;
import co.id.telkomsigma.palapaone.util.qrcode.QRCodeEncoder;

public class PresenceActivity extends AppCompatActivity {

    private TextView txt_scan;
    private TextView txt_scanName;
    private TextView txt_scanRole;
    private TextView txt_scanNation;
    private ImageView img_qr;
    private SessionManager session;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fontbold = Typeface.createFromAsset(PresenceActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(PresenceActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");
        session = new SessionManager(getApplicationContext());

        setContentView(R.layout.activity_presence);
        txt_scan = findViewById(R.id.txt_scan);
        txt_scanName = findViewById(R.id.txt_scan_name);
        txt_scanRole = findViewById(R.id.txt_scan_role);
        txt_scanNation = findViewById(R.id.txt_scan_nation);
        img_qr = findViewById(R.id.img_qr);
        //
        txt_scan.setTypeface(fontbold);
        txt_scanName.setTypeface(fontbold);
        txt_scanRole.setTypeface(fontbold);
        txt_scanNation.setTypeface(fontbold);

        generateQRCode(session.getId(), img_qr);
        txt_scanName.setText(session.getName());
        txt_scanRole.setText(session.getEvent());
        txt_scanNation.setText(session.getNationalName());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Presence");
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

    private void generateQRCode(String qrInputText, ImageView myImage) {
        //Find screen size
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        //Encode with a QR Code image
        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrInputText,
                null,
                Contents.Type.TEXT,
                BarcodeFormat.QR_CODE.toString(),
                smallerDimension);
        try {
            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
            myImage.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
