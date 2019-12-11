package com.wbh.myapplication;

import android.app.AlertDialog;

import android.os.CountDownTimer;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationActions;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private String message;

    public MessageAdapter(String message) {
        this.message = message ;
    }




    private final List<Pair<String, String>> characters = Arrays.asList(
            Pair.create("Lyra Belacqua", message ));

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_message, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, String> pair = characters.get(position);
        holder.display(pair);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView description;
        private final TextView description2;
        private Pair<String, String> currentPair;

        public MyViewHolder(final View itemView) {
            super(itemView);

            description = ((TextView) itemView.findViewById(R.id.message_body));

            description2 = ((TextView) itemView.findViewById(R.id.message_body2));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setMessage(currentPair.second)
                            .show();
                }
            });
        }

        public void display(Pair<String, String> pair) {
            currentPair = pair;
            description.setText(message);
            description2.setText("...");
            new CountDownTimer(3000, 1000) {
                public void onTick(long millisUntilFinished) {
                    //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    description2.setText(message);
                }
            }.start();

        }
    }

}