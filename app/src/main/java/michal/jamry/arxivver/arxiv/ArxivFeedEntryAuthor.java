package michal.jamry.arxivver.arxiv;

import java.io.Serializable;

/**
 * The type Arxiv feed entry author.
 */
public class ArxivFeedEntryAuthor implements Serializable {
    private String name;
    private String affiliation;

    /**
     * Instantiates a new Arxiv feed entry author.
     */
    public ArxivFeedEntryAuthor() {
    }

    /**
     * Instantiates a new Arxiv feed entry author.
     *
     * @param name        the name
     * @param affiliation the affiliation
     */
    public ArxivFeedEntryAuthor(String name, String affiliation) {
        this.name = name;
        this.affiliation = affiliation;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets affiliation.
     *
     * @return the affiliation
     */
    public String getAffiliation() {
        return affiliation;
    }

    /**
     * Sets affiliation.
     *
     * @param affiliation the affiliation
     */
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
