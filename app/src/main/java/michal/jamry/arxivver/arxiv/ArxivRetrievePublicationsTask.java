package michal.jamry.arxivver.arxiv;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ArxivRetrievePublicationsTask extends AsyncTask<String, Void, ArxivFeed> {
    @Override
    protected ArxivFeed doInBackground(String... urls) {
        try {
            return loadXmlFromNetwork(urls[0]);
        } catch (IOException e) {
//            return getResources().getString(R.string.connection_error);
        } catch (XmlPullParserException e) {
//            return getResources().getString(R.string.xml_error);
        }

        return null; //TODO: remove
    }

    private ArxivFeed loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        ArxivFeedParser arxivFeedParser = new ArxivFeedParser();
        ArxivFeed arxivFeed;

        try {
            stream = downloadUrl(urlString);
            arxivFeed = arxivFeedParser.parse(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return arxivFeed;
    }

    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }

}

