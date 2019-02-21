package com.simonescanzani.scanzoseat;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.StringRes;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static androidx.core.content.ContextCompat.getSystemService;

public class Utilities {
    public static boolean doLogin(Context context, String email, String password, int LEN_PASS){
        if(!isEmailValid(email)) {
            return false;
        }
        if(!isPasswordValid(password, LEN_PASS)){
            return false;
        }

        return true;
    }


    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isNumberValid(CharSequence number){
        return Patterns.PHONE.matcher(number).matches();
    }

    public static boolean isUserNameValid(CharSequence username){
        return username.length()>6;
    }

    public static boolean isPasswordValid(CharSequence pass, int LEN_PASS){
        return pass.length()>LEN_PASS;
    }

    protected static void showToast(Context context, @StringRes int resId){
        Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show();
    }

    public static int calculateNoOfColumnsWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int columnCount = (int) (dpWidth / scalingFactor);
        return (columnCount>=2?columnCount:2); // if column no. is less than 2, we still display 2 columns
    }

    public static int calculateNoOfColumnsHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        int scalingFactor = 200;
        int columnCount = (int) (dpHeight / scalingFactor);
        return (columnCount>=2?columnCount:2); // if column no. is less than 2, we still display 2 columns
    }

    public static void hideKeyboard(Activity activity, View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
