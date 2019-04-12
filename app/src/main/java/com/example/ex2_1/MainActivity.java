package com.example.ex2_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView messageViews;
    Button sendMessage;
    EditText writeMessage;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeMessage = (EditText) findViewById(R.id.editText2);
        sendMessage = (Button) findViewById(R.id.button3);
        messageViews = (RecyclerView) findViewById(R.id.rec1);

//        messageViews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
//                false));
        messageViews.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        myAdapter = new MyAdapter();

        messageViews.setAdapter(myAdapter);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageToSend = writeMessage.getText().toString();
                writeMessage.setText("");
                if (messageToSend.length() == 0) {
                    Toast.makeText(getApplicationContext(), "You can't send an empty message, " +
                            "Oh silly!", Toast.LENGTH_LONG).show();
                    return;
                }
                myAdapter.addItem(messageToSend);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("wrote_message", writeMessage.getText().toString());
        savedInstanceState.putStringArrayList("messages", myAdapter.data);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String wrote_message = savedInstanceState.getString("wrote_message");
        myAdapter.data = savedInstanceState.getStringArrayList("messages");
        writeMessage.setText(wrote_message);
        myAdapter.supportConfigurationChange();
    }
}
