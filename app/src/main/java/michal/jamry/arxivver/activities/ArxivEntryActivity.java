package michal.jamry.arxivver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.arxiv.ArxivFeedEntryAuthor;

import static michal.jamry.arxivver.models.ModelUtils.prepareDate;
import static michal.jamry.arxivver.models.ModelUtils.removeNewlines;

public class ArxivEntryActivity extends AppCompatActivity {

    public static final String ARXIV_FEED_ENTRY_TYPE_OBJ = "michal.jamry.arxivver.activities.ArxivEntryActivity.ARXIV_FEED_ENTRY_TYPE_OBJ";
    private ArxivFeedEntry arxivFeedEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arxiv_entry);

        Intent intent = getIntent();
        arxivFeedEntry = (ArxivFeedEntry) intent.getSerializableExtra(ARXIV_FEED_ENTRY_TYPE_OBJ);

        TextView entryTitle = findViewById(R.id.titleTextView);
        entryTitle.setText(removeNewlines(arxivFeedEntry.getTitle().trim()));

        TextView summary = findViewById(R.id.summaryTextView);
        summary.setText(removeNewlines(arxivFeedEntry.getSummary().trim()));

        TextView date = findViewById(R.id.PUBLISHED_DATE_TEXT_VIEW);
        date.setText(prepareDate(arxivFeedEntry.getPublished()));

        TextView authors = findViewById(R.id.authorsTextView);
        authors.setText(prepareAuthors(arxivFeedEntry.getAuthorList()));

    }

    private String prepareAuthors(List<ArxivFeedEntryAuthor> authorList) {
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < authorList.size(); i++) {
            if (i > 0) {
                ret.append(", ");
            }

            ret.append(authorList.get(i).getName());
        }

        return ret.toString();
    }
}
