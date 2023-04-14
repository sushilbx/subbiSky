package com.webdigital.subbisky.utils;

import android.widget.EditText;
import android.widget.Spinner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static final String MOBILE_PATTERN = "[0-9]{10}";
    public static final String PHONE_PATTERN="[0-9]{11}";
    public static final String LAND_LINE_PATTERN = "[0-9]{11}";
    public static final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
    public static Pattern pattern;
    public static Matcher matcher;
    public static final String VEHICLE_NO_PATTERN = "[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)?[0-9]{4}";

    public static boolean isEmpty(EditText editText)
    {
        // TODO method to check edit text is fill or no
        // return true when edit text is empty
        return editText.getText().toString().trim().equals("");
    }

    public static boolean isDotOnly(EditText editText)
    {
        // TODO method to check edit text is fill or no
        if (editText.getText().toString().charAt(0) == '.')
        {
            return true;
        }

        return editText.getText().toString().length() == 1
                && editText.getText().toString().charAt(0) == '.';
    }


    public static boolean isEmailId(EditText editText) {
        // method to check edit text is fill or no
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(editText.getText().toString().trim());
        return !matcher.matches();

    }

    public static boolean isConfirPassWord(EditText edtPassword,
                                           EditText edtConfirPassword) {
        return edtPassword.getText().toString().trim()
                .equals(edtConfirPassword.getText().toString().trim());
    }



    public static boolean isContactNo(EditText editText)
    {
        if (editText.getText().toString().length() != 0
                && editText.getText().toString().length() != 10) {
            return true;
        }
        return editText.getText().toString().length() > 10;
    }
    public static boolean isEmptySpinner(Spinner sp)
    {
        // TODO method to check edit text is fill or no
        // return true when edit text is empty
        return sp.getSelectedItem().toString().trim().equals("");
    }

}




