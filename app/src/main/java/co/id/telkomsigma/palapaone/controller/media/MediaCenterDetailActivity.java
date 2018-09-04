package co.id.telkomsigma.palapaone.controller.media;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Media Center");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Downloading file..",Toast.LENGTH_SHORT).show();
                String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
                final String fileName = linkDownload.split("/")[linkDownload.split("/").length-1];

                destination += fileName;
                final Uri uri = Uri.parse("file://" + destination);


                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(linkDownload));
                request.setDescription("Download " + fileName);
                request.setTitle(fileName);

                //set destination
                request.setDestinationUri(uri);

                // get download service and enqueue file
                final DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                final long downloadId = manager.enqueue(request);

                BroadcastReceiver onComplete = new BroadcastReceiver() {
                    public void onReceive(Context ctxt, Intent intent) {

                        Toast.makeText(getApplicationContext(),"File downloaded",Toast.LENGTH_SHORT).show();
                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(getApplicationContext())
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setContentTitle(fileName)
                                        .setContentText("Download completed");


                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(682736, mBuilder.build());
                    }
                };
                //register receiver for when .apk download is compete
                registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
            }
        });
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
