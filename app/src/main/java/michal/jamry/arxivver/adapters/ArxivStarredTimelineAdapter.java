package michal.jamry.arxivver.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.adapters.listeners.ArxivTimelineAdapterCallbackListener;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.models.ArxivTimelineEntryViewHolder;
import michal.jamry.arxivver.persistence.LocalEntriesStorage;

/**
 * The type Arxiv starred timeline adapter.
 */
public class ArxivStarredTimelineAdapter extends RecyclerView.Adapter<ArxivTimelineEntryViewHolder> {

    private ArxivTimelineAdapterCallbackListener arxivTimelineAdapterCallbackListener;
    private LocalEntriesStorage localEntriesStorage;
    private List<ArxivFeedEntry> arxivFeedEntryList = new ArrayList<>();
    private Map<String, ArxivFeedEntry> storedEntries;

    /**
     * Instantiates a new Arxiv starred timeline adapter.
     *
     * @param localEntriesStorage                  the local entries storage
     * @param arxivTimelineAdapterCallbackListener the arxiv timeline adapter callback listener
     */
    public ArxivStarredTimelineAdapter(LocalEntriesStorage localEntriesStorage, ArxivTimelineAdapterCallbackListener arxivTimelineAdapterCallbackListener) {
        this.arxivTimelineAdapterCallbackListener = arxivTimelineAdapterCallbackListener;
        this.localEntriesStorage = localEntriesStorage;
        this.storedEntries = localEntriesStorage.getAll();
        this.arxivFeedEntryList.addAll(storedEntries.values());
    }

    @Override
    public ArxivTimelineEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.arxiv_timeline_entry_layout, parent, false);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                //get clicked item feed entry
                RecyclerView recyclerView = (RecyclerView) view.getParent().getParent();
                int pos = recyclerView.getChildLayoutPosition((View) view.getParent());
                ArxivFeedEntry arxivFeedEntry = arxivFeedEntryList.get(pos);
                arxivTimelineAdapterCallbackListener.handleEntryLinkClicked(arxivFeedEntry);
            }
        };

        return new ArxivTimelineEntryViewHolder(view, clickableSpan);
    }

    @Override
    public void onBindViewHolder(ArxivTimelineEntryViewHolder holder, int position) {
        ArxivFeedEntry arxivFeedEntry = arxivFeedEntryList.get(position);
        holder.bind(arxivFeedEntry, storedEntries.containsKey(arxivFeedEntry.getId()));
    }

    @Override
    public int getItemCount() {
        return arxivFeedEntryList.size();
    }
}
