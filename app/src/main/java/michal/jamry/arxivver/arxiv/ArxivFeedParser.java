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

    public ArxivApiQueryResult parse(InputStream in) throws XmlPullParserException, IOException {
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

    private ArxivApiQueryResult readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArxivApiQueryResult arxivApiQueryResult = new ArxivApiQueryResult();
        List<ArxivFeedEntry> entries = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, ns, "feed");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals("entry")) {
                entries.add(readEntry(parser));

            } else if (name.equals("link")) {

            } else if (name.equals("id")) {

            } else if (name.equals("updated")) {

            } else if (name.equals("opensearch")) {

            } else {
                skip(parser);
            }
        }

        arxivApiQueryResult.setEntries(entries);
        return arxivApiQueryResult;
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
                    List<String> authorList = arxivFeedEntry.getAuthorList();
                    if (authorList == null) {
                        authorList = new ArrayList<>();
                    }

                    authorList.add(readAuthor(parser));
                    arxivFeedEntry.setAuthorList(authorList);

                    break;
                case "link":
//                        arxivFeedEntry.setLink(readText(parser, "link"));
//                        arxivFeedEntry.setLinkTitle(readText(parser, "link"));

                    break;
                case "arxiv":
                    //primary category
                    //comment
//                    arxivFeedEntry.setPrimaryCategory(readText(parser, "primary"));

                    break;
                case "category":
                    List<String> categories = arxivFeedEntry.getCategories();
                    if (categories == null) {
                        categories = new ArrayList<>();
                    }

                    categories.add(readText(parser, "category"));
                    arxivFeedEntry.setCategories(categories);

                    break;
                default:
                    skip(parser);
                    break;
            }
        }

        return arxivFeedEntry;
    }

    private String readAuthor(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        parser.require(XmlPullParser.START_TAG, ns, "author");
        parser.nextTag();
        parser.require(XmlPullParser.START_TAG, ns, "name");

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
