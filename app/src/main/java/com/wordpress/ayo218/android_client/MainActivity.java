package com.wordpress.ayo218.android_client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
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


//This class uses sockets to connect to IP addresss that you get.
//'Change the IP address to yours' 
class Connect2Server extends AsyncTask<String, Void, Void>{

    @Override
    protected Void doInBackground(String... strings) {
        try {
            try {
                Socket socket = new Socket("192.168.1.38", 8888);
                PrintWriter out = new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));

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
