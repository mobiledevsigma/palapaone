package co.id.telkomsigma.palapaone.controller.materi;

import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.adapter.MateriAdapter;
import co.id.telkomsigma.palapaone.model.MateriModel;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class MateriActivity extends AppCompatActivity {

    private TextView txt_materi;
    private ListView listView;
    private MateriModel model;
    private List<MateriModel> listModel;
    private MateriAdapter adapter;
    private Typeface font, fontbold;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        fontbold = Typeface.createFromAsset(MateriActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(MateriActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        txt_materi = findViewById(R.id.txt_materi);
        listView = findViewById(R.id.lv_materi);
        //
        txt_materi.setTypeface(fontbold);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Documents");

        getMateri();
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

    private void getMateri() {
        AndroidNetworking.get(ConstantUtils.URL.MATERI)
                .setTag("Materi")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            listModel = new ArrayList<MateriModel>();
                            JSONArray jsonArray = response.getJSONArray(ConstantUtils.MATERI.TAG_TITLE);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object = jsonArray.getJSONObject(a);
                                String id = object.getString(ConstantUtils.MATERI.TAG_ID);
                                String name = object.getString(ConstantUtils.MATERI.TAG_NAME);
                                String desc = object.getString(ConstantUtils.MATERI.TAG_DESC);
                                String file = object.getString(ConstantUtils.MATERI.TAG_FILE);
                                String speakID = object.getString(ConstantUtils.MATERI.TAG_SPEAK_ID);
                                String speakName = object.getString(ConstantUtils.MATERI.TAG_SPEAK_NAME);
                                model = new MateriModel(id, name, speakName, desc, file, speakID);
                                listModel.add(model);
                            }

                            adapter = new MateriAdapter(getApplicationContext(), listModel);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String linkDownload = listModel.get(position).getMateri_file();
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

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
