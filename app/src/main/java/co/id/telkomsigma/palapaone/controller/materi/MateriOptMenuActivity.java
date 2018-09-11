package co.id.telkomsigma.palapaone.controller.materi;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.controller.asking.AskingActivity;
import co.id.telkomsigma.palapaone.controller.feedback.FeedbackActivity;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class MateriOptMenuActivity extends Activity {

    private TextView textView;
    private Button btn_download, btn_feedback, btn_asking;
    private String fileName, idSpeaker, idMateri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_detail);
        textView = findViewById(R.id.txt_detail_materi);
        btn_download = findViewById(R.id.btn_materi_download);
        btn_feedback = findViewById(R.id.btn_fb_materi);
        btn_asking = findViewById(R.id.btn_asking_materi);

        Intent intent = getIntent();
        fileName = intent.getStringExtra(ConstantUtils.MATERI.TAG_FILE);
        idSpeaker = intent.getStringExtra(ConstantUtils.MATERI.TAG_SPEAK_ID);
        idMateri = intent.getStringExtra(ConstantUtils.MATERI.TAG_ID);

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadFile(fileName);
            }
        });

        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedbackActivity.class);
                intent.putExtra(ConstantUtils.FEEDBACK.TAG_TYPE, "1");
                intent.putExtra(ConstantUtils.MATERI.TAG_SPEAK_ID, idSpeaker);
                startActivity(intent);
            }
        });

        btn_asking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AskingActivity.class);
                intent.putExtra(ConstantUtils.MATERI.TAG_ID, idMateri);
                startActivity(intent);
            }
        });


    }

    private void DownloadFile(String linkDownload) {
        Toast.makeText(getApplicationContext(), "Downloading file..", Toast.LENGTH_SHORT).show();
        String destination = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/";
        final String fileName = linkDownload.split("/")[linkDownload.split("/").length - 1];

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

                Toast.makeText(getApplicationContext(), "File downloaded", Toast.LENGTH_SHORT).show();
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
}
