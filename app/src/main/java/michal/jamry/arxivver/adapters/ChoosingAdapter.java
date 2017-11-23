package michal.jamry.arxivver.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import michal.jamry.arxivver.R;

/**
 * The type Choosing adapter.
 */
public class ChoosingAdapter extends BaseAdapter implements ListAdapter {
    private List<String> list;
    private Context context;

    /**
     * Instantiates a new Choosing adapter.
     *
     * @param list    the list
     * @param context the context
     */
    public ChoosingAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     * Add element.
     *
     * @param txt the txt
     */
    public void addElement(String txt) {
        list.add(txt);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);

    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_choosing_list_entry, null);
        }

        TextView listItemText = view.findViewById(R.id.chosenParamTextView);
        listItemText.setText(list.get(i));

        Button deleteBtn = view.findViewById(R.id.removeChosenParamButton);
        deleteBtn.setOnClickListener(clickedView -> {
            list.remove(i);
            notifyDataSetChanged();
            Toast.makeText(context, "Query part removed", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
