package michal.jamry.arxivver;

import android.app.Application;
import android.content.SharedPreferences;

import static michal.jamry.arxivver.activities.MainTimelineActivity.ARXIV_TIMELINE_ACTIVITY_STORAGE;
import static michal.jamry.arxivver.activities.MainTimelineActivity.POSITION;

public class ArxivverApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //cleanup on startup
        SharedPreferences sharedPreferences = getSharedPreferences(ARXIV_TIMELINE_ACTIVITY_STORAGE, 0);
        sharedPreferences.edit().putInt(POSITION, 0).apply();
    }
}
