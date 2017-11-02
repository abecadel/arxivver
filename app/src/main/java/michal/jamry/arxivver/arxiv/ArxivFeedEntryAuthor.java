package michal.jamry.arxivver.arxiv;

import java.io.Serializable;

public class ArxivFeedEntryAuthor implements Serializable {
    private String name;
    private String affiliation;

    public ArxivFeedEntryAuthor() {
    }

    public ArxivFeedEntryAuthor(String name, String affiliation) {
        this.name = name;
        this.affiliation = affiliation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", affiliation='" + affiliation + '\'' +
                '}';
    }
}
