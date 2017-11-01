package michal.jamry.arxivver.arxiv;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ArxivFeedEntry {
    private String title;
    private String id;
    private Date published;
    private List<Date> updatedList;
    private String summary;
    private List<ArxivFeedEntryAuthor> authorList;
    private Map<String, String> links;
    private List<String> categories;
    private String primaryCategory;
    private String comment;
    private String journalRef;
    private String doi;

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

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public List<Date> getUpdatedList() {
        return updatedList;
    }

    public void setUpdatedList(List<Date> updatedList) {
        this.updatedList = updatedList;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<ArxivFeedEntryAuthor> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<ArxivFeedEntryAuthor> authorList) {
        this.authorList = authorList;
    }

    public Map<String, String> getLinks() {
        return links;
    }

    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getJournalRef() {
        return journalRef;
    }

    public void setJournalRef(String journalRef) {
        this.journalRef = journalRef;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    @Override
    public String toString() {
        return "ArxivFeedEntry{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", published=" + published +
                ", updatedList=" + updatedList +
                ", summary='" + summary + '\'' +
                ", authorList=" + authorList +
                ", links=" + links +
                ", categories=" + categories +
                ", primaryCategory='" + primaryCategory + '\'' +
                ", comment='" + comment + '\'' +
                ", journalRef='" + journalRef + '\'' +
                ", doi='" + doi + '\'' +
                '}';
    }
}
