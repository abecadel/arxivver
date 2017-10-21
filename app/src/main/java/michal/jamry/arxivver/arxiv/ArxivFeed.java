package michal.jamry.arxivver.arxiv;


import java.util.Date;
import java.util.List;

public class ArxivFeed {
    private String title;
    private String id;
    private Date updated;
    private String link;
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;
    List<ArxivFeedEntry> entries;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public List<ArxivFeedEntry> getEntries() {
        return entries;
    }

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
