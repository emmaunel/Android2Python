package com.wordpress.ayo218.android_client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText text = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit = text.getText().toString();
                new Connect2Server().execute(edit);
                text.getText().clear();
            }
        });
    }
}


class Connect2Server extends AsyncTask<String, Void, Void>{

    @Override
    protected Void doInBackground(String... strings) {
        try {
            String s = null;
            try {
                Socket socket = new Socket("192.168.1.46", 8888);

                Process p = Runtime.getRuntime().exec("ls");
                BufferedReader sInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                //BufferedReader sError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));

                System.out.println("This is the result");
                while ((s = sInput.readLine()) != null){
                    System.out.println(s);
                }


                out.print(strings[0]);
                out.flush();
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (Exception e){
            return null;
        }
        return null;
    }
}