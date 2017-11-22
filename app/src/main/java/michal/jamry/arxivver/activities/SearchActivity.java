package michal.jamry.arxivver.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivApiQueryBuilder;

/**
 * Activity showing results of searched query.
 */
public class SearchActivity extends AbstractTimelineActivity {

    private ArxivApiQueryBuilder query;

    @Override
    protected ArxivApiQueryBuilder getQuery() {
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
            query = ArxivApiQueryBuilder.aBuilder().withFullSearchQuery(intent.getStringExtra(SearchManager.QUERY));
        }

        super.onCreate(savedInstanceState);
    }
}
