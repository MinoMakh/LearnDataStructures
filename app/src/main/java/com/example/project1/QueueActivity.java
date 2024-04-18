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

public class QueueActivity extends AppCompatActivity {

    String selectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_queue);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.queue), (v, insets) -> {
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
            String url = "https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // Youtube Queue Tutorial Button
        Button youtubeButton = findViewById(R.id.youtubeButton);
        youtubeButton.setOnClickListener(view -> {
            String url = "https://www.youtube.com/watch?v=nqXaPZi99JI";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // GeekForGeeks Tutorial Button
        Button geekForGeeksButton = findViewById(R.id.geekForGeeksTutorialButton);
        geekForGeeksButton.setOnClickListener(view -> {
            String url = "https://www.geeksforgeeks.org/queue-data-structure/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // Download Code Button
        Button downloadCodeButton = findViewById(R.id.downloadCodeButton);
        downloadCodeButton.setOnClickListener(view -> {
            String fileUrl = "https://www.mlsu.ac.in/econtents/326_05Queues.pdf";

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
        String[] options = getResources().getStringArray(R.array.queueListView);
        String[] descriptions = getResources().getStringArray(R.array.queueDescriptions);

        CustomAdapter adapter = new CustomAdapter(this, options, descriptions);
        listView.setAdapter(adapter);

        ListView listView2 = findViewById(R.id.list_view2);
        String[] descriptions2 = getResources().getStringArray(R.array.queueTimeComplexity);

        CustomAdapter adapter2 = new CustomAdapter(this, options, descriptions2);
        listView2.setAdapter(adapter2);
    }

    // Put the code implementation into a WebView
    private void setWebView() {
        WebView webView = findViewById(R.id.codeWebView);

        // String for the code implementation part
        String codeSnippet = "<html><head><style>" +
                "body { background-color: #474747; color: white; }" +
                "pre { margin: 0; }" +
                "</style></head><body><pre><code>" +
                "class Queue {\n" +
                "    private static int front, rear, capacity;\n" +
                "    private static int queue[];\n\n" +
                "    Queue(int size) {\n" +
                "        front = rear = 0;\n" +
                "        capacity = size;\n" +
                "        queue = new int[capacity];\n" +
                "    }\n\n" +
                "    // insert an element into the queue\n" +
                "    static void queueEnqueue(int item) {\n" +
                "        // check if the queue is full\n" +
                "        if (capacity == rear) {\n" +
                "            System.out.printf(\"\\nQueue is full\\n\");\n" +
                "            return;\n" +
                "        }\n\n" +
                "        // insert element at the rear\n" +
                "        else {\n" +
                "            queue[rear] = item;\n" +
                "            rear++;\n" +
                "        }\n" +
                "        return;\n" +
                "    }\n\n" +
                "    // remove an element from the queue\n" +
                "    static void queueDequeue() {\n" +
                "        // check if queue is empty\n" +
                "        if (front == rear) {\n" +
                "            System.out.printf(\"\\nQueue is empty\\n\");\n" +
                "            return;\n" +
                "        }\n\n" +
                "        // shift elements to the right by one place up to rear\n" +
                "        else {\n" +
                "            for (int i = 0; i < rear - 1; i++) {\n" +
                "                queue[i] = queue[i + 1];\n" +
                "            }\n" +
                "\n" +
                "            // set queue[rear] to 0\n" +
                "            if (rear < capacity)\n" +
                "                queue[rear] = 0;\n" +
                "\n" +
                "            // decrement rear\n" +
                "            rear--;\n" +
                "        }\n" +
                "        return;\n" +
                "    }\n\n" +
                "    // print queue elements\n" +
                "    static void queueDisplay() {\n" +
                "        int i;\n" +
                "        if (front == rear) {\n" +
                "            System.out.printf(\"Queue is Empty\\n\");\n" +
                "            return;\n" +
                "        }\n\n" +
                "        // traverse front to rear and print elements\n" +
                "        for (i = front; i < rear; i++) {\n" +
                "            System.out.printf(\" %d \", queue[i]);\n" +
                "        }\n" +
                "        return;\n" +
                "    }\n\n" +
                "    // print front of queue\n" +
                "    static void queueFront() {\n" +
                "        if (front == rear) {\n" +
                "            System.out.printf(\"Queue is Empty\\n\");\n" +
                "            return;\n" +
                "        }\n" +
                "        System.out.printf(\"\\nFront Element of the queue: %d\\n\", queue[front]);\n" +
                "        return;\n" +
                "    }\n" +
                "}\n\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        // Create a queue of capacity 4\n" +
                "        Queue q = new Queue(4);\n" +
                "\n" +
                "        System.out.println(\"Initial Queue:\");\n" +
                "        // print Queue elements\n" +
                "        q.queueDisplay();\n" +
                "\n" +
                "        // inserting elements in the queue\n" +
                "        q.queueEnqueue(10);\n" +
                "        q.queueEnqueue(30);\n" +
                "        q.queueEnqueue(50);\n" +
                "        q.queueEnqueue(70);\n" +
                "\n" +
                "        // print Queue elements\n" +
                "        System.out.println(\"Queue after Enqueue Operation:\");\n" +
                "        q.queueDisplay();\n" +
                "\n" +
                "        // print front of the queue\n" +
                "        q.queueFront();\n" +
                "\n" +
                "        // insert element in the queue\n" +
                "        q.queueEnqueue(90);\n" +
                "\n" +
                "        // print Queue elements\n" +
                "        q.queueDisplay();\n" +
                "\n" +
                "        q.queueDequeue();\n" +
                "        q.queueDequeue();\n" +
                "        System.out.printf(\"\\nQueue after two dequeue operations:\");\n" +
                "\n" +
                "        // print Queue elements\n" +
                "        q.queueDisplay();\n" +
                "\n" +
                "        // print front of the queue\n" +
                "        q.queueFront();\n" +
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