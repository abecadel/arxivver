package michal.jamry.arxivver.models;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.arxiv.ArxivFeedEntryAuthor;

import static michal.jamry.arxivver.models.ModelUtils.prepareDate;
import static michal.jamry.arxivver.models.ModelUtils.prepareTxt;

public class ArxivTimelineEntryViewHolder extends RecyclerView.ViewHolder {
    TextView entryTitle;
    TextView entrySummary;
    TextView publishedDate;
    TextView authors;

    public ArxivTimelineEntryViewHolder(View itemView) {
        super(itemView);

        entryTitle = itemView.findViewById(R.id.ENTRY_TITLE);
        entrySummary = itemView.findViewById(R.id.ENTRY_SUMMARY);
        publishedDate = itemView.findViewById(R.id.PUBLISHED_DATE_TEXT_VIEW);
        authors = itemView.findViewById(R.id.AUTHORS);
    }

    public void bind(ArxivFeedEntry arxivFeedEntry) {
        entryTitle.setText(prepareTxt(arxivFeedEntry.getTitle()));
        entrySummary.setText(prepareTxt(arxivFeedEntry.getSummary()));
        publishedDate.setText(prepareDate(arxivFeedEntry.getPublished()));
        authors.setText(prepareAuthors(arxivFeedEntry.getAuthorList()));
    }

    private String prepareAuthors(List<ArxivFeedEntryAuthor> authorList) {
        StringBuilder ret = new StringBuilder();

        for (int i = 0; i < authorList.size() && i < 3; i++) {
            if (i > 0) {
                ret.append(", ");
            }

            ret.append(authorList.get(i).getName());
        }

        if (authorList.size() > 3) {
            ret.append("...");
        }

        return ret.toString();
    }

}

