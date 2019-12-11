package com.wbh.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChatActivity extends AppCompatActivity {

    private String channelID = "CHANNEL_ID_FROM_YOUR_SCALEDRONE_DASHBOARD";
    private String roomName = "observable-room";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editText = (EditText) findViewById(R.id.editText);
    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            final RecyclerView rv = (RecyclerView) findViewById(R.id.messages_view);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(new MessageAdapter(message));
            //scaledrone.publish("observable-room", message);
            editText.getText().clear();
        }
    }
}
