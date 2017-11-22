package michal.jamry.arxivver.arxiv;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The type Arxiv feed entry.
 */
public class ArxivFeedEntry implements Serializable {
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
     * Gets published.
     *
     * @return the published
     */
    public Date getPublished() {
        return published;
    }

    /**
     * Sets published.
     *
     * @param published the published
     */
    public void setPublished(Date published) {
        this.published = published;
    }

    /**
     * Gets updated list.
     *
     * @return the updated list
     */
    public List<Date> getUpdatedList() {
        return updatedList;
    }

    /**
     * Sets updated list.
     *
     * @param updatedList the updated list
     */
    public void setUpdatedList(List<Date> updatedList) {
        this.updatedList = updatedList;
    }

    /**
     * Gets summary.
     *
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets summary.
     *
     * @param summary the summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets author list.
     *
     * @return the author list
     */
    public List<ArxivFeedEntryAuthor> getAuthorList() {
        return authorList;
    }

    /**
     * Sets author list.
     *
     * @param authorList the author list
     */
    public void setAuthorList(List<ArxivFeedEntryAuthor> authorList) {
        this.authorList = authorList;
    }

    /**
     * Gets links.
     *
     * @return the links
     */
    public Map<String, String> getLinks() {
        return links;
    }

    /**
     * Sets links.
     *
     * @param links the links
     */
    public void setLinks(Map<String, String> links) {
        this.links = links;
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    public List<String> getCategories() {
        return categories;
    }

    /**
     * Sets categories.
     *
     * @param categories the categories
     */
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    /**
     * Gets primary category.
     *
     * @return the primary category
     */
    public String getPrimaryCategory() {
        return primaryCategory;
    }

    /**
     * Sets primary category.
     *
     * @param primaryCategory the primary category
     */
    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets journal ref.
     *
     * @return the journal ref
     */
    public String getJournalRef() {
        return journalRef;
    }

    /**
     * Sets journal ref.
     *
     * @param journalRef the journal ref
     */
    public void setJournalRef(String journalRef) {
        this.journalRef = journalRef;
    }

    /**
     * Gets doi.
     *
     * @return the doi
     */
    public String getDoi() {
        return doi;
    }

    /**
     * Sets doi.
     *
     * @param doi the doi
     */
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
