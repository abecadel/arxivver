package michal.jamry.arxivver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.adapters.ArxivTimelineAdapter;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.persistence.LocalEntriesStorage;

public class ArxivTimelineActivity extends AppCompatActivity {

    private ArxivTimelineAdapter arxivTimelineAdapter;
    private RecyclerView recyclerView;
    private LocalEntriesStorage localEntriesStorage;

    private void openEntry(ArxivFeedEntry arxivFeedEntry) {
        Log.d("Clicked", arxivFeedEntry.toString());
        Intent intent = new Intent(this, ArxivEntryActivity.class);
        intent.putExtra(ArxivEntryActivity.ARXIV_FEED_ENTRY_TYPE_OBJ, arxivFeedEntry);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arxiv_timeline_layout);
        localEntriesStorage = new LocalEntriesStorage(getApplicationContext());

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        arxivTimelineAdapter = new ArxivTimelineAdapter("LSTM", localEntriesStorage, this::openEntry);

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
}
