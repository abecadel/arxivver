package michal.jamry.arxivver.arxiv;


import java.util.Date;
import java.util.List;

public class ArxivApiQueryResult {
    List<ArxivFeedEntry> entries;
    private String id;
    private String link;
    private Date updated;
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;

    public List<ArxivFeedEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ArxivFeedEntry> entries) {
        this.entries = entries;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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

    @Override
    public String toString() {
        return "ArxivApiQueryResult{" +
                "entries=" + entries +
                ", id='" + id + '\'' +
                ", link='" + link + '\'' +
                ", updated=" + updated +
                ", totalResults=" + totalResults +
                ", startIndex=" + startIndex +
                ", itemsPerPage=" + itemsPerPage +
                '}';
    }
}
