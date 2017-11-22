package michal.jamry.arxivver.models;


import android.support.v7.widget.RecyclerView;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Collections;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;

import static michal.jamry.arxivver.models.ModelUtils.makeLinks;
import static michal.jamry.arxivver.models.ModelUtils.prepareCategories;
import static michal.jamry.arxivver.models.ModelUtils.prepareDate;
import static michal.jamry.arxivver.models.ModelUtils.prepareShortAuthorsList;
import static michal.jamry.arxivver.models.ModelUtils.prepareTxt;

/**
 * The type Arxiv timeline entry view holder.
 */
public class ArxivTimelineEntryViewHolder extends RecyclerView.ViewHolder {
    /**
     * The Entry title.
     */
    TextView entryTitle;
    /**
     * The Entry summary.
     */
    TextView entrySummary;
    /**
     * The Published date.
     */
    TextView publishedDate;
    /**
     * The Authors.
     */
    TextView authors;
    /**
     * The Categories.
     */
    TextView categories;
    /**
     * The On title click listener.
     */
    ClickableSpan onTitleClickListener;
    /**
     * The Toggle button.
     */
    ToggleButton toggleButton;

    /**
     * Instantiates a new Arxiv timeline entry view holder.
     *
     * @param itemView             the item view
     * @param onTitleClickListener the on title click listener
     */
    public ArxivTimelineEntryViewHolder(View itemView, ClickableSpan onTitleClickListener) {
        super(itemView);
        this.onTitleClickListener = onTitleClickListener;

        entryTitle = itemView.findViewById(R.id.ENTRY_TITLE);
        entrySummary = itemView.findViewById(R.id.ENTRY_SUMMARY);
        publishedDate = itemView.findViewById(R.id.publishedDateEntryPage);
        authors = itemView.findViewById(R.id.AUTHORS);
        categories = itemView.findViewById(R.id.CATEGORIES);
        toggleButton = itemView.findViewById(R.id.toggleButton);
    }

    /**
     * Bind.
     *
     * @param arxivFeedEntry the arxiv feed entry
     * @param storedLocally  the stored locally
     */
    public void bind(ArxivFeedEntry arxivFeedEntry, boolean storedLocally) {
        String titleTxt = prepareTxt(arxivFeedEntry.getTitle());
        entryTitle.setText(titleTxt);
        makeLinks(entryTitle, Collections.singletonList(titleTxt), Collections.singletonList(onTitleClickListener));

        entrySummary.setText(prepareTxt(arxivFeedEntry.getSummary()));
        publishedDate.setText(prepareDate(arxivFeedEntry.getPublished()));
        authors.setText(prepareShortAuthorsList(arxivFeedEntry.getAuthorList()));
        categories.setText(prepareCategories(arxivFeedEntry.getPrimaryCategory(), arxivFeedEntry.getCategories()));
        toggleButton.setChecked(storedLocally);
    }

}

