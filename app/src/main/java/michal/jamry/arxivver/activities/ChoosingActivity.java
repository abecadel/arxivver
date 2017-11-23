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
import michal.jamry.arxivver.configuration.MainTimelineConfiguration;

/**
 * This activity allows us to create a query used in MainTimelineActivity.
 */
public class ChoosingActivity extends AppCompatActivity {

    private static final String[] HINTS = {"ti:", "au:", "abs:", "co:", "jr:", "cat:", "rn:", "stat.AP", "stat.CO", "stat.ML", "stat.ME", "stat.TH", "q-bio.BM", "q-bio.CB", "q-bio.GN", "q-bio.MN", "q-bio.NC", "q-bio.OT", "q-bio.PE", "q-bio.QM", "q-bio.SC", "q-bio.TO", "cs.AR", "cs.AI", "cs.CL", "cs.CC", "cs.CE", "cs.CG", "cs.GT", "cs.CV", "cs.CY", "cs.CR", "cs.DS", "cs.DB", "cs.DL", "cs.DM", "cs.DC", "cs.GL", "cs.GR", "cs.HC", "cs.IR", "cs.IT", "cs.LG", "cs.LO", "cs.MS", "cs.MA", "cs.MM", "cs.NI", "cs.NE", "cs.NA", "cs.OS", "cs.OH", "cs.PF", "cs.PL", "cs.RO", "cs.SE", "cs.SD", "cs.SC", "nlin.AO", "nlin.CG", "nlin.CD", "nlin.SI", "nlin.PS", "math.AG", "math.AT", "math.AP", "math.CT", "math.CA", "math.CO", "math.AC", "math.CV", "math.DG", "math.DS", "math.FA", "math.GM", "math.GN", "math.GT", "math.GR", "math.HO", "math.IT", "math.KT", "math.LO", "math.MP", "math.MG", "math.NT", "math.NA", "math.OA", "math.OC", "math.PR", "math.QA", "math.RT", "math.RA", "math.SP", "math.ST", "math.SG", "astro-ph", "cond-mat.dis-nn", "cond-mat.mes-hall", "cond-mat.mtrl-sci", "cond-mat.other", "cond-mat.soft", "cond-mat.stat-mech", "cond-mat.str-el", "cond-mat.supr-con", "gr-qc", "hep-ex", "hep-lat", "hep-ph", "hep-th", "math-ph", "nucl-ex", "nucl-th", "physics.acc-ph", "physics.ao-ph", "physics.atom-ph", "physics.atm-clus", "physics.bio-ph", "physics.chem-ph", "physics.class-ph", "physics.comp-ph", "physics.data-an", "physics.flu-dyn", "physics.gen-ph", "physics.geo-ph", "physics.hist-ph", "physics.ins-det", "physics.med-ph", "physics.optics", "physics.ed-ph", "physics.soc-ph", "physics.plasm-ph", "physics.pop-ph", "physics.space-ph", "quant-ph"};
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
