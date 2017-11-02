package michal.jamry.arxivver.models;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;

import static michal.jamry.arxivver.models.ModelUtils.prepareTxt;

public class ArxivTimelineEntryViewHolder extends RecyclerView.ViewHolder {
    TextView entryTitle;
    TextView entrySummary;

    public ArxivTimelineEntryViewHolder(View itemView) {
        super(itemView);

        entryTitle = itemView.findViewById(R.id.entry_title);
        entrySummary = itemView.findViewById(R.id.entry_summary);
    }

    public void bind(ArxivFeedEntry arxivFeedEntry) {
        entryTitle.setText(prepareTxt(arxivFeedEntry.getTitle()));
        entrySummary.setText(prepareTxt(arxivFeedEntry.getSummary()));
    }

}

