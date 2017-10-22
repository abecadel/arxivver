package michal.jamry.arxivver.arxiv;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArxivApiQueryBuilder {

    private static final String ARXIV_HOST_PREFIX = "http://export.arxiv.org/api/query?";
    private String query;

    private ArxivApiQueryBuilder(String prefix) {
        query = prefix;
    }

    public static ArxivApiQueryBuilder aBuilder() {
        return new ArxivApiQueryBuilder(ARXIV_HOST_PREFIX);
    }

    public static ArxivApiQueryBuilder aBuilder(String prefix) {
        return new ArxivApiQueryBuilder(prefix);
    }

    public String build() {
        return query;
    }

    public ArxivApiQueryBuilder withSearchQuery(String searchQuery) {
        return withSearchQuery(Arrays.asList(new String[]{searchQuery}));
    }

    public ArxivApiQueryBuilder withSearchQuery(List<String> searchQueryList) {
        String param = "search_query=";

        for (int i = 0; i < searchQueryList.size(); i++) {
            if (i > 0) {
                param += "+AND+";
            }
            param += searchQueryList.get(i);
        }
        addParam(param);
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
        addParam("max_result=" + maxResults);
        return this;
    }

    private void addParam(String param) {
        if (!query.endsWith("?")) {
            query = query + "&";
        }

        query = query + param;
    }
}
