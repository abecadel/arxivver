package michal.jamry.arxivver.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import michal.jamry.arxivver.arxiv.ArxivFeedEntry;

import static android.content.Context.MODE_PRIVATE;

public class LocalEntriesStorage {
    private Gson gson = new Gson();
    private SharedPreferences sharedPreferences;

    public LocalEntriesStorage(Context context) {
        sharedPreferences = context.getSharedPreferences("LOCAL_ENTRIES_STORAGE", MODE_PRIVATE);
    }

    public boolean checkedIfStored(String id) {
        return sharedPreferences.contains(id);
    }

    public void store(ArxivFeedEntry arxivFeedEntry) {
        if (checkedIfStored(arxivFeedEntry.getId())) {
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(arxivFeedEntry.getId(), gson.toJson(arxivFeedEntry));
        editor.apply();
    }

    public ArxivFeedEntry get(String id) {
        String serializedEntry = sharedPreferences.getString(id, null);
        if (serializedEntry == null) {
            return null;
        }

        return gson.fromJson(serializedEntry, ArxivFeedEntry.class);
    }

    public void removeFromStorage(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(id);
        editor.apply();
    }

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
