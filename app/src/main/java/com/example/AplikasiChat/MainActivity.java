package com.example.AplikasiChat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.AplikasiChat.Adapter.MessageListAdapter;
import com.example.AplikasiChat.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText userInput;
    RecyclerView recyclerView;
    MessageListAdapter messageAdapter;
    List<Message> MessageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.userInput);
        recyclerView = findViewById(R.id.rv_message_list);
        MessageList = new ArrayList<>();
        messageAdapter = new MessageListAdapter(MessageList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(messageAdapter);
        Message message0 = new Message("Selamat Datang, Kamu bisa menanyakan Hal di bawah ini : \n 1. Kamu mengantuk? \n 2. Kamu lapar?", true);
        MessageList.add(message0);

        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    Message message = new Message(userInput.getText().toString(), true);
                    MessageList.add(message);
                    if(userInput.getText().toString().equals("Kamu mengantuk?")){
                        Message message1 = new Message("Iya aku mengantuk",false);
                        MessageList.add(message1);

                    }
                    else if(userInput.getText().toString().equals("Kamu lapar?")){
                        Message message3 = new Message("Belikan makanan dongs", false);
                        MessageList.add(message3);
                    }
                    else {

                        Message message2 = new Message("Oke sayang", false);
                        MessageList.add(message2);
                    }
                    messageAdapter.notifyDataSetChanged();
                    if (!isLastVisible())
                        recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                }
                return false;
            }
        });
    }
    boolean isLastVisible() {
        LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
        int pos = layoutManager.findLastCompletelyVisibleItemPosition();
        int numItems = recyclerView.getAdapter().getItemCount();
        return (pos >= numItems);
    }
}
