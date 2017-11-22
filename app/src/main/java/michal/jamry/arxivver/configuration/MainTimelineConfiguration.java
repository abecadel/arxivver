package michal.jamry.arxivver.configuration;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Storage of selected query params, selected by the user.
 */
public class MainTimelineConfiguration {

    private static final String STORAGE_NAME = "MAIN_TIMELINE_STORAGE";
    private static final String KEY_NAME = "CHOSEN_QUERY_PARAMS";
    private SharedPreferences sharedPreferences;

    /**
     * Instantiates a new Main timeline configuration.
     *
     * @param context the context
     */
    public MainTimelineConfiguration(Context context) {
        sharedPreferences = context.getSharedPreferences(STORAGE_NAME, MODE_PRIVATE);
    }

    /**
     * Is already configured boolean.
     *
     * @return the boolean
     */
    public boolean isAlreadyConfigured() {
        if (sharedPreferences.contains(KEY_NAME)) {
            String val = sharedPreferences.getString(KEY_NAME, null);
            if (val != null && val.length() > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets query parts.
     *
     * @return the query parts
     */
    public List<String> getQueryParts() {
        String val = sharedPreferences.getString(KEY_NAME, null);
        if (val != null) {
            List<String> ret = new ArrayList<>();
            ret.addAll(Arrays.asList(val.split(";")));
            return ret;
        }

        return null;
    }

    /**
     * Sets query parts.
     *
     * @param queryParts the query parts
     */
    public void setQueryParts(List<String> queryParts) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < queryParts.size(); i++) {
            if (i > 0) {
                stringBuilder.append(";");
            }

            stringBuilder.append(queryParts.get(i));
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, stringBuilder.toString());
        editor.apply();
    }
}
