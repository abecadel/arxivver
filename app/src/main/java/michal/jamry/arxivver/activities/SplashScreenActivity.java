package michal.jamry.arxivver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.configuration.MainTimelineConfiguration;

/**
 * "Starting" activity, it checks if it's the first time we've run the application and either runs the query configuration activity or the main timeline activity.
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (new MainTimelineConfiguration(getBaseContext()).isAlreadyConfigured()) {
            startActivity(new Intent(this, MainTimelineActivity.class));
        } else {
            startActivity(new Intent(this, ChoosingActivity.class));
        }

        finish();
    }
}
