package com.example.garyrendle.mis_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //called to retrieve data from given URL
    public void retrieveData(View view){

        //get url from input box
        EditText editText = (EditText) findViewById(R.id.url_input);
        String url = editText.getText().toString();

        TextView textView = (TextView) findViewById(R.id.display);
        new GetContentTask(textView,  this).execute(url);

    }
}
