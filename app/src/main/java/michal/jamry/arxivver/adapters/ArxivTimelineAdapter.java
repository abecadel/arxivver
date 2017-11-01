package michal.jamry.arxivver.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.arxiv.ArxivApiQueryBuilder;
import michal.jamry.arxivver.arxiv.ArxivFeed;
import michal.jamry.arxivver.arxiv.ArxivFeedEntry;
import michal.jamry.arxivver.arxiv.ArxivRetrievePublicationsTask;

public class ArxivTimelineAdapter extends RecyclerView.Adapter<ArxivTimelineAdapter.ArxivTimelineEntryViewHolder> {

    private String query;
    private List<ArxivFeedEntry> arxivFeedEntryList = new ArrayList<>();

    public ArxivTimelineAdapter(String query) {
        this.query = query;

        String fullquery = ArxivApiQueryBuilder
                .aBuilder()
                .withSearchQuery(query)
                .withMaxResults(100)
                .build();

        new TimelineAdapterArxivRetrievePublicationsTask(this).execute(fullquery);
    }

    @Override
    public ArxivTimelineEntryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.arxiv_timeline_entry_layout;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        ArxivTimelineEntryViewHolder viewHolder = new ArxivTimelineEntryViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArxivTimelineEntryViewHolder holder, int position) {
        holder.bind(arxivFeedEntryList.get(position));
    }

    @Override
    public int getItemCount() {
        return arxivFeedEntryList.size();
    }

    void addArxivFeed(ArxivFeed arxivFeed) {
        arxivFeedEntryList.addAll(arxivFeed.getEntries());
        notifyDataSetChanged();
    }

    class ArxivTimelineEntryViewHolder extends RecyclerView.ViewHolder {
        TextView listItemNumberView;

        ArxivTimelineEntryViewHolder(View itemView) {
            super(itemView);

            listItemNumberView = itemView.findViewById(R.id.tv_item_number);
        }

        void bind(ArxivFeedEntry arxivFeedEntry) {
            listItemNumberView.setText(arxivFeedEntry.getTitle());
        }
    }


    private static class TimelineAdapterArxivRetrievePublicationsTask extends ArxivRetrievePublicationsTask {
        private WeakReference<ArxivTimelineAdapter> adapterWeakReference;

        public TimelineAdapterArxivRetrievePublicationsTask(ArxivTimelineAdapter adapter) {
            this.adapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        protected void onPostExecute(ArxivFeed arxivFeed) {
            if (arxivFeed != null) {
                adapterWeakReference.get().addArxivFeed(arxivFeed);
            } else {
                //handle error
                Log.d("Error downloading feed", exception.getLocalizedMessage());
            }
        }
    }
}

