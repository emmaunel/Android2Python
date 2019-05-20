package com.wordpress.ayo218.android_client;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private EditText ip_addr, port;
    public TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip_addr = findViewById(R.id.ip);
        port = findViewById(R.id.port);
        status = findViewById(R.id.status);
        Button connect = findViewById(R.id.connect);

        connect.setOnClickListener(view -> {
            // TODO: 5/19/2019 Verify input
            String ip = this.ip_addr.getText().toString().trim();
            String port = this.port.getText().toString().trim();

            new Connect2Server().execute(ip, port);
        });
    }
}


//This class uses sockets to connect to IP address that you get.
//'Change the IP address to yours' 
class Connect2Server extends AsyncTask<String, Void, Void>{


    @Override
    protected Void doInBackground(String... strings) {
        try {
            String results;
            try {
                Socket socket = new Socket(strings[0], Integer.parseInt(strings[1]));

                // if connected, good. Move to the next screen
                if (socket.isConnected()){
                    System.out.println("Connected");
                }

                // Sends commands to server
                PrintWriter commands = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

                // Receives reply from server
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println("This is the result");

//                while ((s = sInput.readLine()) != null){
//                    System.out.println(s);
//                }
//
//
//                out.print(strings[0]);
//                out.flush();
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (Exception e){
            return null;
        }
        return null;
    }
}
