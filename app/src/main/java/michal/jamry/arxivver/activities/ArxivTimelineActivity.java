package michal.jamry.arxivver.activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.adapters.ArxivTimelineAdapter;
import michal.jamry.arxivver.adapters.listeners.ArxivTimelineAdapterCallbackListener;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.persistence.LocalEntriesStorage;

public class ArxivTimelineActivity extends AppCompatActivity {

    public static final String ARXIV_TIMELINE_ACTIVITY_STORAGE = "MainTimelineStorage";
    private static final String POSITION = "POSITION";
    private ArxivTimelineAdapter arxivTimelineAdapter;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private int lastScrollPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arxiv_timeline_layout);
        sharedPreferences = getSharedPreferences(ARXIV_TIMELINE_ACTIVITY_STORAGE, 0);
        lastScrollPosition = sharedPreferences.getInt(POSITION, 0);

        recyclerView = findViewById(R.id.recycler_view);
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
        };
        arxivTimelineAdapter = new ArxivTimelineAdapter("LSTM", new LocalEntriesStorage(getApplicationContext()), arxivTimelineAdapterCallbackListener);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this, TimelineSearchActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        int lastFirstVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        sharedPreferences.edit().putInt(POSITION, lastFirstVisiblePosition).apply();
    }

    private void openEntry(ArxivFeedEntry arxivFeedEntry) {
        Intent intent = new Intent(this, ArxivEntryActivity.class);
        intent.putExtra(ArxivEntryActivity.ARXIV_FEED_ENTRY_TYPE_OBJ, arxivFeedEntry);
        startActivity(intent);
    }

    private void scrollToPreviousPosition() {
        if (lastScrollPosition != 0) {
            ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPosition(lastScrollPosition);
        }
    }
}
