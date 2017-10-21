package michal.jamry.arxivver.arxiv;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ArxivRetrievePublicationsTask extends AsyncTask<String, Void, ArxivApiQueryResult> {
    @Override
    protected ArxivApiQueryResult doInBackground(String... urls) {
        try {
            return loadXmlFromNetwork(urls[0]);
        } catch (IOException e) {
//            return getResources().getString(R.string.connection_error);
        } catch (XmlPullParserException e) {
//            return getResources().getString(R.string.xml_error);
        }

        return null; //TODO: remove
    }

    @Override
    protected void onPostExecute(ArxivApiQueryResult result) {
//        setContentView(R.layout.main);
//        // Displays the HTML string in the UI via a WebView
//        WebView myWebView = (WebView) findViewById(R.id.webview);
//        myWebView.loadData(result, "text/html", null);
    }

    private ArxivApiQueryResult loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        ArxivFeedParser arxivFeedParser = new ArxivFeedParser();
        ArxivApiQueryResult arxivApiQueryResult;

        try {
            stream = downloadUrl(urlString);
            arxivApiQueryResult = arxivFeedParser.parse(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        return arxivApiQueryResult;
    }

    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Starts the query
        conn.connect();
        return conn.getInputStream();
    }

}

