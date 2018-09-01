package co.id.telkomsigma.palapaone.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import co.id.telkomsigma.palapaone.util.connection.ConstantUtils;

public class DictionaryManager {
    private static final String PREFER_NAME = "PalapaOne";
    private static final String IS_LOGIN = "IsLoggedIn";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    public DictionaryManager(Context context) {
        this._context = context;

        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLanguageMain(String uname, String pwd, String login, String msg, String menu1, String menu2, String menu3, String menu4, String menu5
            , String menu6, String menu7, String menu8, String menu9) {
        editor.putString(ConstantUtils.DICTIONARY.TAG_USERNAME, uname);
        editor.putString(ConstantUtils.DICTIONARY.TAG_PASSWORD, pwd);
        editor.putString(ConstantUtils.DICTIONARY.TAG_LOGIN, login);
        editor.putString(ConstantUtils.DICTIONARY.TAG_MSG, msg);
        editor.putString(ConstantUtils.DICTIONARY.TAG_MENU_1, menu1);
        editor.putString(ConstantUtils.DICTIONARY.TAG_MENU_2, menu2);
        editor.putString(ConstantUtils.DICTIONARY.TAG_MENU_3, menu3);
        editor.putString(ConstantUtils.DICTIONARY.TAG_MENU_4, menu4);
        editor.putString(ConstantUtils.DICTIONARY.TAG_MENU_5, menu5);
        editor.putString(ConstantUtils.DICTIONARY.TAG_MENU_6, menu6);
        editor.putString(ConstantUtils.DICTIONARY.TAG_MENU_7, menu7);
        editor.putString(ConstantUtils.DICTIONARY.TAG_MENU_8, menu8);
        editor.putString(ConstantUtils.DICTIONARY.TAG_MENU_9, menu9);
        // commit changes
        editor.commit();
    }

    public String getDictUname() {
        return pref.getString(ConstantUtils.DICTIONARY.TAG_USERNAME, "");
    }

    public String getDictPwd() {
        return pref.getString(ConstantUtils.DICTIONARY.TAG_PASSWORD, "");
    }

    public String getDictLogin() {
        return pref.getString(ConstantUtils.DICTIONARY.TAG_LOGIN, "");
    }

    public HashMap<String, String> getDictHome() {
        HashMap<String, String> home = new HashMap<String, String>();
        home.put(ConstantUtils.DICTIONARY.TAG_MSG, pref.getString(ConstantUtils.DICTIONARY.TAG_MSG, null));
        home.put(ConstantUtils.DICTIONARY.TAG_MENU_1, pref.getString(ConstantUtils.DICTIONARY.TAG_MENU_1, null));
        home.put(ConstantUtils.DICTIONARY.TAG_MENU_2, pref.getString(ConstantUtils.DICTIONARY.TAG_MENU_2, null));
        home.put(ConstantUtils.DICTIONARY.TAG_MENU_3, pref.getString(ConstantUtils.DICTIONARY.TAG_MENU_3, null));
        home.put(ConstantUtils.DICTIONARY.TAG_MENU_4, pref.getString(ConstantUtils.DICTIONARY.TAG_MENU_4, null));
        home.put(ConstantUtils.DICTIONARY.TAG_MENU_5, pref.getString(ConstantUtils.DICTIONARY.TAG_MENU_5, null));
        home.put(ConstantUtils.DICTIONARY.TAG_MENU_6, pref.getString(ConstantUtils.DICTIONARY.TAG_MENU_6, null));
        home.put(ConstantUtils.DICTIONARY.TAG_MENU_7, pref.getString(ConstantUtils.DICTIONARY.TAG_MENU_7, null));
        home.put(ConstantUtils.DICTIONARY.TAG_MENU_8, pref.getString(ConstantUtils.DICTIONARY.TAG_MENU_8, null));
        home.put(ConstantUtils.DICTIONARY.TAG_MENU_9, pref.getString(ConstantUtils.DICTIONARY.TAG_MENU_9, null));
        // return user
        return home;
    }
}
