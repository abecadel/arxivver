package michal.jamry.arxivver.models;


import android.support.v7.widget.RecyclerView;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import java.util.Collections;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;

import static michal.jamry.arxivver.models.ModelUtils.makeLinks;
import static michal.jamry.arxivver.models.ModelUtils.prepareCategories;
import static michal.jamry.arxivver.models.ModelUtils.prepareDate;
import static michal.jamry.arxivver.models.ModelUtils.prepareShortAuthorsList;
import static michal.jamry.arxivver.models.ModelUtils.prepareTxt;

public class ArxivTimelineEntryViewHolder extends RecyclerView.ViewHolder {
    TextView entryTitle;
    TextView entrySummary;
    TextView publishedDate;
    TextView authors;
    TextView categories;
    ClickableSpan onTitleClickListener;

    public ArxivTimelineEntryViewHolder(View itemView, ClickableSpan onTitleClickListener) {
        super(itemView);
        this.onTitleClickListener = onTitleClickListener;

        entryTitle = itemView.findViewById(R.id.ENTRY_TITLE);
        entrySummary = itemView.findViewById(R.id.ENTRY_SUMMARY);
        publishedDate = itemView.findViewById(R.id.publishedDateEntryPage);
        authors = itemView.findViewById(R.id.AUTHORS);
        categories = itemView.findViewById(R.id.CATEGORIES);

    }

    public void bind(ArxivFeedEntry arxivFeedEntry) {
        String titleTxt = prepareTxt(arxivFeedEntry.getTitle());
        entryTitle.setText(titleTxt);
        makeLinks(entryTitle, Collections.singletonList(titleTxt), Collections.singletonList(onTitleClickListener));

        entrySummary.setText(prepareTxt(arxivFeedEntry.getSummary()));
        publishedDate.setText(prepareDate(arxivFeedEntry.getPublished()));
        authors.setText(prepareShortAuthorsList(arxivFeedEntry.getAuthorList()));
        categories.setText(prepareCategories(arxivFeedEntry.getPrimaryCategory(), arxivFeedEntry.getCategories()));
    }

}

