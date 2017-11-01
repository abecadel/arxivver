package michal.jamry.arxivver.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivApiQueryBuilder;
import michal.jamry.arxivver.arxiv.ArxivFeed;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.arxiv.ArxivRetrievePublicationsTask;

public class MainTimelineActivity extends AppCompatActivity {

    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        list.setAdapter(adapter);
    }

    public void onButtonClick(View view) {
        loadPage();
    }

    public void addItem(String item) {
        arrayList.add(item);
        adapter.notifyDataSetChanged();
    }

    public void handleError(Exception e) {

    }

    public void loadPage() {
        String query = ArxivApiQueryBuilder
                .aBuilder()
                .withSearchQuery("LSTM")
                .withMaxResults(100)
                .build();

        new MainTimelineArxivRetrievePublicationsTask(this)
                .execute(query);
    }

    private static class MainTimelineArxivRetrievePublicationsTask extends ArxivRetrievePublicationsTask {
        private WeakReference<MainTimelineActivity> activity;

        public MainTimelineArxivRetrievePublicationsTask(MainTimelineActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        protected void onPostExecute(ArxivFeed arxivFeed) {
            if (arxivFeed != null) {
                for (ArxivFeedEntry arxivFeedEntry : arxivFeed.getEntries()) {
                    activity.get().addItem(arxivFeedEntry.getTitle());
                }
            } else {
                activity.get().handleError(exception);
            }
        }
    }

}
