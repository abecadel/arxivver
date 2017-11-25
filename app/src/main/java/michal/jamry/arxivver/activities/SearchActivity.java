package michal.jamry.arxivver.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
            String orgQuery = intent.getStringExtra(SearchManager.QUERY);
            query = ArxivApiQueryBuilder.aBuilder().withFullSearchQuery(orgQuery);
            ((AppCompatActivity) this).getSupportActionBar().setTitle("Search: " + orgQuery);
        }

        super.onCreate(savedInstanceState);
    }
}
