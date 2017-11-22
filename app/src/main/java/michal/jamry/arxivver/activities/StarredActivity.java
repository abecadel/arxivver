package michal.jamry.arxivver.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.adapters.ArxivStarredTimelineAdapter;
import michal.jamry.arxivver.adapters.listeners.ArxivTimelineAdapterCallbackListener;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.persistence.LocalEntriesStorage;

public class StarredActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArxivStarredTimelineAdapter arxivStarredTimelineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starred);
        recyclerView = findViewById(R.id.recycler_view_starred);

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
            public void handleErrors(String error, Exception e) {
                handleAdapterError(error, e);
            }
        };

        arxivStarredTimelineAdapter = new ArxivStarredTimelineAdapter(new LocalEntriesStorage(getApplicationContext()), arxivTimelineAdapterCallbackListener);
        recyclerView.setAdapter(arxivStarredTimelineAdapter);
    }

    private void openEntry(ArxivFeedEntry arxivFeedEntry) {
        Intent intent = new Intent(this, PublicationActivity.class);
        intent.putExtra(PublicationActivity.ARXIV_FEED_ENTRY_TYPE_OBJ, arxivFeedEntry);
        startActivity(intent);
    }

    private void handleAdapterError(String error, Exception e) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
