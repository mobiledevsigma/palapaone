package co.id.telkomsigma.palapaone.controller.profile;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.util.SessionManager;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;
import co.id.telkomsigma.palapaone.util.photo.PhotoUtil;
import co.id.telkomsigma.palapaone.util.photo.TakePhotoUtil;

public class ProfileEditActivity extends AppCompatActivity {
    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private static final int CAPTURE_PHOTO = 2;
    private static final int CAPTURE_GALLERY = 3;
    private TakePhotoUtil photoUtil;
    private Bitmap bitmapPhoto;
    private String img_data, mCurrentPhotoPath;
    private PhotoUtil imageUtil;
    private String TAG = "TAG profile";
    private Typeface font, fontbold;
    private ImageView imageView;
    private EditText a3;
    private EditText a5;
    private EditText role;
    private EditText job;
    private EditText about;
    private EditText email;
    private EditText phone;
    private EditText quote;
    private LinearLayout selesai;
    private ProgressBar progressBar;
    private SessionManager session;
    private String fileName;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        session = new SessionManager(getApplicationContext());
        Typeface fontbold = Typeface.createFromAsset(ProfileEditActivity.this.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        Typeface font = Typeface.createFromAsset(ProfileEditActivity.this.getAssets(), "fonts/AvenirLTStd-Book.otf");

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        photoUtil = new TakePhotoUtil();
        imageUtil = new PhotoUtil();

        userID = session.getId() + session.getEvent();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        imageView = findViewById(R.id.iv_profile_edit);

        TextView ax = findViewById(R.id.q);
        TextView a1 = findViewById(R.id.w);
        TextView a2 = findViewById(R.id.e);
        TextView a4 = findViewById(R.id.t);
        TextView a6 = findViewById(R.id.u);
        TextView a7 = findViewById(R.id.o);
        TextView a8 = findViewById(R.id.a);
        //
        ax.setTypeface(fontbold);
        a1.setTypeface(fontbold);
        a2.setTypeface(fontbold);
        a4.setTypeface(fontbold);
        a6.setTypeface(fontbold);
        a7.setTypeface(fontbold);
        a8.setTypeface(fontbold);

        LinearLayout goto_image = (LinearLayout) findViewById(R.id.image);
        goto_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAction();
            }
        });

        a3 = findViewById(R.id.r);
        a5 = findViewById(R.id.y);
        role = findViewById(R.id.i);
        job = findViewById(R.id.p);
        about = findViewById(R.id.s);
        email = findViewById(R.id.f);
        phone = findViewById(R.id.h);
        quote = findViewById(R.id.k);
        //
        a3.setTypeface(fontbold);
        a5.setTypeface(fontbold);
        role.setTypeface(fontbold);
        job.setTypeface(fontbold);
        about.setTypeface(fontbold);
        email.setTypeface(fontbold);
        phone.setTypeface(fontbold);
        quote.setTypeface(fontbold);
        //
        if (session.getPhoto().isEmpty()) {
            imageView.setImageResource(R.drawable.icon_avatars);
        } else {
            downloadImage(getApplicationContext(), session.getPhoto(), imageView);
        }
        a3.setText(session.getName());
        a5.setHint(session.getNationalName());
        role.setHint(session.getRole());
        job.setText(session.getJob());
        about.setText(session.getAbout());
        email.setHint(session.getEmail());
        phone.setHint(session.getPhone());
        quote.setText(session.getQuote());

        selesai = findViewById(R.id.done);
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ProfileEditActivity.this)
                        .setMessage("Are you sure you want to update your profile?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//                                saveEdit(a3.getText().toString(), email.getText().toString(), phone.getText().toString(), about.getText().toString(), quote.getText().toString(),
//                                        job.getText().toString(), session.getOffice(), session.getNationalID(), session.getId());
                                updatePhoto(session.getId());
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Edit Profile");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d(TAG, "Permission callback called-------");
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "all permission granted");
                        // process the normal flow
                        //else any one or both the permissions are not granted
                    } else {
                        Log.d(TAG, "Some permissions are not granted ask again ");
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission
//                        // shouldShowRequestPermissionRationale will return true
                        //show the dialog or snackbar saying its necessary and try again otherwise proceed with setup.
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
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
                            Log.d("yes", "masuk");
                        }
                    }
                }
            }
        }

    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new android.app.AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("SETTINGS", okListener)
                .create()
                .show();
    }

    private void selectAction() {
        final CharSequence[] items = {"Capture Photo", "Choose from Gallery", "Cancel"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ProfileEditActivity.this);
        builder.setTitle("Select Action");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Capture Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
                        // Create the File where the photo should go
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {
                            // Error occurred while creating the File;
                            Log.d("catch", "me");
                        }
                        // Continue only if the File was successfully created
                        if (photoFile != null) {
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                            startActivityForResult(intent, CAPTURE_PHOTO);
                        }
                    }
                } else if (items[item].equals("Choose from Gallery")) {
                    // Create intent to Open Image applications like Gallery, Google Photos
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    // Start the Intent
                    startActivityForResult(galleryIntent, CAPTURE_GALLERY);


                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == CAPTURE_PHOTO) {
                if (bitmapPhoto != null) {
                    bitmapPhoto.recycle();
                }
                setPic();
            } else if (requestCode == CAPTURE_GALLERY) {
                onSelectFromGalleryResult(data);
            }
        } else {
            //Toast.makeText(getActivity().getApplicationContext(), "request code == null", Toast.LENGTH_SHORT).show();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        //String imageFileName = username + "_";
        String imageFileName = userID + "_";
        fileName = imageFileName + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        imageView.setImageBitmap(bm);
    }

    private void setPic() {
        Bitmap scaledBitmap = null;
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        // Decode the image file into a Bitmap sized to fill the View
        int actualHeight = bmOptions.outHeight;
        int actualWidth = bmOptions.outWidth;
        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;
            }
        }

        bmOptions.inSampleSize = imageUtil.calculateInSampleSize(bmOptions, actualWidth, actualHeight);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inDither = false;
        bmOptions.inPurgeable = true;
        bmOptions.inInputShareable = true;
        bmOptions.inTempStorage = new byte[16 * 1024];

        try {
            bmp = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) bmOptions.outWidth;
        float ratioY = actualHeight / (float) bmOptions.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));


        ExifInterface exif;
        try {
            exif = new ExifInterface(mCurrentPhotoPath);

            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bitmapPhoto = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        img_data = photoUtil.getStringBase64Bitmap(scaledBitmap);
        imageView.setImageBitmap(scaledBitmap);
//        img_bg.setImageBitmap(scaledBitmap);
//        img_ic.setVisibility(View.GONE);
    }

    private void saveEdit(final String name, final String email, String phone, final String about, final String quote,
                          final String job, String office, String nation, String user) {
        progressBar.setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(ConstantUtils.LOGIN.TAG_NAME, name);
            jsonObject.put(ConstantUtils.LOGIN.TAG_EMAIL, email);
            jsonObject.put(ConstantUtils.LOGIN.TAG_PHONE, phone);
            jsonObject.put(ConstantUtils.LOGIN.TAG_ABOUT, about);
            jsonObject.put(ConstantUtils.LOGIN.TAG_QUOTE, quote);
            jsonObject.put(ConstantUtils.LOGIN.TAG_JOB, job);
            jsonObject.put(ConstantUtils.LOGIN.TAG_OFFICE, office);
            jsonObject.put(ConstantUtils.LOGIN.TAG_NATIONAL_ID, nation);
            jsonObject.put(ConstantUtils.LOGIN.TAG_USERID, user);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (!img_data.isEmpty()) {
            updatePhoto(user);
        }

        AndroidNetworking.post(ConstantUtils.URL.PROFILE_EDIT)
                .addJSONObjectBody(jsonObject) // posting json
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            if (response.getString("status").equals("1")) {
                                session.updateUser(name, email, about, quote, job);
                                Toast.makeText(ProfileEditActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(ProfileEditActivity.this, "Data not saved", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            Toast.makeText(ProfileEditActivity.this, "Data not saved, please check your connection", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            System.out.println("dua " + e);
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(ProfileEditActivity.this, "Data not saved, please check your connection", Toast.LENGTH_SHORT).show();
                        System.out.println("dua1 " + error);
                    }
                });
    }

    private void updatePhoto(String userID) {
        JSONObject jsontitle = new JSONObject();
        JSONObject json1 = new JSONObject();
        try {
            json1.put(ConstantUtils.UPDATE_PP.TAG_BASE64, img_data);
            json1.put(ConstantUtils.UPDATE_PP.TAG_NAME, fileName);
            json1.put(ConstantUtils.UPDATE_PP.TAG_USER, userID);

            jsontitle.put(ConstantUtils.UPDATE_PP.TAG_TITLE, json1);
        } catch (JSONException e) {
            //Log.d("hasil exception", e.toString());
        }

        progressBar.setVisibility(View.VISIBLE);
        AndroidNetworking.post(ConstantUtils.URL.UPDATE_PP)
                .addJSONObjectBody(jsontitle)
                .setTag("Profile")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString(ConstantUtils.SUBMIT_GALLERY.TAG_MSG).equals("Success")) {
                                progressBar.setVisibility(View.GONE);
                            } else {
                                System.out.println("satu");
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        System.out.println("satu1");
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void downloadImage(Context context, String url, ImageView image) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.icon_avatars)
                .into(image);
    }
}