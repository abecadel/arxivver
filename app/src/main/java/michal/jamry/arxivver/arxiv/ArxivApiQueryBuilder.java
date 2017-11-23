package michal.jamry.arxivver.arxiv;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Arxiv api query builder.
 */
public class ArxivApiQueryBuilder {

    /**
     * The constant HINTS.
     */
    public static final String[] HINTS = {"ti:", "au:", "abs:", "co:", "jr:", "cat:", "rn:", "stat.AP", "stat.CO", "stat.ML", "stat.ME", "stat.TH", "q-bio.BM", "q-bio.CB", "q-bio.GN", "q-bio.MN", "q-bio.NC", "q-bio.OT", "q-bio.PE", "q-bio.QM", "q-bio.SC", "q-bio.TO", "cs.AR", "cs.AI", "cs.CL", "cs.CC", "cs.CE", "cs.CG", "cs.GT", "cs.CV", "cs.CY", "cs.CR", "cs.DS", "cs.DB", "cs.DL", "cs.DM", "cs.DC", "cs.GL", "cs.GR", "cs.HC", "cs.IR", "cs.IT", "cs.LG", "cs.LO", "cs.MS", "cs.MA", "cs.MM", "cs.NI", "cs.NE", "cs.NA", "cs.OS", "cs.OH", "cs.PF", "cs.PL", "cs.RO", "cs.SE", "cs.SD", "cs.SC", "nlin.AO", "nlin.CG", "nlin.CD", "nlin.SI", "nlin.PS", "math.AG", "math.AT", "math.AP", "math.CT", "math.CA", "math.CO", "math.AC", "math.CV", "math.DG", "math.DS", "math.FA", "math.GM", "math.GN", "math.GT", "math.GR", "math.HO", "math.IT", "math.KT", "math.LO", "math.MP", "math.MG", "math.NT", "math.NA", "math.OA", "math.OC", "math.PR", "math.QA", "math.RT", "math.RA", "math.SP", "math.ST", "math.SG", "astro-ph", "cond-mat.dis-nn", "cond-mat.mes-hall", "cond-mat.mtrl-sci", "cond-mat.other", "cond-mat.soft", "cond-mat.stat-mech", "cond-mat.str-el", "cond-mat.supr-con", "gr-qc", "hep-ex", "hep-lat", "hep-ph", "hep-th", "math-ph", "nucl-ex", "nucl-th", "physics.acc-ph", "physics.ao-ph", "physics.atom-ph", "physics.atm-clus", "physics.bio-ph", "physics.chem-ph", "physics.class-ph", "physics.comp-ph", "physics.data-an", "physics.flu-dyn", "physics.gen-ph", "physics.geo-ph", "physics.hist-ph", "physics.ins-det", "physics.med-ph", "physics.optics", "physics.ed-ph", "physics.soc-ph", "physics.plasm-ph", "physics.pop-ph", "physics.space-ph", "quant-ph"};
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
     * With full search query arxiv api query builder.
     *
     * @param searchQuery the search query
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withFullSearchQuery(String searchQuery) {
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
    public ArxivApiQueryBuilder and() {
        searchQueryList.add("+AND+");
        return this;
    }

    /**
     * Or arxiv api query builder.
     *
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder or() {
        searchQueryList.add("+OR+");
        return this;
    }

    /**
     * With search query param arxiv api query builder.
     *
     * @param param the param
     * @return the arxiv api query builder
     */
    public ArxivApiQueryBuilder withSearchQueryParam(String param) {
        searchQueryList.add(param);
        return this;
    }

    /**
     * Build string.
     *
     * @return the string
     */
    public String build() {
        if (searchQueryList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : searchQueryList) {
                sb.append(s);
            }

            withFullSearchQuery(sb.toString());
        }

        return query;
    }
}
