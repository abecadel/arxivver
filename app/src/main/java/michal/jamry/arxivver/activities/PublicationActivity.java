package michal.jamry.arxivver.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.persistence.LocalEntriesStorage;

import static michal.jamry.arxivver.models.ModelUtils.prepareCategories;
import static michal.jamry.arxivver.models.ModelUtils.prepareDate;
import static michal.jamry.arxivver.models.ModelUtils.prepareLongAuthorsList;
import static michal.jamry.arxivver.models.ModelUtils.removeNewlines;

/**
 * Activity showing details of a specific publication. Allows us to "star" it or show the pdf.
 */
public class PublicationActivity extends AppCompatActivity {

    /**
     * The constant ARXIV_FEED_ENTRY_TYPE_OBJ.
     */
    public static final String ARXIV_FEED_ENTRY_TYPE_OBJ = "michal.jamry.arxivver.activities.PublicationActivity.ARXIV_FEED_ENTRY_TYPE_OBJ";
    private static final String MIME_PDF = "application/pdf";
    private ArxivFeedEntry arxivFeedEntry;

    private LocalEntriesStorage localEntriesStorage;

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
                Toast.makeText(getBaseContext(), "Publication starred", Toast.LENGTH_SHORT);
            } else {
                localEntriesStorage.removeFromStorage(arxivFeedEntry.getId());
                Toast.makeText(getBaseContext(), "Publication unstarred", Toast.LENGTH_SHORT);
            }
        });

        TextView categories = findViewById(R.id.categoriesEntryPage);
        categories.setText(prepareCategories(arxivFeedEntry.getPrimaryCategory(), arxivFeedEntry.getCategories()));

        Button openPdfButton = findViewById(R.id.openpdfbutton);
        openPdfButton.setOnClickListener(view -> {
            String url = arxivFeedEntry.getLinks().get(MIME_PDF);

            if (url != null) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setDataAndType(Uri.parse(url), MIME_PDF);

                Intent chooser = Intent.createChooser(browserIntent, "Choose how to open pdf");
                chooser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(chooser);
            } else {
                Toast.makeText(getBaseContext(), "Pdf link is missing", Toast.LENGTH_LONG);
            }
        });
    }

}
