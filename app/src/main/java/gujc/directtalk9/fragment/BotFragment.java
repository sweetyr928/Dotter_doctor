package gujc.directtalk9.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import gujc.directtalk9.R;
import gujc.directtalk9.bot.BotAdapter;
import gujc.directtalk9.common.FirestoreAdapter;
import gujc.directtalk9.model.Chatbot;
import gujc.directtalk9.model.Message;


public class BotFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Chatbot> arrayList = new ArrayList<>();
    private BotFragment botFragment;


//    public BotFragment(ArrayList<Chatbot> arrayList) {
//        this.arrayList = arrayList;
//    }

    public BotFragment(){}


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bot, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        final FragmentTransaction ft = getFragmentManager().beginTransaction();

        Button button1 = (Button) view.findViewById(R.id.button1);
        final TextView result = (TextView) view.findViewById(R.id.result);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("111");
                Chatbot chatbot = new Chatbot(R.drawable.ic_launcher_background,"Chatbot","hihi");
                arrayList.add(chatbot);
                System.out.println(chatbot.getCurrent());
                ft.detach(BotFragment.this).attach(BotFragment.this).commit();
                //BotFragment.refresh();
            }
        });

        Button button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("222");
            }
        });

        return view;

    }

    private static void refresh() {
        refresh();
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public static final int msgleft = 0;
        public static final int msgright = 1;
        private Context context;
        FirebaseUser fuser;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_botmsg_left, parent, false);
            CustomViewHolder holder = new CustomViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
            final CustomViewHolder customViewHolder = (CustomViewHolder) holder;
            customViewHolder.cphoto.setImageResource(arrayList.get(position).getPhoto());
            customViewHolder.botname.setText(arrayList.get(position).getName());
            customViewHolder.botcontent.setText(arrayList.get(position).getCurrent());

            customViewHolder.itemView.setTag(position);
            customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String curName  = customViewHolder.botname.getText().toString();
                    Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
                }
            });

            customViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    remove(holder.getAdapterPosition());
                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return (null != arrayList ? arrayList.size()  : 0);
        }

        //messageViewholder
        public class CustomViewHolder extends RecyclerView.ViewHolder {
            public ImageView cphoto;
            public TextView botname;
            public TextView botcontent;

            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                this.cphoto = (ImageView) itemView.findViewById(R.id.bot_photo);
                this.botname = (TextView) itemView.findViewById(R.id.msg_name);
                this.botcontent = (TextView) itemView.findViewById(R.id.msg_current);
            }


        }

        public void remove(int position){
            try{
                arrayList.remove(position);
                notifyItemRemoved(position);
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
    }
}