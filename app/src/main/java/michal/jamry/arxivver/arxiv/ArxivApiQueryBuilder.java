package michal.jamry.arxivver.arxiv;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Arxiv api query builder.
 */
public class ArxivApiQueryBuilder {

    private static final String ARXIV_HOST_PREFIX = "http://export.arxiv.org/api/query?";
    private String query;
    private List<String> searchQueryList = new ArrayList<>();

    private ArxivApiQueryBuilder(String prefix) {
        query = prefix;
    }

    /**
     * A builder arxiv api query builder.
     *
     * @return the arxiv api query builder
     */
    public static ArxivApiQueryBuilder aBuilder() {
        return new ArxivApiQueryBuilder(ARXIV_HOST_PREFIX);
    }

    /**
     * A builder arxiv api query builder.
     *
     * @param prefix the prefix
     * @return the arxiv api query builder
     */
    public static ArxivApiQueryBuilder aBuilder(String prefix) {
        return new ArxivApiQueryBuilder(prefix);
    }


    /**
     * With search query arxiv api query builder.
     *
     * @param searchQuery the search query
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withSearchQuery(String searchQuery) {
        addParam("search_query=" + searchQuery);
        return this;
    }

    /**
     * With id arxiv api query builder.
     *
     * @param id the id
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withId(String id) {
        return withIdList(Collections.singletonList(id));
    }

    /**
     * With id list arxiv api query builder.
     *
     * @param ids the ids
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withIdList(List<String> ids) {
        StringBuilder param = new StringBuilder("id_list=");

        for (int i = 0; i < ids.size(); i++) {
            if (i > 0) {
                param.append(",");
            }
            param.append(ids.get(i));
        }
        addParam(param.toString());
        return this;
    }

    /**
     * With start arxiv api query builder.
     *
     * @param start the start
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withStart(int start) {
        addParam("start=" + start);
        return this;
    }

    /**
     * With sort by relevance arxiv api query builder.
     *
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withSortByRelevance() {
        addParam("sortBy=relevance");
        return this;
    }

    /**
     * With sort by last updated date arxiv api query builder.
     *
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withSortByLastUpdatedDate() {
        addParam("sortBy=lastUpdatedDate");
        return this;
    }

    /**
     * With sort by submitted date arxiv api query builder.
     *
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withSortBySubmittedDate() {
        addParam("sortBy=submittedDate");
        return this;
    }

    /**
     * With sort order ascending arxiv api query builder.
     *
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withSortOrderAscending() {
        addParam("sortOrder=ascending");
        return this;
    }

    /**
     * With sort order descending arxiv api query builder.
     *
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withSortOrderDescending() {
        addParam("sortOrder=descending");
        return this;
    }

    /**
     * With max results arxiv api query builder.
     *
     * @param maxResults the max results
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withMaxResults(int maxResults) {
        addParam("max_results=" + maxResults);
        return this;
    }

    private void addParam(String param) {
        if (!query.endsWith("?")) {
            query = query + "&";
        }

        query = query + param;
    }

    /**
     * And arxiv api query builder.
     *
     * @return the arxiv api query builder
     */
// Search query
    public ArxivApiQueryBuilder and(){
        searchQueryList.add("+AND+");
        return this;
    }

    /**
     * Or arxiv api query builder.
     *
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder or(){
        searchQueryList.add("+OR+");
        return this;
    }

    /**
     * Build string.
     *
     * @return the string
     */
    public String build() {
        return query;
    }
}
