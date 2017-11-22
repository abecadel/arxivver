package michal.jamry.arxivver.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import michal.jamry.arxivver.R;
import michal.jamry.arxivver.configuration.MainTimelineConfiguration;

public class ChoosingActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private Button addButton;
    private ListView listView;

    private List<String> queryParts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);

        List<String> storedQueryParts = MainTimelineConfiguration.getQueryParts();
        if (storedQueryParts == null) {
            queryParts = new ArrayList<>();
        } else {
            queryParts = storedQueryParts;
        }

        autoCompleteTextView = findViewById(R.id.queryPartAutoCompleteTextView);
        addButton = findViewById(R.id.addQueryPartButton);
        listView = findViewById(R.id.queryPartsListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, queryParts);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(view -> {
            String txt = autoCompleteTextView.getText().toString();
            if (txt.length() > 0) {
                autoCompleteTextView.setText("");
                queryParts.add(txt.trim());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
