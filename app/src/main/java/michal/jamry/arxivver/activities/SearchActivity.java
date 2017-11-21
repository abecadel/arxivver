package michal.jamry.arxivver.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import michal.jamry.arxivver.R;

public class SearchActivity extends AbstractTimelineActivity {

    private String query;

    @Override
    protected String getQuery() {
        return query;
    }

    @Override
    protected int getLayout() {
        return R.layout.arxiv_timeline_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //retrieve search query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
        }

        super.onCreate(savedInstanceState);
    }
}
