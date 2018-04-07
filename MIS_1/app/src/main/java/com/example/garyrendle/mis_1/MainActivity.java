package com.example.garyrendle.mis_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //called to retrieve data from given URL
    // ref from: https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html
    public void retrieveData(View view){

//        try {
//
//            EditText urlField  = (EditText)findViewById(R.id.url_input);
//            String url_string = urlField.getText().toString();
//
//
//            //URL url = new URL(url_string);
//            URL url = new URL("https://www.york.ac.uk/teaching/cws/wws/webpage1.html");
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(url.openStream()));
//
//
//            String inputLine;
//            String allInput = "C";
//            allInput = in.readLine();
////            while ((inputLine = in.readLine()) != null)
////                allInput += inputLine;
////
////            in.close();
//
//            TextView textView = (TextView)findViewById(R.id.display);
//
//            String test = String.valueOf(allInput.length());
//            textView.setText(test);
//
//        }
//        catch (Exception e){
//            //error message
//
//        }

        EditText editText = (EditText) findViewById(R.id.url_input);
        String url = editText.getText().toString();

        TextView textView = (TextView) findViewById(R.id.display);
        new GetContentTask(textView,  this).execute(url);

    }
}
