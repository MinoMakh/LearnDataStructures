package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setMenuOptions();
        setSpinner();

        // Checking if the name is saved
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        name = sharedPreferences.getString("name", "");

        if (!name.isEmpty()) {
            TextView textview = findViewById(R.id.welcomeText);
            textview.setText("Welcome Back " + name + "!");
        } else {
            // If not show popup and ask for name
            showPopup();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Welcome Back " + name + "!", Toast.LENGTH_SHORT).show();
    }

    // Creating the dropdown menu
    private void setSpinner() {
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.options_array, R.layout.spinner_item_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Redirect to selected activity from dropdown menu
                String selectedOption = parent.getItemAtPosition(position).toString();
                switch (selectedOption) {
                    case "Stack":
                        openStackActivity();
                        break;
                    case "Queue":
                        openQueueActivity();
                        break;
                    case "Tree":
                        openTreeActivity();
                        break;
                    case "LinkedList":
                        openLinkedListActivity();
                        break;
                    case "Array":
                        openArrayActivity();
                        break;
                    case "HashMap":
                        openHashMapActivity();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    // Create the menu options
    private void setMenuOptions() {
        GridLayout gridLayout = findViewById(R.id.grid_layout);

        // Inflate the custom layout for stack option
        View stackOptionView = LayoutInflater.from(this).inflate(R.layout.menu_option_layout, null);
        LinearLayout stackLinearLayout = stackOptionView.findViewById(R.id.menu_option_layout);
        stackLinearLayout.setBackgroundResource(R.drawable.rounded_corners_blue);
        TextView stackTitle = stackOptionView.findViewById(R.id.menu_option_title);
        stackTitle.setText("Stack");
        ImageView stackIcon = stackOptionView.findViewById(R.id.menu_option_icon);
        stackIcon.setImageResource(R.drawable.stack_icon);
        gridLayout.addView(stackOptionView);

        // Inflate the custom layout for queue option
        View queueOptionView = LayoutInflater.from(this).inflate(R.layout.menu_option_layout, null);
        LinearLayout queueLinearLayout = queueOptionView.findViewById(R.id.menu_option_layout);
        queueLinearLayout.setBackgroundResource(R.drawable.rounded_corners_blue);
        TextView queueTitle = queueOptionView.findViewById(R.id.menu_option_title);
        queueTitle.setText("Queue");
        ImageView queueIcon = queueOptionView.findViewById(R.id.menu_option_icon);
        queueIcon.setImageResource(R.drawable.queue_icon);
        gridLayout.addView(queueOptionView);

        // Inflate the custom layout for tree option
        View treeOptionView = LayoutInflater.from(this).inflate(R.layout.menu_option_layout, null);
        LinearLayout treeLinearLayout = treeOptionView.findViewById(R.id.menu_option_layout);
        treeLinearLayout.setBackgroundResource(R.drawable.rounded_corners_blue);
        TextView treeTitle = treeOptionView.findViewById(R.id.menu_option_title);
        treeTitle.setText("Tree");
        ImageView treeIcon = treeOptionView.findViewById(R.id.menu_option_icon);
        treeIcon.setImageResource(R.drawable.tree_icon);
        gridLayout.addView(treeOptionView);

        // Inflate the custom layout for linked list option
        View linkedOptionView = LayoutInflater.from(this).inflate(R.layout.menu_option_layout, null);
        LinearLayout linkedLinearLayout = linkedOptionView.findViewById(R.id.menu_option_layout);
        linkedLinearLayout.setBackgroundResource(R.drawable.rounded_corners_blue);
        TextView linkedTitle = linkedOptionView.findViewById(R.id.menu_option_title);
        linkedTitle.setText("LinkedList");
        ImageView linkedIcon = linkedOptionView.findViewById(R.id.menu_option_icon);
        linkedIcon.setImageResource(R.drawable.linked_list_icon);
        gridLayout.addView(linkedOptionView);

        // Inflate the custom layout for array option
        View arrayOptionView = LayoutInflater.from(this).inflate(R.layout.menu_option_layout, null);
        LinearLayout arrayLinearLayout = arrayOptionView.findViewById(R.id.menu_option_layout);
        arrayLinearLayout.setBackgroundResource(R.drawable.rounded_corners_blue);
        TextView arrayTitle = arrayOptionView.findViewById(R.id.menu_option_title);
        arrayTitle.setText("Array");
        ImageView arrayIcon = arrayOptionView.findViewById(R.id.menu_option_icon);
        arrayIcon.setImageResource(R.drawable.array_icon);
        gridLayout.addView(arrayOptionView);

        // Inflate the custom layout for hashmap option
        View hashmapOptionView = LayoutInflater.from(this).inflate(R.layout.menu_option_layout, null);
        LinearLayout hashmapLinearLayout = hashmapOptionView.findViewById(R.id.menu_option_layout);
        hashmapLinearLayout.setBackgroundResource(R.drawable.rounded_corners_blue);
        TextView hashmapTitle = hashmapOptionView.findViewById(R.id.menu_option_title);
        hashmapTitle.setText("HashMap");
        ImageView hashmapIcon = hashmapOptionView.findViewById(R.id.menu_option_icon);
        hashmapIcon.setImageResource(R.drawable.hashmap_icon);
        gridLayout.addView(hashmapOptionView);

        // Redirecting to corresponding activities when clicking the containers
        stackLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a new activity for stack
                openStackActivity();
            }
        });

        queueLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a new activity for queue
                openQueueActivity();
            }
        });

        treeLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a new activity for tree
                openTreeActivity();
            }
        });

        linkedLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a new activity for linked list
                openLinkedListActivity();
            }
        });

        arrayLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a new activity for array
                openArrayActivity();
            }
        });

        hashmapLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a new activity for hashmap
                openHashMapActivity();
            }
        });
    }

    // Intents for redirection
    private void openStackActivity() {
        Intent intent = new Intent(this, StackActivity.class);
        startActivity(intent);
    }

    private void openQueueActivity() {
        Intent intent = new Intent(this, QueueActivity.class);
        startActivity(intent);
    }

    private void openTreeActivity() {
        Intent intent = new Intent(this, TreeActivity.class);
        startActivity(intent);
    }

    private void openLinkedListActivity() {
        Intent intent = new Intent(this, LinkedListActivity.class);
        startActivity(intent);
    }

    private void openArrayActivity() {
        Intent intent = new Intent(this, ArrayActivity.class);
        startActivity(intent);
    }

    private void openHashMapActivity() {
        Intent intent = new Intent(this, HashMapActivity.class);
        startActivity(intent);
    }

    // Show the popup
    private void showPopup() {
        View popupView = getLayoutInflater().inflate(R.layout.popup_name, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(popupView);

        EditText editTextUsername = popupView.findViewById(R.id.editTextName);
        Button buttonSave = popupView.findViewById(R.id.buttonSave);

        AlertDialog dialog = builder.create();

        buttonSave.setOnClickListener(view -> {
            String name = editTextUsername.getText().toString().trim();

            // Save the username to SharedPreferences
            saveName(name);

            dialog.dismiss();
        });

        dialog.show();
    }

    // Save the username to SharedPreferences
    private void saveName(String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.apply();
        Toast.makeText(this, "Thank you! " + name, Toast.LENGTH_SHORT).show();
    }


}
