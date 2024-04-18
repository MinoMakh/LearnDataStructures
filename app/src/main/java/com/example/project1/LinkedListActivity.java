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

public class LinkedListActivity extends AppCompatActivity {

    String selectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_linked_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linkedList), (v, insets) -> {
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
            String url = "https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // Youtube LinkedList Tutorial Button
        Button youtubeButton = findViewById(R.id.youtubeButton);
        youtubeButton.setOnClickListener(view -> {
            String url = "https://www.youtube.com/watch?v=N6dOwBde7-M";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // GeekForGeeks Tutorial Button
        Button geekForGeeksButton = findViewById(R.id.geekForGeeksTutorialButton);
        geekForGeeksButton.setOnClickListener(view -> {
            String url = "https://www.geeksforgeeks.org/data-structures/linked-list/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // Download Code Button
        Button downloadCodeButton = findViewById(R.id.downloadCodeButton);
        downloadCodeButton.setOnClickListener(view -> {
            String fileUrl = "https://www.lkouniv.ac.in/site/writereaddata/siteContent/202003251324427324himanshu_Linked_List.pdf";

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
        String[] options = getResources().getStringArray(R.array.linkedListView);
        String[] descriptions = getResources().getStringArray(R.array.linkedDescriptions);

        CustomAdapter adapter = new CustomAdapter(this, options, descriptions);
        listView.setAdapter(adapter);

        ListView listView2 = findViewById(R.id.list_view2);
        String[] descriptions2 = getResources().getStringArray(R.array.linkedTimeComplexity);

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
                "public class SinglyLinkedList {    \n" +
                "    //Represent a node of the singly linked list    \n" +
                "    class Node{    \n" +
                "        int data;    \n" +
                "        Node next;    \n" +
                "            \n" +
                "        public Node(int data) {    \n" +
                "            this.data = data;    \n" +
                "            this.next = null;    \n" +
                "        }    \n" +
                "    }    \n" +
                "     \n" +
                "    //Represent the head and tail of the singly linked list    \n" +
                "    public Node head = null;    \n" +
                "    public Node tail = null;    \n" +
                "        \n" +
                "    //addNode() will add a new node to the list    \n" +
                "    public void addNode(int data) {    \n" +
                "        //Create a new node    \n" +
                "        Node newNode = new Node(data);    \n" +
                "            \n" +
                "        //Checks if the list is empty    \n" +
                "        if(head == null) {    \n" +
                "            //If list is empty, both head and tail will point to new node    \n" +
                "            head = newNode;    \n" +
                "            tail = newNode;    \n" +
                "        }    \n" +
                "        else {    \n" +
                "            //newNode will be added after tail such that tail's next will point to newNode    \n" +
                "            tail.next = newNode;    \n" +
                "            //newNode will become new tail of the list    \n" +
                "            tail = newNode;    \n" +
                "        }    \n" +
                "    }    \n" +
                "        \n" +
                "    //display() will display all the nodes present in the list    \n" +
                "    public void display() {    \n" +
                "        //Node current will point to head    \n" +
                "        Node current = head;    \n" +
                "            \n" +
                "        if(head == null) {    \n" +
                "            System.out.println(\"List is empty\");    \n" +
                "            return;    \n" +
                "        }    \n" +
                "        System.out.println(\"Nodes of singly linked list: \");    \n" +
                "        while(current != null) {    \n" +
                "            //Prints each node by incrementing pointer    \n" +
                "            System.out.print(current.data + \" \");    \n" +
                "            current = current.next;    \n" +
                "        }    \n" +
                "        System.out.println();    \n" +
                "    }    \n" +
                "        \n" +
                "    public static void main(String[] args) {    \n" +
                "            \n" +
                "        SinglyLinkedList sList = new SinglyLinkedList();    \n" +
                "            \n" +
                "        //Add nodes to the list    \n" +
                "        sList.addNode(1);    \n" +
                "        sList.addNode(2);    \n" +
                "        sList.addNode(3);    \n" +
                "        sList.addNode(4);    \n" +
                "            \n" +
                "        //Displays the nodes present in the list    \n" +
                "        sList.display();    \n" +
                "    }    \n" +
                "}    \n" +
                "</code></pre></body></html>";

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