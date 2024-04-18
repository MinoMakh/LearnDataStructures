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

public class StackActivity extends AppCompatActivity {

    String selectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stack);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.stack), (v, insets) -> {
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
            String url = "https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // Youtube Stack Tutorial Button
        Button youtubeButton = findViewById(R.id.youtubeButton);
        youtubeButton.setOnClickListener(view -> {
            String url = "https://www.youtube.com/watch?v=KInG04mAjO0";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // GeekForGeeks Tutorial Button
        Button geekForGeeksButton = findViewById(R.id.geekForGeeksTutorialButton);
        geekForGeeksButton.setOnClickListener(view -> {
            String url = "https://www.geeksforgeeks.org/stack-data-structure/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // Download Code Button
        Button downloadCodeButton = findViewById(R.id.downloadCodeButton);
        downloadCodeButton.setOnClickListener(view -> {
            String fileUrl = "https://www.mlsu.ac.in/econtents/325_04Stacks.pdf";

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
        String[] options = getResources().getStringArray(R.array.stackListView);
        String[] descriptions = getResources().getStringArray(R.array.stackDescriptions);

        CustomAdapter adapter = new CustomAdapter(this, options, descriptions);
        listView.setAdapter(adapter);

        ListView listView2 = findViewById(R.id.list_view2);
        String[] descriptions2 = getResources().getStringArray(R.array.stackTimeComplexity);

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
                "// Stack implementation in Java\n\n" +
                "class Stack {\n\n" +
                "  // store elements of stack\n" +
                "  private int arr[];\n" +
                "  // represent top of stack\n" +
                "  private int top;\n" +
                "  // total capacity of the stack\n" +
                "  private int capacity;\n\n" +
                "  // Creating a stack\n" +
                "  Stack(int size) {\n" +
                "    // initialize the array\n" +
                "    // initialize the stack variables\n" +
                "    arr = new int[size];\n" +
                "    capacity = size;\n" +
                "    top = -1;\n" +
                "  }\n\n" +
                "  // push elements to the top of stack\n" +
                "  public void push(int x) {\n" +
                "    if (isFull()) {\n" +
                "      System.out.println(\"Stack OverFlow\");\n" +
                "      // terminates the program\n" +
                "      System.exit(1);\n" +
                "    }\n\n" +
                "    // insert element on top of stack\n" +
                "    System.out.println(\"Inserting \" + x);\n" +
                "    arr[++top] = x;\n" +
                "  }\n\n" +
                "  // pop elements from top of stack\n" +
                "  public int pop() {\n\n" +
                "    // if stack is empty\n" +
                "    // no element to pop\n" +
                "    if (isEmpty()) {\n" +
                "      System.out.println(\"STACK EMPTY\");\n" +
                "      // terminates the program\n" +
                "      System.exit(1);\n" +
                "    }\n\n" +
                "    // pop element from top of stack\n" +
                "    return arr[top--];\n" +
                "  }\n\n" +
                "  // return size of the stack\n" +
                "  public int getSize() {\n" +
                "    return top + 1;\n" +
                "  }\n\n" +
                "  // check if the stack is empty\n" +
                "  public Boolean isEmpty() {\n" +
                "    return top == -1;\n" +
                "  }\n\n" +
                "  // check if the stack is full\n" +
                "  public Boolean isFull() {\n" +
                "    return top == capacity - 1;\n" +
                "  }\n\n" +
                "  // display elements of stack\n" +
                "  public void printStack() {\n" +
                "    for (int i = 0; i <= top; i++) {\n" +
                "      System.out.print(arr[i] + \", \");\n" +
                "    }\n" +
                "  }\n\n" +
                "  public static void main(String[] args) {\n" +
                "    Stack stack = new Stack(5);\n\n" +
                "    stack.push(1);\n" +
                "    stack.push(2);\n" +
                "    stack.push(3);\n\n" +
                "    System.out.print(\"Stack: \");\n" +
                "    stack.printStack();\n\n" +
                "    // remove element from stack\n" +
                "    stack.pop();\n" +
                "    System.out.println(\"\\nAfter popping out\");\n" +
                "    stack.printStack();\n\n" +
                "  }\n" +
                "}" +
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