package michal.jamry.arxivver.arxiv;


import android.support.annotation.Nullable;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Arxiv feed parser.
 */
public class ArxivFeedParser {

    private static final String ns = null;
    private static final DateFormat arxivEntryDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    /**
     * Parse arxiv feed.
     *
     * @param in the in
     * @return the arxiv feed
     * @throws XmlPullParserException the xml pull parser exception
     * @throws IOException            the io exception
     */
    public ArxivFeed parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private ArxivFeed readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArxivFeed arxivFeed = new ArxivFeed();
        List<ArxivFeedEntry> entries = new ArrayList<>();

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            switch (name) {
                case "entry":
                    entries.add(readEntry(parser));

                    break;
                case "link":
                    arxivFeed.setLink(parser.getAttributeValue(null, "href"));
                    parser.nextTag();

                    break;
                case "title":
                    arxivFeed.setTitle(readText(parser, "title"));

                    break;
                case "id":
                    arxivFeed.setId(readText(parser, "id"));

                    break;
                case "updated":
                    arxivFeed.setUpdated(readDate(parser, "updated"));

                    break;
                case "opensearch:totalResults":
                    arxivFeed.setTotalResults(Integer.parseInt(readText(parser, "opensearch:totalResults")));

                    break;
                case "opensearch:startIndex":
                    arxivFeed.setStartIndex(Integer.parseInt(readText(parser, "opensearch:startIndex")));

                    break;
                case "opensearch:itemsPerPage":
                    arxivFeed.setItemsPerPage(Integer.parseInt(readText(parser, "opensearch:itemsPerPage")));

                    break;
                default:
                    skip(parser);
                    break;
            }
        }

        arxivFeed.setEntries(entries);
        return arxivFeed;
    }

    private ArxivFeedEntry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArxivFeedEntry arxivFeedEntry = new ArxivFeedEntry();
        parser.require(XmlPullParser.START_TAG, ns, "entry");

        while (parser.next() != XmlPullParser.END_TAG) {

            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            switch (name) {
                case "id":
                    arxivFeedEntry.setId(readText(parser, "id"));

                    break;
                case "updated":
                    List<Date> updatedList = arxivFeedEntry.getUpdatedList();
                    if (updatedList == null) {
                        updatedList = new ArrayList<>();
                    }

                    updatedList.add(readDate(parser, "updated"));
                    arxivFeedEntry.setUpdatedList(updatedList);

                    break;
                case "published":
                    arxivFeedEntry.setPublished(readDate(parser, "published"));

                    break;
                case "title":
                    arxivFeedEntry.setTitle(readText(parser, "title"));

                    break;
                case "summary":
                    arxivFeedEntry.setSummary(readText(parser, "summary"));

                    break;
                case "author":
                    List<ArxivFeedEntryAuthor> authorList = arxivFeedEntry.getAuthorList();
                    if (authorList == null) {
                        authorList = new ArrayList<>();
                    }

                    authorList.add(readAuthor(parser));
                    arxivFeedEntry.setAuthorList(authorList);

                    break;
                case "link":
                    String linkType = parser.getAttributeValue(null, "type");
                    String href = parser.getAttributeValue(null, "href");

                    Map<String, String> links = arxivFeedEntry.getLinks();
                    if (links == null) {
                        links = new HashMap<>();
                    }

                    links.put(linkType, href);
                    arxivFeedEntry.setLinks(links);
                    parser.nextTag();

                    break;
                case "arxiv:primary_category":
                    arxivFeedEntry.setPrimaryCategory(parser.getAttributeValue(null, "term"));
                    skip(parser);

                    break;
                case "arxiv:comment":
                    arxivFeedEntry.setComment(readText(parser, "arxiv:comment"));

                    break;
                case "arxiv:journal_ref":
                    arxivFeedEntry.setJournalRef(readText(parser, "arxiv:journal_ref"));

                    break;
                case "arxiv:doi":
                    arxivFeedEntry.setJournalRef(parser.getAttributeValue(null, "term"));
                    skip(parser);

                    break;
                case "category":
                    List<String> categories = arxivFeedEntry.getCategories();
                    if (categories == null) {
                        categories = new ArrayList<>();
                    }

                    categories.add(parser.getAttributeValue(null, "term"));
                    parser.nextTag();
                    arxivFeedEntry.setCategories(categories);

                    break;
                default:
                    skip(parser);
                    break;
            }
        }

        return arxivFeedEntry;
    }

    private ArxivFeedEntryAuthor readAuthor(XmlPullParser parser) throws IOException, XmlPullParserException {
        String name = null;
        String affiliation = null;
        parser.require(XmlPullParser.START_TAG, ns, "author");
        parser.nextTag();

        if (parser.next() == XmlPullParser.TEXT) {
            name = parser.getText();
            parser.nextTag();
        }

        parser.require(XmlPullParser.END_TAG, ns, "name");
        parser.nextTag();

        while (parser.getName().equals("arxiv:affiliation")) {
            parser.require(XmlPullParser.START_TAG, ns, "arxiv:affiliation");

            if (parser.next() == XmlPullParser.TEXT) {
                affiliation = parser.getText();
                parser.nextTag();
            }

            parser.require(XmlPullParser.END_TAG, ns, "arxiv:affiliation");
            parser.nextTag();
        }

        parser.require(XmlPullParser.END_TAG, ns, "author");

        return new ArxivFeedEntryAuthor(name, affiliation);
    }

    private String readText(XmlPullParser parser, String tagName) throws IOException, XmlPullParserException {
        String result = "";
        parser.require(XmlPullParser.START_TAG, ns, tagName);

        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }

        parser.require(XmlPullParser.END_TAG, ns, tagName);
        return result;
    }

    @Nullable
    private Date readDate(XmlPullParser parser, String tagName) {
        try {
            String str = readText(parser, tagName);
            return arxivEntryDateFormat.parse(str);

        } catch (ParseException | IOException | XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }

        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

}
