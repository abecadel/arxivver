package michal.jamry.arxivver.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.adapters.ArxivTimelineAdapter;
import michal.jamry.arxivver.adapters.listeners.ArxivTimelineAdapterCallbackListener;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.persistence.LocalEntriesStorage;


/**
 * The type Abstract timeline activity.
 */
public abstract class AbstractTimelineActivity extends AppCompatActivity {

    /**
     * The constant ARXIV_TIMELINE_ACTIVITY_STORAGE.
     */
    public static final String ARXIV_TIMELINE_ACTIVITY_STORAGE = "MainTimelineStorage";
    /**
     * The constant POSITION.
     */
    public static final String POSITION = "POSITION";
    private ArxivTimelineAdapter arxivTimelineAdapter;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private int lastScrollPosition = 0;

    /**
     * Gets query.
     *
     * @return the query
     */
    protected abstract String getQuery();

    /**
     * Gets layout.
     *
     * @return the layout
     */
    protected abstract int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        sharedPreferences = getSharedPreferences(ARXIV_TIMELINE_ACTIVITY_STORAGE, 0);
        lastScrollPosition = sharedPreferences.getInt(POSITION, 0);
        recyclerView = findViewById(R.id.recycler_view);


        //setup recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        //setup adapter
        ArxivTimelineAdapterCallbackListener arxivTimelineAdapterCallbackListener = new ArxivTimelineAdapterCallbackListener() {
            @Override
            public void handleEntryLinkClicked(ArxivFeedEntry arxivFeedEntry) {
                openEntry(arxivFeedEntry);
            }

            @Override
            public void handleDataRequestComplete() {
                scrollToPreviousPosition();
            }

            @Override
            public void handleErrors(String error, Exception e) {
                handleAdapterError(error, e);
            }
        };
        arxivTimelineAdapter = new ArxivTimelineAdapter(getQuery(), new LocalEntriesStorage(getApplicationContext()), arxivTimelineAdapterCallbackListener);
        recyclerView.setAdapter(arxivTimelineAdapter);


        //infinity scroll
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        arxivTimelineAdapter.getMoreFeed();
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        int lastFirstVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        sharedPreferences.edit().putInt(POSITION, lastFirstVisiblePosition).apply();
    }

    private void openEntry(ArxivFeedEntry arxivFeedEntry) {
        Intent intent = new Intent(this, PublicationActivity.class);
        intent.putExtra(PublicationActivity.ARXIV_FEED_ENTRY_TYPE_OBJ, arxivFeedEntry);
        startActivity(intent);
    }

    private void scrollToPreviousPosition() {
        if (lastScrollPosition != 0) {
            ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPosition(lastScrollPosition);
        }
    }

    private void handleAdapterError(String error, Exception e) {
        Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT);
    }
}
