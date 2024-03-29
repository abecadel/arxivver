package michal.jamry.arxivver.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.adapters.listeners.ArxivTimelineAdapterCallbackListener;
import michal.jamry.arxivver.arxiv.ArxivApiQueryBuilder;
import michal.jamry.arxivver.arxiv.ArxivFeed;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.arxiv.ArxivRetrievePublicationsTask;
import michal.jamry.arxivver.models.ArxivTimelineEntryViewHolder;
import michal.jamry.arxivver.persistence.LocalEntriesStorage;

/**
 * The type Arxiv timeline adapter.
 */
public class ArxivTimelineAdapter extends RecyclerView.Adapter<ArxivTimelineEntryViewHolder> {

    private static final int FETCHED_BATCH_SIZE = 25;
    private ArxivApiQueryBuilder query;
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;
    private boolean loading = false;
    private List<ArxivFeedEntry> arxivFeedEntryList = new ArrayList<>();
    private ArxivTimelineAdapterCallbackListener arxivTimelineAdapterCallbackListener;
    private LocalEntriesStorage localEntriesStorage;
    private Map<String, ArxivFeedEntry> storedEntries;

    /**
     * Instantiates a new Arxiv timeline adapter.
     *
     * @param query                                the query
     * @param localEntriesStorage                  the local entries storage
     * @param arxivTimelineAdapterCallbackListener the arxiv timeline adapter callback listener
     */
    public ArxivTimelineAdapter(ArxivApiQueryBuilder query, LocalEntriesStorage localEntriesStorage, ArxivTimelineAdapterCallbackListener arxivTimelineAdapterCallbackListener) {
        this.query = query;
        this.arxivTimelineAdapterCallbackListener = arxivTimelineAdapterCallbackListener;
        this.localEntriesStorage = localEntriesStorage;
        storedEntries = localEntriesStorage.getAll();
        retrieveFeed(query, 0, FETCHED_BATCH_SIZE * 2);
    }

    private void retrieveFeed(String query, int start, int maxResults) {
        ArxivApiQueryBuilder arxivApiQueryBuilder = ArxivApiQueryBuilder
                .aBuilder()
                .withFullSearchQuery(query);

        retrieveFeed(arxivApiQueryBuilder, start, maxResults);
    }

    private void retrieveFeed(ArxivApiQueryBuilder arxivApiQueryBuilder, int start, int maxResults) {
        if (loading) {
            return;
        }
        loading = true;

        String fullquery = arxivApiQueryBuilder
                .withSortBySubmittedDate()
                .withSortOrderDescending()
                .withStart(start)
                .withMaxResults(maxResults)
                .build();

        new TimelineAdapterArxivRetrievePublicationsTask(this).execute(fullquery);
    }

    /**
     * Gets more feed.
     */
    public void getMoreFeed() {
        if (startIndex < totalResults) {
            retrieveFeed(query, startIndex + itemsPerPage, FETCHED_BATCH_SIZE);
        }
    }

    @Override
    public ArxivTimelineEntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.arxiv_timeline_entry_layout, viewGroup, false);

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

    /**
     * Handle data.
     *
     * @param arxivFeed the arxiv feed
     */
    void handleData(ArxivFeed arxivFeed) {
        totalResults = arxivFeed.getTotalResults();
        startIndex = arxivFeed.getStartIndex();
        itemsPerPage = arxivFeed.getItemsPerPage();
        arxivFeedEntryList.addAll(arxivFeed.getEntries());
        notifyDataSetChanged();
        loading = false;
        arxivTimelineAdapterCallbackListener.handleDataRequestComplete();
    }

    /**
     * Handle error.
     *
     * @param e the e
     */
    void handleError(Exception e) {
        Log.d("AdapterError", "Error downloading feed");
        arxivTimelineAdapterCallbackListener.handleErrors("Error retrieving feed", e);
        loading = false;
    }

    private static class TimelineAdapterArxivRetrievePublicationsTask extends ArxivRetrievePublicationsTask {
        private WeakReference<ArxivTimelineAdapter> adapterWeakReference;

        /**
         * Instantiates a new Timeline adapter arxiv retrieve publications task.
         *
         * @param adapter the adapter
         */
        public TimelineAdapterArxivRetrievePublicationsTask(ArxivTimelineAdapter adapter) {
            this.adapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        protected void onPostExecute(ArxivFeed arxivFeed) {
            if (arxivFeed != null) {
                adapterWeakReference.get().handleData(arxivFeed);
            } else {
                adapterWeakReference.get().handleError(exception);
            }
        }
    }
}

