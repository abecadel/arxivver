package michal.jamry.arxivver.models;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;

public class ArxivTimelineEntryViewHolder extends RecyclerView.ViewHolder {
    TextView listItemNumberView;

    public ArxivTimelineEntryViewHolder(View itemView) {
        super(itemView);

        listItemNumberView = itemView.findViewById(R.id.tv_item_number);
    }

    public void bind(ArxivFeedEntry arxivFeedEntry) {
        listItemNumberView.setText(arxivFeedEntry.getTitle());
    }
}

