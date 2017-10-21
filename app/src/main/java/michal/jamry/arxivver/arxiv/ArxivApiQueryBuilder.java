package michal.jamry.arxivver.arxiv;


public class ArxivApiQueryBuilder {

    public static final String ARXIV_HOST_PREFIX = "http://export.arxiv.org/api/query?";
    private String searchQuery;

    private ArxivApiQueryBuilder() {
    }

    public static ArxivApiQueryBuilder aBuilder() {
        return new ArxivApiQueryBuilder();
    }

    public String build() {
        return ARXIV_HOST_PREFIX + null;
    }

    public ArxivApiQueryBuilder withSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
        return this;
    }
}
