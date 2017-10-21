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
import java.util.List;

public class ArxivFeedParser {

    private static final String ns = null;
    private static final DateFormat arxivEntryDateFormat = new SimpleDateFormat("YYYY-MM-DD'T'HH:MM:SSZ");

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

            if (name.equals("entry")) {
                entries.add(readEntry(parser));

            } else if (name.equals("link")) {
                arxivFeed.setLink(parser.getAttributeValue(null, "href"));
                parser.nextTag();

            } else if (name.equals("title")) {
                arxivFeed.setTitle(readText(parser, "title"));

            } else if (name.equals("id")) {
                arxivFeed.setId(readText(parser, "id"));

            } else if (name.equals("updated")) {
                arxivFeed.setUpdated(readDate(parser, "updated"));

            } else if (name.equals("opensearch:totalResults")) {
                arxivFeed.setTotalResults(Integer.parseInt(readText(parser, "opensearch:totalResults")));

            } else if (name.equals("opensearch:startIndex")) {
                arxivFeed.setStartIndex(Integer.parseInt(readText(parser, "opensearch:startIndex")));

            } else if (name.equals("opensearch:itemsPerPage")) {
                arxivFeed.setItemsPerPage(Integer.parseInt(readText(parser, "opensearch:itemsPerPage")));

            } else {
                skip(parser);
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
            if (name.equals("id")) {
                arxivFeedEntry.setId(readText(parser, "id"));

            } else if (name.equals("updated")) {
                List<Date> updatedList = arxivFeedEntry.getUpdatedList();
                if (updatedList == null) {
                    updatedList = new ArrayList<>();
                }

                updatedList.add(readDate(parser, "updated"));
                arxivFeedEntry.setUpdatedList(updatedList);

            } else if (name.equals("published")) {
                arxivFeedEntry.setPublished(readDate(parser, "published"));

            } else if (name.equals("title")) {
                arxivFeedEntry.setTitle(readText(parser, "title"));

            } else if (name.equals("summary")) {
                arxivFeedEntry.setSummary(readText(parser, "summary"));

            } else if (name.equals("author")) {
                List<String> authorList = arxivFeedEntry.getAuthorList();
                if (authorList == null) {
                    authorList = new ArrayList<>();
                }

                authorList.add(readAuthor(parser));
                arxivFeedEntry.setAuthorList(authorList);

            } else if (name.equals("link")) {
                //                        arxivFeedEntry.setLink(readText(parser, "link"));
//                        arxivFeedEntry.setLinkTitle(readText(parser, "link"));
                skip(parser); //TODO: remove

            } else if (name.equals("arxiv:primary_category")) {
                arxivFeedEntry.setPrimaryCategory(parser.getAttributeValue(null, "term"));
                skip(parser);

            } else if (name.equals("arxiv:comment")) {
                arxivFeedEntry.setComment(readText(parser, "arxiv:comment"));

            } else if (name.equals("arxiv:journal_ref")) {
                arxivFeedEntry.setJournalRef(parser.getAttributeValue(null, "term"));
                skip(parser);

            } else if (name.equals("arxiv:doi")) {
                arxivFeedEntry.setJournalRef(parser.getAttributeValue(null, "term"));
                skip(parser);

            } else if (name.equals("category")) {
                List<String> categories = arxivFeedEntry.getCategories();
                if (categories == null) {
                    categories = new ArrayList<>();
                }

                categories.add(parser.getAttributeValue(null, "term"));
                parser.nextTag();
                arxivFeedEntry.setCategories(categories);

            } else {
                skip(parser);
            }
        }

        return arxivFeedEntry;
    }

    private String readAuthor(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        parser.require(XmlPullParser.START_TAG, ns, "author");
        parser.nextTag();
        parser.require(XmlPullParser.START_TAG, ns, "name"); //TODO: if name==name getText

        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }

        parser.require(XmlPullParser.END_TAG, ns, "name");
        parser.nextTag();
        parser.require(XmlPullParser.END_TAG, ns, "author");
        return result;
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
            String str = readText(parser, tagName).replace("Z", "+0000");
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
