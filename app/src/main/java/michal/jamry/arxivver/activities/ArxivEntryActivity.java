package michal.jamry.arxivver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ToggleButton;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.persistence.LocalEntriesStorage;

import static michal.jamry.arxivver.models.ModelUtils.prepareCategories;
import static michal.jamry.arxivver.models.ModelUtils.prepareDate;
import static michal.jamry.arxivver.models.ModelUtils.prepareLongAuthorsList;
import static michal.jamry.arxivver.models.ModelUtils.removeNewlines;

public class ArxivEntryActivity extends AppCompatActivity {

    public static final String ARXIV_FEED_ENTRY_TYPE_OBJ = "michal.jamry.arxivver.activities.ArxivEntryActivity.ARXIV_FEED_ENTRY_TYPE_OBJ";
    private ArxivFeedEntry arxivFeedEntry;

    LocalEntriesStorage localEntriesStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arxiv_entry_page_layout);
        localEntriesStorage = new LocalEntriesStorage(getApplicationContext());

        Intent intent = getIntent();
        arxivFeedEntry = (ArxivFeedEntry) intent.getSerializableExtra(ARXIV_FEED_ENTRY_TYPE_OBJ);

        TextView entryTitle = findViewById(R.id.titleEntryPage);
        entryTitle.setText(removeNewlines(arxivFeedEntry.getTitle().trim()));

        TextView summary = findViewById(R.id.summaryEntryPage);
        summary.setText(removeNewlines(arxivFeedEntry.getSummary().trim()));

        TextView date = findViewById(R.id.publishedDateEntryPage);
        date.setText(prepareDate(arxivFeedEntry.getPublished()));

        TextView authors = findViewById(R.id.authorsEntryPage);
        authors.setText(prepareLongAuthorsList(arxivFeedEntry.getAuthorList()));

        ToggleButton toggleButton = findViewById(R.id.entryActivityToggleButton);
        toggleButton.setChecked(localEntriesStorage.checkedIfStored(arxivFeedEntry.getId()));
        toggleButton.setOnCheckedChangeListener((compoundButton, state) -> {
            if (state) {
                localEntriesStorage.store(arxivFeedEntry);
            } else {
                localEntriesStorage.removeFromStorage(arxivFeedEntry.getId());
            }
        });

        TextView categories = findViewById(R.id.categoriesEntryPage);
        categories.setText(prepareCategories(arxivFeedEntry.getPrimaryCategory(), arxivFeedEntry.getCategories()));
    }

}
