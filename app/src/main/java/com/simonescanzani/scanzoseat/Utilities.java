package com.simonescanzani.scanzoseat;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.Patterns;
import android.widget.Toast;

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
}
