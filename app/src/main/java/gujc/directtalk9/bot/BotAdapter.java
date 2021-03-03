package gujc.directtalk9.bot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;

import gujc.directtalk9.R;
import gujc.directtalk9.fragment.BotFragment;
import gujc.directtalk9.model.Chatbot;

public class BotAdapter extends RecyclerView.Adapter<BotAdapter.Holder> {
    public static final int msgtype_right = 0;
    public static final int msgtype_left = 1;
    public static final int msgtype_left2 = 2;
    public static final int msgtype_left3 = 3;
    private String fuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private Context context;
    private ArrayList<Chatbot> arrayList = new ArrayList<>();
    String mcurrent = "start";
    int count = 0;

    public BotAdapter(ArrayList<Chatbot> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BotAdapter.Holder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        if (viewType == msgtype_left && count == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_botbtn, parent, false);
            final Button button1 = (Button) view.findViewById(R.id.button1);
            final Button button2 = (Button) view.findViewById(R.id.button2);
            final Button button3 = (Button) view.findViewById(R.id.button3);
            final Button button4 = (Button) view.findViewById(R.id.button4);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Chatbot chatbot = new Chatbot(fuser, mcurrent);
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent + "1b");
                    switch (chatbot.getCurrent()) {
                        case "start": {
                            mcurrent = "101";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
                            notifyDataSetChanged();
                            break;
                        }
                        default: {
                            view.setVisibility(View.INVISIBLE);
                            break;
                        }
                    }
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Chatbot chatbot = new Chatbot(fuser, mcurrent);
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent + "2b");
                    switch (chatbot.getCurrent()) {
                        case "start": {
                            mcurrent = "102";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
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
        } else if (viewType == msgtype_left2 && count == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_botbtn2, parent, false);
            final Button button1 = (Button) view.findViewById(R.id.button1);
            final Button button2 = (Button) view.findViewById(R.id.button2);
            final Button button3 = (Button) view.findViewById(R.id.button3);
            final Button button4 = (Button) view.findViewById(R.id.button4);
            button4.setVisibility(View.VISIBLE);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Chatbot chatbot = new Chatbot(fuser, mcurrent);
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent + "1b");
                    switch (chatbot.getCurrent()) {
                        case "101": {
                            mcurrent = "201";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
                            notifyDataSetChanged();
                            break;
                        }
                        case "102": {
                            mcurrent = "211";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
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
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent + "2b");
                    switch (chatbot.getCurrent()) {
                        case "101": {
                            mcurrent = "202";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
                            notifyDataSetChanged();
                            break;
                        }
                        case "102": {
                            mcurrent = "212";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
                            notifyDataSetChanged();
                            break;
                        }
                        case "211": {
                            mcurrent = "312";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
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
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent + "3b");
                    switch (chatbot.getCurrent()) {
                        case "101": {
                            mcurrent = "203";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
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
        } else if (viewType == msgtype_left3 && count == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_botbtn3, parent, false);
            final Button button1 = (Button) view.findViewById(R.id.button1);
            final Button button2 = (Button) view.findViewById(R.id.button2);
            final Button button3 = (Button) view.findViewById(R.id.button3);
            final Button button4 = (Button) view.findViewById(R.id.button4);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Chatbot chatbot = new Chatbot(fuser, mcurrent);
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent + "b1");
                    switch (chatbot.getCurrent()) {
                        case "201": {
                            mcurrent = "301";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
                            notifyDataSetChanged();
                            break;
                        }
                        case "202": {
                            mcurrent = "304";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
                            notifyDataSetChanged();
                            break;
                        }
                        case "211": {
                            mcurrent = "311";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
                            notifyDataSetChanged();
                            break;
                        }
                        case "212": {
                            mcurrent = "321";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
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
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent + "b2");
                    switch (chatbot.getCurrent()) {
                        case "201": {
                            mcurrent = "302";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
                            notifyDataSetChanged();
                            break;
                        }
                        case "202": {
                            mcurrent = "305";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
                            notifyDataSetChanged();
                            break;
                        }
                        case "211": {
                            mcurrent = "312";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
                            notifyDataSetChanged();
                            break;
                        }
                        case "212": {
                            mcurrent = "322";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            button1.setVisibility(View.GONE);
                            button2.setVisibility(View.GONE);
                            button3.setVisibility(View.GONE);
                            button4.setVisibility(View.GONE);
                            count++;
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
                    Chatbot chatbot1 = new Chatbot("bot", mcurrent + "b3");
                    switch (chatbot.getCurrent()) {
                        case "211": {
                            mcurrent = "313";
                            arrayList.add(chatbot);
                            arrayList.add(chatbot1);
                            count++;
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
            if (count == 0)
                return msgtype_left;
            else if (count == 1)
                return msgtype_left2;
            else if (count == 2)
                return msgtype_left3;
            else
                return msgtype_left;
        }
    }
}