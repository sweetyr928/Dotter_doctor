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
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;

import gujc.directtalk9.R;
import gujc.directtalk9.model.Chatbot;

public class BotAdapter extends RecyclerView.Adapter<BotAdapter.Holder> {
    public static final int msgtype_left = 0;
    public static final int msgtype_right = 1;

    private Context context;
    private ArrayList<Chatbot> arrayList = new ArrayList<>();
    private String fuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public BotAdapter(ArrayList<Chatbot> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BotAdapter.Holder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        if (viewType == msgtype_left) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_botbtn2, parent, false);
            final Button button1 = (Button) view.findViewById(R.id.button1);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_botbtn2,parent,false);
                    Chatbot chatbot = new Chatbot("bot","hihi2");
                    Chatbot chatbot1 = new Chatbot(fuser, (String) button1.getText());
                    arrayList.add(chatbot1);
                    final Button button2 = (Button) view.findViewById(R.id.button1);
                    arrayList.add(chatbot);
                    if(chatbot1.getCurrent().equals("111")){
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Chatbot chatbot2 = new Chatbot("bot","222");
                                Chatbot chatbot3 = new Chatbot(fuser, (String) button2.getText());
                                arrayList.add(chatbot2);
                                arrayList.add(chatbot3);
                            }
                        });
                    }else {
                        chatbot1 = new Chatbot(fuser, "end");
                    }

                    notifyDataSetChanged();
                    //notifyAdapter();
                }
            });
            return new Holder(view);
        }else {
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
                Toast.makeText(view.getContext(),curname,Toast.LENGTH_SHORT).show();
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

    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        protected TextView botname;
        protected TextView botcurrent;
        protected Button btn1;
        protected Button btn2;

        public Holder(@NonNull View itemView) {
            super(itemView);
            this.botname = (TextView) itemView.findViewById(R.id.msg_name);
            this.botcurrent = (TextView) itemView.findViewById(R.id.msg_current);
            this.btn1 = (Button) itemView.findViewById(R.id.button1);
            this.btn2 = (Button) itemView.findViewById(R.id.button2);
        }
    }

    @Override
    public int getItemViewType(int position) {
        String fuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (arrayList.get(position).getName().equals(fuser)){
            return msgtype_right;
        }else {
            return msgtype_left;
        }
    }

    public void notifyAdapter(){
        notifyDataSetChanged();
    }
}
