package michal.jamry.arxivver.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

public class TimelineSearchActivity extends AbstractTimelineActivity {

    private String query;

    @Override
    protected String getQuery() {
        return query;
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
