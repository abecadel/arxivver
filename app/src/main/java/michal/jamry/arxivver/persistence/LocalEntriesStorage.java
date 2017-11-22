package michal.jamry.arxivver.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import michal.jamry.arxivver.arxiv.ArxivFeedEntry;

import static android.content.Context.MODE_PRIVATE;

/**
 * The type Local entries storage.
 */
public class LocalEntriesStorage {
    private Gson gson = new Gson();
    private SharedPreferences sharedPreferences;

    /**
     * Instantiates a new Local entries storage.
     *
     * @param context the context
     */
    public LocalEntriesStorage(Context context) {
        sharedPreferences = context.getSharedPreferences("LOCAL_ENTRIES_STORAGE", MODE_PRIVATE);
    }

    /**
     * Checked if stored boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean checkedIfStored(String id) {
        return sharedPreferences.contains(id);
    }

    /**
     * Store.
     *
     * @param arxivFeedEntry the arxiv feed entry
     */
    public void store(ArxivFeedEntry arxivFeedEntry) {
        if (checkedIfStored(arxivFeedEntry.getId())) {
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(arxivFeedEntry.getId(), gson.toJson(arxivFeedEntry));
        editor.apply();
    }

    /**
     * Get arxiv feed entry.
     *
     * @param id the id
     * @return the arxiv feed entry
     */
    public ArxivFeedEntry get(String id) {
        String serializedEntry = sharedPreferences.getString(id, null);
        if (serializedEntry == null) {
            return null;
        }

        return gson.fromJson(serializedEntry, ArxivFeedEntry.class);
    }

    /**
     * Remove from storage.
     *
     * @param id the id
     */
    public void removeFromStorage(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(id);
        editor.apply();
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public Map<String, ArxivFeedEntry> getAll() {
        Map<String, ArxivFeedEntry> stringArxivFeedMap = new HashMap<>();
        for (Map.Entry entry : sharedPreferences.getAll().entrySet()) {
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();

            stringArxivFeedMap.put(key, gson.fromJson(val, ArxivFeedEntry.class));
        }

        return stringArxivFeedMap;
    }
}
