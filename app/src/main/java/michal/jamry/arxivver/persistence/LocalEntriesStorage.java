package michal.jamry.arxivver.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import michal.jamry.arxivver.arxiv.ArxivFeedEntry;

@Singleton
public class LocalEntriesStorage {

    private Map<String, ArxivFeedEntry> storage = new HashMap<>();

    public boolean checkedIfStored(String id) {
        return storage.containsKey(id);
    }

    public void store(ArxivFeedEntry arxivFeedEntry) {
        storage.put(arxivFeedEntry.getId(), arxivFeedEntry);
    }

    public ArxivFeedEntry get(String id) {
        return storage.get(id);
    }

    public void removeFromStorage(String id) {
        storage.remove(id);
    }

    public List<ArxivFeedEntry> getAll() {
        List<ArxivFeedEntry> list = new ArrayList<>(storage.values());
        Collections.sort(list, (arxivFeedEntry, other) -> other.getPublished().compareTo(arxivFeedEntry.getPublished()));
        Collections.reverse(list);

        return list;
    }
}
