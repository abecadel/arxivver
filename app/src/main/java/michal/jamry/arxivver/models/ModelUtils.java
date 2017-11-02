package michal.jamry.arxivver.models;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class ModelUtils {
    private static final int TWEET_LENGTH = 288;
    private static final DateFormat dateFormat = DateFormat.getDateInstance();

    public static String elipsis(String txt) {
        if (txt == null) {
            return null;
        }

        if (txt.length() > TWEET_LENGTH) {
            return txt.substring(0, TWEET_LENGTH).trim() + "...";
        } else {
            return txt;
        }
    }

    public static String removeNewlines(String txt) {
        return txt.replaceAll("\n", " ");
    }

    public static String prepareTxt(String txt) {
        if (txt == null) {
            return null;
        }

        return removeNewlines(elipsis(txt.trim()));
    }

    public static String prepareDate(Date date) {
        return dateFormat.format(date);
    }

    //https://stackoverflow.com/a/45727769/1062744
    public static void makeLinks(TextView textView, String[] links, ClickableSpan[] clickableSpans) {
        SpannableString spannableString = new SpannableString(textView.getText());
        for (int i = 0; i < links.length; i++) {
            ClickableSpan clickableSpan = clickableSpans[i];
            String link = links[i];

            int startIndexOfLink = textView.getText().toString().indexOf(link);
            spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + link.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }
}
