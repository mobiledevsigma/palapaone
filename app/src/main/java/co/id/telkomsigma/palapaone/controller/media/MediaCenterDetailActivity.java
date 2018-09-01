package co.id.telkomsigma.palapaone.controller.media;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.controller.main.MainActivity;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class MediaCenterDetailActivity extends AppCompatActivity {

    private TextView textView;
    private PDFView pdfView;
    private Button button;
    private String namaFile;
    int id = 1;
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fontbold = Typeface.createFromAsset(MediaCenterDetailActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(MediaCenterDetailActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        setContentView(R.layout.activity_media_detail);
        textView = findViewById(R.id.txt_media_detail);
        pdfView = findViewById(R.id.pdfView);
        button = findViewById(R.id.btn_save);
        //
        textView.setTypeface(fontbold);
        button.setTypeface(fontbold);

        Intent intent = getIntent();
        final String title = intent.getStringExtra(ConstantUtils.MEDIA.TAG_NAME);
        final String linkDownload = intent.getStringExtra(ConstantUtils.MEDIA.TAG_FILE);

        textView.setText(title);
        new RetrievePDFStream().execute(linkDownload);
    }

    class RetrievePDFStream extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }
}
