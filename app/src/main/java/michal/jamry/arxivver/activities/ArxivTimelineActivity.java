package michal.jamry.arxivver.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.adapters.ArxivTimelineAdapter;
import michal.jamry.arxivver.adapters.EntryTitleClickedListener;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;

public class ArxivTimelineActivity extends AppCompatActivity {

    private ArxivTimelineAdapter arxivTimelineAdapter;
    private RecyclerView recyclerView;

    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arxiv_timeline_layout);

        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        EntryTitleClickedListener entryTitleClickedListener = new EntryTitleClickedListener() {
            @Override
            public void onClick(ArxivFeedEntry arxivFeedEntry) {
                Log.d("Clicked", arxivFeedEntry.toString());
            }
        };
        arxivTimelineAdapter = new ArxivTimelineAdapter("LSTM", entryTitleClickedListener);

        recyclerView.setAdapter(arxivTimelineAdapter);

        //infinity scroll
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        arxivTimelineAdapter.getMoreFeed();
                    }
                }
            }
        });

    }
}
