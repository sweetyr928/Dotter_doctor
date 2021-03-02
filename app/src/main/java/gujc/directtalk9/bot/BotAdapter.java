package gujc.directtalk9.bot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;


import java.util.ArrayList;

import gujc.directtalk9.R;
import gujc.directtalk9.model.Chatbot;

public class BotAdapter extends RecyclerView.Adapter<BotAdapter.Holder> {
    public static final int msgtype_left = 0;
    public static final int msgtype_right = 1;
    private String fuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private Context context;
    private ArrayList<Chatbot> arrayList = new ArrayList<>();
    String mcurrent = "start";

    public BotAdapter(ArrayList<Chatbot> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BotAdapter.Holder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        if (viewType == msgtype_left) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_botbtn, parent, false);
            final Button button1 = (Button) view.findViewById(R.id.button1);
            final Button button2 = (Button) view.findViewById(R.id.button2);
            final Button button3 = (Button) view.findViewById(R.id.button3);
            final Button button4 = (Button) view.findViewById(R.id.button4);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Chatbot chatbot = new Chatbot(fuser, mcurrent);
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent);
                    switch (chatbot.getCurrent()) {
                        case "start": {
                            mcurrent = "문진";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setText("101");
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            notifyDataSetChanged();
                            break;
                        }
                        case "처방전": {
                            mcurrent = "yes";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            notifyDataSetChanged();
                            break;
                        }
                        case "문진": {
                            mcurrent = "상체";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            notifyDataSetChanged();
                            break;
                        }
                        case "상체": {
                            mcurrent = "머리";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            notifyDataSetChanged();
                            break;
                        }
                        case "하체": {
                            mcurrent = "허벅지";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            notifyDataSetChanged();
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Chatbot chatbot = new Chatbot(fuser, mcurrent);
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent + "선택");
                    switch (chatbot.getCurrent()) {
                        case "start": {
                            mcurrent = "처방전";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            notifyDataSetChanged();
                            break;
                        }
                        case "문진": {
                            mcurrent = "하체";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            notifyDataSetChanged();
                            break;
                        }
                        case "상체": {
                            mcurrent = "가슴";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            notifyDataSetChanged();
                            break;
                        }
                        case "하체": {
                            mcurrent = "종아리";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            notifyDataSetChanged();
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Chatbot chatbot = new Chatbot(fuser, mcurrent);
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent + "입니다3");
                    switch (chatbot.getCurrent()) {
                        case "상체": {
                            mcurrent = "배";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            notifyDataSetChanged();
                            break;
                        }
                        case "하체": {
                            mcurrent = "발";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            notifyDataSetChanged();
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Chatbot chatbot = new Chatbot(fuser, mcurrent);
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent + "입니다4");
                    switch (chatbot.getCurrent()) {
                        case "start": {
                            mcurrent = "201";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            notifyDataSetChanged();
                            break;
                        }
                        default: {
                            break;
                        }
                    }
                }
            });

            return new Holder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_botmsg_right, parent, false);
            return new Holder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final BotAdapter.Holder holder, int position) {
        holder.botname.setText(arrayList.get(position).getName());
        holder.botcurrent.setText(arrayList.get(position).getCurrent());
//        holder.botname.setVisibility(View.GONE);

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curname = holder.botname.getText().toString();
                Toast.makeText(view.getContext(), curname, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position) {
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        protected TextView botname;
        protected TextView botcurrent;
        protected Button btn1;

        public Holder(@NonNull View itemView) {
            super(itemView);
            this.botname = (TextView) itemView.findViewById(R.id.msg_name);
            this.botcurrent = (TextView) itemView.findViewById(R.id.msg_current);
            this.btn1 = (Button) itemView.findViewById(R.id.button1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String fuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (arrayList.get(position).getName().equals(fuser)) {
            return msgtype_right;
        } else {
            return msgtype_left;
        }
    }

    public void notifyAdapter() {
        notifyDataSetChanged();
    }


}