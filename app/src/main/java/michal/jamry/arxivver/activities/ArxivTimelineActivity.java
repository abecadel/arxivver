package michal.jamry.arxivver.activities;


import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import michal.jamry.arxivver.R;

public class ArxivTimelineActivity extends AbstractTimelineActivity {
    @Override
    protected String getQuery() {
        return "LSTM";
    }

    @Override
    protected int getLayout() {
        return R.layout.arxiv_timeline_layout;
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
}
