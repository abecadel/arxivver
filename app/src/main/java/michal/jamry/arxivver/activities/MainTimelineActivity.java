package michal.jamry.arxivver.activities;


import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivApiQueryBuilder;
import michal.jamry.arxivver.configuration.MainTimelineConfiguration;

/**
 * Main timeline of publications, chosen by the query constructed on ChoosingActivity. It allows us to "star" a publication or see the details of a specific publication.
 */
public class MainTimelineActivity extends AbstractTimelineActivity {

    @Override
    protected ArxivApiQueryBuilder getQuery() {
        MainTimelineConfiguration mainTimelineConfiguration = new MainTimelineConfiguration(getBaseContext());
        ArxivApiQueryBuilder arxivApiQueryBuilder = ArxivApiQueryBuilder.aBuilder();
        List<String> params = mainTimelineConfiguration.getQueryParts();

        for (int i = 0; i < params.size(); i++) {
            if (i > 0) {
                arxivApiQueryBuilder.or();
            }

            arxivApiQueryBuilder.withSearchQueryParam(params.get(i));
        }

        return arxivApiQueryBuilder;
    }

    @Override
    protected int getLayout() {
        return R.layout.arxiv_timeline_layout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_timeline_search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        ComponentName componentName = new ComponentName(this, SearchActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.choosingButton:
                startActivity(new Intent(this, ChoosingActivity.class));
                finish();
                return true;
            case R.id.starredButton:
                startActivity(new Intent(this, StarredActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
