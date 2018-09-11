package co.id.telkomsigma.palapaone.controller.main;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.util.DictionaryManager;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class BilingualActivity extends AppCompatActivity {

    private boolean doubleBackToExitPressedOnce = false;
    private static final int REQUEST_PERMISSIONS = 1;
    private static final int PERMISSION_ALL = 100;
    private Button btn_english;
    private Button btn_indo;
    private int getClick;
    private DictionaryManager dictionary;
    private SessionManager session;
    private Typeface font, fontbold;
    String[] PERMISSIONS = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA
    };
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilingual);
        fontbold = Typeface.createFromAsset(BilingualActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(BilingualActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        AndroidNetworking.initialize(getApplicationContext());
        dictionary = new DictionaryManager(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        progressBar = findViewById(R.id.progressBar);
        btn_english = findViewById(R.id.btn_english);
        btn_indo = findViewById(R.id.btn_indo);
        //
        progressBar.setVisibility(View.GONE);
        btn_english.setTypeface(fontbold);
        btn_indo.setTypeface(fontbold);

        if(session.isLogin()){
            Intent i = new Intent(BilingualActivity.this,MenuActivity.class);
            startActivity(i);
            finish();
        }

        btn_english.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(!hasPermissions(BilingualActivity.this, PERMISSIONS)){
                    ActivityCompat.requestPermissions(BilingualActivity.this, PERMISSIONS, PERMISSION_ALL);
                } else {
                    getLanguage("en");
                    getClick = 0;
                }
            }
        });

        btn_indo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if(!hasPermissions(BilingualActivity.this, PERMISSIONS)){
                    ActivityCompat.requestPermissions(BilingualActivity.this, PERMISSIONS, PERMISSION_ALL);
                } else {
                    getLanguage("id");
                    getClick = 1;
                }
            }
        });
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void getLanguage(final String kode) {
        progressBar.setVisibility(View.VISIBLE);
        System.out.println("bahasa0 ");
        AndroidNetworking.get(ConstantUtils.URL.DICTIONARY + "{bahasa}")
                .addPathParameter("bahasa", kode)
                .setTag("bahasa")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("bahasa " +response);
                        try {
                            String uname = response.getString(ConstantUtils.DICTIONARY.TAG_USERNAME);
                            String pwd = response.getString(ConstantUtils.DICTIONARY.TAG_PASSWORD);
                            String login = response.getString(ConstantUtils.DICTIONARY.TAG_LOGIN);
                            String msg = response.getString(ConstantUtils.DICTIONARY.TAG_MSG);
                            String menu1 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_1);
                            String menu2 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_2);
                            String menu3 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_3);
                            String menu4 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_4);
                            String menu5 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_5);
                            String menu6 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_6);
                            String menu7 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_7);
                            String menu8 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_8);
                            String menu9 = response.getString(ConstantUtils.DICTIONARY.TAG_MENU_9);
                            dictionary.setLanguageMain(uname, pwd, login, msg, menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9, kode);
                            session.setLanguage();

                            if (session.isLogin()) {
                                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getApplicationContext(), BeforeLoginActivity.class);
                                startActivity(intent);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        System.out.println("bahasa2 ");
                    }
                });
    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("SETTINGS", okListener)
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        //Log.d(TAG, "Permission callback called-------");
        if (requestCode == REQUEST_PERMISSIONS) {
            //Log.d(TAG, "code    ---    " + requestCode);
            Map<String, Integer> perms = new HashMap<>();
            // Initialize the map with both permissions
            perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
            perms.put(Manifest.permission.CALL_PHONE, PackageManager.PERMISSION_GRANTED);
            perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
            perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
            // Fill with actual results from user
            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for both permissions
                if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        || perms.get(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
                        || perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //Log.d(TAG, "all permission granted");
                    // process the normal flow
                    if (getClick == 0) {
                        Intent intent = new Intent(getApplicationContext(), BeforeLoginActivity.class);
                        startActivity(intent);
                        getLanguage("en");
                    } else if (getClick == 1){
                        Intent intent = new Intent(getApplicationContext(), BeforeLoginActivity.class);
                        startActivity(intent);
                        getLanguage("id");
                    }
                    //else any one or both the permissions are not granted
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        showDialogOK("The Permissions are required for this application",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case DialogInterface.BUTTON_POSITIVE:
                                                // proceed with logic by disabling the related features or quit the app.
                                                break;
                                            case DialogInterface.BUTTON_NEGATIVE:
                                                //checkAndRequestPermissions();
                                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                        Uri.fromParts("package", getPackageName(), null));
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);
                                                break;
                                        }
                                    }
                                });
                    } else {
                        //nothing
                    }
                }
            }
        } else {
            Log.d("hasil code", String.valueOf(requestCode));
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tap one again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
