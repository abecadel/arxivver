package michal.jamry.arxivver.adapters.listeners;


import michal.jamry.arxivver.arxiv.ArxivFeedEntry;

/**
 * Composed callback used to handle click and error events while displaying feed entries.
 */
public class ArxivTimelineAdapterCallbackListener {
    /**
     * Handle entry link clicked.
     *
     * @param arxivFeedEntry the arxiv feed entry
     */
    public void handleEntryLinkClicked(ArxivFeedEntry arxivFeedEntry) {
    }

    /**
     * Handle data request complete.
     */
    public void handleDataRequestComplete() {
    }

    /**
     * Handle errors.
     *
     * @param error the error
     * @param e     the e
     */
    public void handleErrors(String error, Exception e) {
    }

}
