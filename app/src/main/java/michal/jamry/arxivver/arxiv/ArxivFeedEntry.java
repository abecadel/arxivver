package michal.jamry.arxivver.arxiv;

import java.util.Date;
import java.util.List;

class ArxivFeedEntry {
    private String id;
    private List<Date> updatedList;
    private Date published;
    private String title;
    private String summary;
    private List<String> authorList;
    private String link;
    private String linkTitle;
    private String primaryCategory;
    private List<String> categories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Date> getUpdatedList() {
        return updatedList;
    }

    public void setUpdatedList(List<Date> updatedList) {
        this.updatedList = updatedList;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ArxivFeedEntry{" +
                "id='" + id + '\'' +
                ", updatedList=" + updatedList +
                ", published=" + published +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", authorList=" + authorList +
                ", link='" + link + '\'' +
                ", linkTitle='" + linkTitle + '\'' +
                ", primaryCategory='" + primaryCategory + '\'' +
                ", categories=" + categories +
                '}';
    }
}
