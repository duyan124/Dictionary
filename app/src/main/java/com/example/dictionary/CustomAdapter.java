package com.example.dictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapter extends ArrayAdapter<Vocabulary> {
    private final ArrayList<Vocabulary> arrayList; //search
    private Context context;
    private ArrayList<Vocabulary> Vocabularys;
    private int layoutResource;
    private Filter mFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            return ((Vocabulary)resultValue).getWord();
        }
        @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null) {
            ArrayList<Vocabulary> suggestions = new ArrayList<Vocabulary>();
            for (Vocabulary vocabulary : arrayList) {
                if (vocabulary.getWord().toLowerCase().contains(constraint.toString().toLowerCase())) {
                    suggestions.add(vocabulary);
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        clear();
        if (results != null && results.count > 0) {
            // we have filtered results
            addAll((ArrayList<Vocabulary>) results.values);
        } else {
            // no filter, add entire original list back in
            addAll(Vocabularys);
        }
        notifyDataSetChanged();
    }
};

    public CustomAdapter(Context context, int resource,ArrayList<Vocabulary> objects) {
        super(context, resource, objects);
        this.context=context;
        this.Vocabularys=objects;
        this.layoutResource = resource;
        arrayList = new ArrayList<>();
        this.arrayList.addAll(Vocabularys);
    }
    @NonNull
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup){
        LayoutInflater inflater= LayoutInflater.from(context);
        view=inflater.inflate(layoutResource,null);
        TextView name = (TextView) view.findViewById(R.id.tv_nameVocabulary);
        name.setText(Vocabularys.get(i).getWord());
        return view;
    }
    @Override
    public Filter getFilter() {
        return mFilter;
    }
}
