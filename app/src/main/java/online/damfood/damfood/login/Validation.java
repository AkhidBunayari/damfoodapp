package online.damfood.damfood.login;

import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Validation {

    // Regular Expression
    // you can change the expression based on your need
    private static final String NIM_REGEX = "\\d{2}.\\d{2}.\\d{4}{10,10}";

    // Error Messages
    private static final String REQUIRED_MSG = "required | ##.##.#####";
    private static final String NIM_MSG = "##.##.#####";

    // call this method when you need to check nim validation
    public static boolean isNIM(EditText etNIM, boolean required) {
        return isValid(etNIM, NIM_REGEX, NIM_MSG, required);
    }

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(EditText etNIM, String regex, String errMsg, boolean required) {

        String text = etNIM.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        etNIM.setError(null);

        // text required and editText is blank, so return false
        if ( required && !hasText(etNIM) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            etNIM.setError(errMsg);
            return false;
        };

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText etNIM) {

        String text = etNIM.getText().toString().trim();
        etNIM.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            etNIM.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
}