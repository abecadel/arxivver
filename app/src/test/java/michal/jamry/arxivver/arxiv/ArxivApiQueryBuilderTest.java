package michal.jamry.arxivver.arxiv;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArxivApiQueryBuilderTest {

    @Test
    public void simpleSearchQuery() {
        //when
        String query = ArxivApiQueryBuilder.aBuilder().withFullSearchQuery("LSTM").build();
        //then
        assertEquals("http://export.arxiv.org/api/query?search_query=LSTM", query);
    }

    @Test
    public void maxResultsSearchQuery() {
        //when
        String query = ArxivApiQueryBuilder.aBuilder().withFullSearchQuery("LSTM").withMaxResults(100).build();
        //then
        assertEquals("http://export.arxiv.org/api/query?search_query=LSTM&max_results=100", query);
    }

    @Test
    public void andSearchQuery() {
        //when
        String query = ArxivApiQueryBuilder.aBuilder().withFullSearchQuery("all:electron+AND+all:proton").build();
        //then
        assertEquals("http://export.arxiv.org/api/query?search_query=all:electron+AND+all:proton", query);
    }

    @Test
    public void andParamSearchQuery() {
        //when
        String query = ArxivApiQueryBuilder.aBuilder().withSearchQueryParam("LSTM").or().withSearchQueryParam("RNN").build();
        //then
        assertEquals("http://export.arxiv.org/api/query?search_query=LSTM+OR+RNN", query);
    }


}