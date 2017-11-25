package michal.jamry.arxivver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.adapters.ChoosingAdapter;
import michal.jamry.arxivver.configuration.MainTimelineConfiguration;

import static michal.jamry.arxivver.arxiv.ArxivApiQueryBuilder.HINTS;

/**
 * This activity allows us to create a query used in MainTimelineActivity.
 */
public class ChoosingActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private Button addButton;
    private Button saveButton;
    private ListView listView;
    private MainTimelineConfiguration mainTimelineConfiguration;
    private List<String> queryParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);

        ((AppCompatActivity)this).getSupportActionBar().setTitle("Settings");

        mainTimelineConfiguration = new MainTimelineConfiguration(getBaseContext());

        List<String> storedQueryParts = mainTimelineConfiguration.getQueryParts();
        if (storedQueryParts == null) {
            queryParts = new ArrayList<>();
        } else {
            queryParts = storedQueryParts;
        }

        autoCompleteTextView = findViewById(R.id.queryPartAutoCompleteTextView);
        addButton = findViewById(R.id.addQueryPartButton);
        listView = findViewById(R.id.queryPartsListView);
        saveButton = findViewById(R.id.saveButton);

        ChoosingAdapter adapter = new ChoosingAdapter(queryParts, getBaseContext());
        listView.setAdapter(adapter);

        addButton.setOnClickListener(view -> {
            String txt = autoCompleteTextView.getText().toString();
            if (txt.length() > 0) {
                autoCompleteTextView.setText("");
                adapter.addElement(txt.trim());
            }
        });

        saveButton.setOnClickListener(view -> {
            if (queryParts.size() > 0) {
                mainTimelineConfiguration.setQueryParts(queryParts);
                startActivity(new Intent(this, MainTimelineActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Add at least one query part", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<String> autocompleteAdapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_dropdown_item_1line, HINTS);
        autoCompleteTextView.setAdapter(autocompleteAdapter);
    }
}
