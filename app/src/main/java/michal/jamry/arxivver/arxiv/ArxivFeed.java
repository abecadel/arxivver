package michal.jamry.arxivver.arxiv;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The type Arxiv feed.
 */
public class ArxivFeed implements Serializable {
    private String title;
    private String id;
    private Date updated;
    private String link;
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;
    /**
     * The Entries.
     */
    List<ArxivFeedEntry> entries;

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets updated.
     *
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * Sets updated.
     *
     * @param updated the updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * Gets link.
     *
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets link.
     *
     * @param link the link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Gets total results.
     *
     * @return the total results
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * Sets total results.
     *
     * @param totalResults the total results
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * Gets start index.
     *
     * @return the start index
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Sets start index.
     *
     * @param startIndex the start index
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Gets items per page.
     *
     * @return the items per page
     */
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * Sets items per page.
     *
     * @param itemsPerPage the items per page
     */
    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * Gets entries.
     *
     * @return the entries
     */
    public List<ArxivFeedEntry> getEntries() {
        return entries;
    }

    /**
     * Sets entries.
     *
     * @param entries the entries
     */
    public void setEntries(List<ArxivFeedEntry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "ArxivFeed{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", updated=" + updated +
                ", link='" + link + '\'' +
                ", totalResults=" + totalResults +
                ", startIndex=" + startIndex +
                ", itemsPerPage=" + itemsPerPage +
                ", entries=" + entries +
                '}';
    }
}
