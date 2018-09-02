package co.id.telkomsigma.palapaone.util;

/**
 * Created by Gilang on 12-Apr-16.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import co.id.telkomsigma.palapaone.controller.main.Splashscreen;
import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;


public class SessionManager {

    private static final String PREFER_NAME = "PalapaOne";
    private static final String IS_LOGIN = "IsLoggedIn";
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this._context = context;

        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setUserSession(String id, String username, String name, String email, String phone, String about,
                               String quote, String job, String office, String role, String eventID, String event, String parent, String nas_id, String nas_name) {

        editor.putBoolean(IS_LOGIN, true);
        editor.putBoolean("notifStatus", true);
        editor.putString(ConstantUtils.LOGIN.TAG_USERID, id);
        editor.putString(ConstantUtils.LOGIN.TAG_USERNAME, username);
        editor.putString(ConstantUtils.LOGIN.TAG_NAME, name);
        editor.putString(ConstantUtils.LOGIN.TAG_EMAIL, email);
        editor.putString(ConstantUtils.LOGIN.TAG_PHONE, phone);
        editor.putString(ConstantUtils.LOGIN.TAG_ABOUT, about);
        editor.putString(ConstantUtils.LOGIN.TAG_QUOTE, quote);
        editor.putString(ConstantUtils.LOGIN.TAG_JOB, job);
        editor.putString(ConstantUtils.LOGIN.TAG_OFFICE, office);
        editor.putString(ConstantUtils.LOGIN.TAG_ROLE, role);
        editor.putString(ConstantUtils.LOGIN.TAG_EVENTID, eventID);
        editor.putString(ConstantUtils.LOGIN.TAG_EVENT, event);
        editor.putString(ConstantUtils.LOGIN.TAG_PARENT, parent);
        editor.putString(ConstantUtils.LOGIN.TAG_NATIONAL_ID, nas_id);
        editor.putString(ConstantUtils.LOGIN.TAG_NATIONAL_NAME, nas_name);
        // commit changes
        editor.commit();
    }


    public void updateUser(String name, String email, String about,String quote, String job) {

        editor.putString(ConstantUtils.LOGIN.TAG_NAME, name);
        editor.putString(ConstantUtils.LOGIN.TAG_EMAIL, email);
        editor.putString(ConstantUtils.LOGIN.TAG_ABOUT, about);
        editor.putString(ConstantUtils.LOGIN.TAG_QUOTE, quote);
        editor.putString(ConstantUtils.LOGIN.TAG_JOB, job);
        // commit changes
        editor.commit();
    }

    public String getId() {
        return pref.getString(ConstantUtils.LOGIN.TAG_USERID, "");
    }

    public String getUsername() {
        return pref.getString(ConstantUtils.LOGIN.TAG_USERNAME, "");
    }

    public String getName() {
        return pref.getString(ConstantUtils.LOGIN.TAG_NAME, "");
    }

    public String getEmail() {
        return pref.getString(ConstantUtils.LOGIN.TAG_EMAIL, "");
    }

    public String getPhone() {
        return pref.getString(ConstantUtils.LOGIN.TAG_PHONE, "");
    }

    public String getAbout() {
        return pref.getString(ConstantUtils.LOGIN.TAG_ABOUT, "");
    }

    public String getQuote() {
        return pref.getString(ConstantUtils.LOGIN.TAG_QUOTE, "");
    }

    public String getJob() {
        return pref.getString(ConstantUtils.LOGIN.TAG_JOB, "");
    }

    public String getOffice() {
        return pref.getString(ConstantUtils.LOGIN.TAG_OFFICE, "");
    }

    public String getRole() {
        return pref.getString(ConstantUtils.LOGIN.TAG_ROLE, "");
    }

    public String getEventID() {
        return pref.getString(ConstantUtils.LOGIN.TAG_EVENTID, "");
    }
    public String getEvent() {
        return pref.getString(ConstantUtils.LOGIN.TAG_EVENT, "2");
    }
    public String getParentID() {
        return pref.getString(ConstantUtils.LOGIN.TAG_PARENT, "1");
    }
    public String getNationalID() {
        return pref.getString(ConstantUtils.LOGIN.TAG_NATIONAL_ID, "");
    }
    public String getNationalName() {
        return pref.getString(ConstantUtils.LOGIN.TAG_NATIONAL_NAME, "");
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Splashscreen.class);
        i.setAction("com.package.ACTION_LOGOUT");
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(i);
    }

    public boolean isLogin() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
