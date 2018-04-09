package com.example.garyrendle.mis_1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    public EditText url_input;
    public TextView html_display;
    public ToggleButton viewType;
    public WebView webView;

    private boolean viewAsPlainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url_input = findViewById(R.id.url_input);
        html_display =  findViewById(R.id.display);
        viewType = findViewById(R.id.viewType);
        webView = findViewById(R.id.webview);

        viewAsPlainText = true;
        viewType.setChecked(viewAsPlainText);

        html_display.setVisibility(View.VISIBLE);
        webView.setVisibility(View.INVISIBLE);


    }

    //called to retrieve data from given URL
    public void retrieveData(View view){

        // ref: https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
        View v = this.getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

        //get url from input box
        String url = url_input.getText().toString();
        new GetContentTask(html_display,  this).execute(url);


        //ref: https://stackoverflow.com/questions/3624171/how-to-show-the-html-contents-to-the-webview-using-android
        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        String html = html_display.getText().toString();

        webView.loadData(html, mimeType, encoding);


    }

    public void changeView(View view){

        viewAsPlainText = !viewAsPlainText;

        if (viewAsPlainText){
            html_display.setVisibility(View.VISIBLE);
            webView.setVisibility(View.INVISIBLE);

        }

        else{
            html_display.setVisibility(View.INVISIBLE);
            webView.setVisibility(View.VISIBLE);
        }
    }
}
