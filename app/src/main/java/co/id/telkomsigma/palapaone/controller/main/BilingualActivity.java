package co.id.telkomsigma.palapaone.controller.main;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    private static final int REQUEST_PERMISSIONS = 1;
    private static final int PERMISSION_ALL = 100;
    private Button btn_english;
    private Button btn_indo;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilingual);
        fontbold = Typeface.createFromAsset(BilingualActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(BilingualActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        AndroidNetworking.initialize(getApplicationContext());
        dictionary = new DictionaryManager(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        btn_english = findViewById(R.id.btn_english);
        btn_indo = findViewById(R.id.btn_indo);
        //
        btn_english.setTypeface(fontbold);
        btn_indo.setTypeface(fontbold);


        System.out.println("TES "+session.isLogin());
        if(session.isLogin()){
            Intent i = new Intent(BilingualActivity.this,MenuActivity.class);
            startActivity(i);
            finish();
        }

        btn_english.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
//                if (isPermissionEnabled()) {
//                    getLanguage("en");
//                } else {
//                    requestStoragePermission();
//                }
                if(!hasPermissions(BilingualActivity.this, PERMISSIONS)){
                    ActivityCompat.requestPermissions(BilingualActivity.this, PERMISSIONS, PERMISSION_ALL);
                } else {
                    getLanguage("en");
                }
            }
        });

        btn_indo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
//                if (isPermissionEnabled()) {
//                    getLanguage("id");
//                } else {
//                    requestStoragePermission();
//                }
                if(!hasPermissions(BilingualActivity.this, PERMISSIONS)){
                    ActivityCompat.requestPermissions(BilingualActivity.this, PERMISSIONS, PERMISSION_ALL);
                } else {
                    getLanguage("en");
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

    private void getLanguage(String kode) {
        AndroidNetworking.get(ConstantUtils.URL.DICTIONARY + "{bahasa}")
                .addPathParameter("bahasa", kode)
                .setTag("bahasa")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
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
                            dictionary.setLanguageMain(uname, pwd, login, msg, menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9);

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
                    }

                    @Override
                    public void onError(ANError anError) {

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

    private void showDialogPerm(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setCancelable(false)
                .create()
                .show();
    }

    public boolean isPermissionEnabled() {
        return ContextCompat.checkSelfPermission(BilingualActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestStoragePermission() {
//        ActivityCompat.requestPermissions(SplashScreen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS);
        showDialogPerm("The Permissions are required for this application. Please allow storage permission.",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            ActivityCompat.requestPermissions(BilingualActivity.this,
                                    new String[]{Manifest.permission.CALL_PHONE,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                            Manifest.permission.ACCESS_FINE_LOCATION,
                                            Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSIONS);
                        }
                    }
                });
        return;
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
//                    btn_english.setOnClickListener(new View.OnClickListener() {
//                        public void onClick(View arg0) {
//                            getLanguage("en");
//                        }
//                    });
//
//                    btn_indo.setOnClickListener(new View.OnClickListener() {
//                        public void onClick(View arg0) {
//                            getLanguage("id");
//                        }
//                    });
                    //else any one or both the permissions are not granted
                } else {
                    //Log.d(TAG, "Some permissions are not granted ask again ");
                    //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
                    // shouldShowRequestPermissionRationale will return true
                    //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
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
}
