package michal.jamry.arxivver.models;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;

import static michal.jamry.arxivver.models.ModelUtils.prepareDate;
import static michal.jamry.arxivver.models.ModelUtils.prepareTxt;

public class ArxivTimelineEntryViewHolder extends RecyclerView.ViewHolder {
    TextView entryTitle;
    TextView entrySummary;
    TextView publishedDate;

    public ArxivTimelineEntryViewHolder(View itemView) {
        super(itemView);

        entryTitle = itemView.findViewById(R.id.ENTRY_TITLE);
        entrySummary = itemView.findViewById(R.id.ENTRY_SUMMARY);
        publishedDate = itemView.findViewById(R.id.PUBLISHED_DATE_TEXT_VIEW);
    }

    public void bind(ArxivFeedEntry arxivFeedEntry) {
        entryTitle.setText(prepareTxt(arxivFeedEntry.getTitle()));
        entrySummary.setText(prepareTxt(arxivFeedEntry.getSummary()));
        publishedDate.setText(prepareDate(arxivFeedEntry.getPublished()));
    }

}

