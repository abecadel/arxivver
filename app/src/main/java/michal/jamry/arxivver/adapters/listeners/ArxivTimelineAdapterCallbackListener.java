package michal.jamry.arxivver.adapters.listeners;


import michal.jamry.arxivver.arxiv.ArxivFeedEntry;

public interface ArxivTimelineAdapterCallbackListener {
    void handleEntryLinkClicked(ArxivFeedEntry arxivFeedEntry);
    void handleDataRequestComplete();
}
