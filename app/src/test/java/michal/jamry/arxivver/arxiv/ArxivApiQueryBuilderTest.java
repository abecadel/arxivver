package michal.jamry.arxivver.arxiv;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArxivApiQueryBuilderTest {

    @Test
    public void simpleSearchQuery() {
        //when
        String query = ArxivApiQueryBuilder.aBuilder().withSearchQuery("LSTM").build();
        //then
        assertEquals("http://export.arxiv.org/api/query?search_query=LSTM", query);
    }

    @Test
    public void andSearchQuery() {
        //when
        String query = ArxivApiQueryBuilder.aBuilder().withSearchQuery("all:electron+AND+all:proton").build();
        //then
        assertEquals("http://export.arxiv.org/api/query?search_query=all:electron+AND+all:proton", query);
    }

}