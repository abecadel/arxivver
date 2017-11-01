package michal.jamry.arxivver.arxiv;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArxivApiQueryBuilder {

    private static final String ARXIV_HOST_PREFIX = "http://export.arxiv.org/api/query?";
    private String query;
    private List<String> searchQueryList = new ArrayList<>();

    private ArxivApiQueryBuilder(String prefix) {
        query = prefix;
    }

    public static ArxivApiQueryBuilder aBuilder() {
        return new ArxivApiQueryBuilder(ARXIV_HOST_PREFIX);
    }

    public static ArxivApiQueryBuilder aBuilder(String prefix) {
        return new ArxivApiQueryBuilder(prefix);
    }


    public ArxivApiQueryBuilder withSearchQuery(String searchQuery) {
        addParam("search_query=" + searchQuery);
        return this;
    }

    public ArxivApiQueryBuilder withId(String id) {
        return withIdList(Collections.singletonList(id));
    }

    public ArxivApiQueryBuilder withIdList(List<String> ids) {
        String param = "id_list=";

        for (int i = 0; i < ids.size(); i++) {
            if (i > 0) {
                param += ",";
            }
            param += ids.get(i);
        }
        addParam(param);
        return this;
    }

    public ArxivApiQueryBuilder withStart(int start) {
        addParam("start=" + start);
        return this;
    }

    public ArxivApiQueryBuilder withSortByRelevance() {
        addParam("sortBy=relevance");
        return this;
    }

    public ArxivApiQueryBuilder withSortByLastUpdatedDate() {
        addParam("sortBy=lastUpdatedDate");
        return this;
    }

    public ArxivApiQueryBuilder withSortBySubmittedDate() {
        addParam("sortBy=submittedDate");
        return this;
    }

    public ArxivApiQueryBuilder withSortOrderAscending() {
        addParam("sortOrder=ascending");
        return this;
    }

    public ArxivApiQueryBuilder withSortOrderDescending() {
        addParam("sortOrder=descending");
        return this;
    }

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

    // Search query
    public ArxivApiQueryBuilder and(){
        searchQueryList.add("+AND+");
        return this;
    }

    public ArxivApiQueryBuilder or(){
        searchQueryList.add("+OR+");
        return this;
    }

    public String build() {
        return query;
    }
}
