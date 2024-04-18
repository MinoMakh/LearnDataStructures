package com.example.project1;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HashMapActivity extends AppCompatActivity {

    String selectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hash_map);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hashmap), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setWebView();
        setListViews();
        setButtons();
        setSpinner();


    }

    // Creating the dropdown menu
    private void setSpinner() {
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.options_array, R.layout.spinner_item_layout);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // Redirect into the selected option activity
                    switch (selectedOption) {
                        case "Main":
                            openMainActivity();
                            break;
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
                    return true;
                }
                return false;
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Save the selected Option
                selectedOption = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // Setting up the buttons with their click listeners
    private void setButtons() {
        // Check Documentation Button
        Button documentationButton = findViewById(R.id.documentationButton);
        documentationButton.setOnClickListener(view -> {
            String url = "https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // Youtube HashMap Tutorial Button
        Button youtubeButton = findViewById(R.id.youtubeButton);
        youtubeButton.setOnClickListener(view -> {
            String url = "https://www.youtube.com/watch?v=H62Jfv1DJlU";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // GeekForGeeks Tutorial Button
        Button geekForGeeksButton = findViewById(R.id.geekForGeeksTutorialButton);
        geekForGeeksButton.setOnClickListener(view -> {
            String url = "https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/#what-is-hashmap";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // Download Code Button
        Button downloadCodeButton = findViewById(R.id.downloadCodeButton);
        downloadCodeButton.setOnClickListener(view -> {
            String fileUrl = "https://www3.cs.stonybrook.edu/~pfodor/courses/CSE219/L09-hashmap.pdf";

            // Create a DownloadManager.Request with the file URL
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileUrl));
            request.setTitle("File Download"); // Set title for notification
            request.setDescription("Downloading..."); // Set description for notification
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); // Show notification after download completes
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "file.pdf"); // Set destination directory and file name

            // Get the download service and enqueue the download request
            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
            Toast.makeText(this, "File downloading...", Toast.LENGTH_SHORT).show();
        });

    }

    // Setting up the listviews with its descriptions
    private void setListViews() {
        ListView listView = findViewById(R.id.list_view);
        String[] options = getResources().getStringArray(R.array.hashListView);
        String[] descriptions = getResources().getStringArray(R.array.hashDescriptions);

        CustomAdapter adapter = new CustomAdapter(this, options, descriptions);
        listView.setAdapter(adapter);

        ListView listView2 = findViewById(R.id.list_view2);
        String[] descriptions2 = getResources().getStringArray(R.array.hashTimeComplexity);

        CustomAdapter adapter2 = new CustomAdapter(this, options, descriptions2);
        listView2.setAdapter(adapter2);
    }

    // Put the code implementation into a WebView
    private void setWebView() {
        WebView webView = findViewById(R.id.codeWebView);

        // String for the code implementation part
        String codeSnippet = "<html><head><style>" +
                "body { background-color: #474747; color: white; }" +
                "pre { margin: 0; }" + // Remove default margin of <pre> tag
                "</style></head><body><pre><code>" +
                "class MyMap<K, V> {\n" +
                "    private Entry<K, V>[] buckets;\n" +
                "    private static final int INITIAL_CAPACITY = 1 << 4; // 16\n\n" +
                "    private int size = 0;\n\n" +
                "    public MyMap() {\n" +
                "        this(INITIAL_CAPACITY);\n" +
                "    }\n\n" +
                "    public MyMap(int capacity) {\n" +
                "        this.buckets = new Entry[capacity];\n" +
                "    }\n\n" +
                "    public void put(K key, V value) {\n" +
                "        Entry<K, V> entry = new Entry<>(key, value, null);\n\n" +
                "        int bucket = getHash(key) % getBucketSize();\n\n" +
                "        Entry<K, V> existing = buckets[bucket];\n" +
                "        if (existing == null) {\n" +
                "            buckets[bucket] = entry;\n" +
                "            size++;\n" +
                "        } else {\n" +
                "            // compare the keys see if key already exists\n" +
                "            while (existing.next != null) {\n" +
                "                if (existing.key.equals(key)) {\n" +
                "                    existing.value = value;\n" +
                "                    return;\n" +
                "                }\n" +
                "                existing = existing.next;\n" +
                "            }\n\n" +
                "            if (existing.key.equals(key)) {\n" +
                "                existing.value = value;\n" +
                "            } else {\n" +
                "                existing.next = entry;\n" +
                "                size++;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n\n" +
                "    public V get(K key) {\n" +
                "        Entry<K, V> bucket = buckets[getHash(key) % getBucketSize()];\n\n" +
                "        while (bucket != null) {\n" +
                "            if (bucket.key.equals(key)) {\n" +
                "                return bucket.value;\n" +
                "            }\n" +
                "            bucket = bucket.next;\n" +
                "        }\n" +
                "        return null;\n" +
                "    }\n" +
                "}</code></pre></body></html>";
        webView.loadDataWithBaseURL(null, codeSnippet, "text/html", "UTF-8", null);
    }

    // Intents for redirection
    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

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
}