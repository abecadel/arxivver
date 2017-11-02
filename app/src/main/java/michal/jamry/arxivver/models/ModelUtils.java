package michal.jamry.arxivver.models;

public class ModelUtils {
    private static final int TWEET_LENGTH = 288;

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
}
