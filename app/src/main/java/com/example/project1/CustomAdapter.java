package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<String> {

    private String[] descriptions;

    public CustomAdapter(Context context, String[] options, String[] descriptions) {
        super(context, 0, options);
        this.descriptions = descriptions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_layout, parent, false);
        }

        String option = getItem(position);
        String description = descriptions[position];

        TextView optionTextView = listItemView.findViewById(R.id.option_text);
        TextView descriptionTextView = listItemView.findViewById(R.id.description_text);

        optionTextView.setText(option);
        descriptionTextView.setText(description);


        return listItemView;
    }
}
