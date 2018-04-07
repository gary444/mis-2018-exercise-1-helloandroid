package com.example.garyrendle.mis_1;

import android.content.Context;
import android.os.AsyncTask;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//ref from: https://androidkennel.org/android-networking-tutorial-with-asynctask/
public class GetContentTask extends AsyncTask<String, Void, String> {

    private TextView textView;
    private MainActivity activity;

    public GetContentTask(TextView textView, MainActivity activity){
        this.textView = textView;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {

        String inputString;
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);

//                int code = urlConnection.getResponseCode();
//                builder.append(code);
//                builder.append('\n');
            }
//            int code = urlConnection.getResponseCode();
//            builder.append(code);
//            builder.append('\n');

            urlConnection.disconnect();

            return builder.toString() + "\nEND";
        } catch (Exception  e) {
            //print error

            final String err = "Error: " + e.getMessage();

            //show toast message on ui thread
            //ref: https://stackoverflow.com/questions/3134683/android-toast-in-a-thread
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(activity, err, Toast.LENGTH_SHORT).show();
                }
            });

            return err;
        }
    }

    @Override
    protected void onPostExecute(String content) {
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(content);
    }
}
