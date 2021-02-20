package gujc.directtalk9.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    FirestoreAdapter firestoreAdapter;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Context context;
    private ArrayList<Chatbot> arrayList = new ArrayList<>();
    private BotAdapter botAdapter;

//    public BotFragment(ArrayList<Chatbot> arrayList) {
//        this.arrayList = arrayList;
//    }

    public BotFragment(){}


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bot, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
//        linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setLayoutManager( new LinearLayoutManager((inflater.getContext()),LinearLayoutManager.HORIZONTAL, false));
        //final LinearLayoutManager manager = new LinearLayoutManager((inflater.getContext()));
//        manager.setReverseLayout(true);
//        manager.setStackFromEnd(true);
        //recyclerView.setLayoutManager(manager); // timestamp 순으로 출력
        //LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        //linearSnapHelper.attachToRecyclerView(recyclerView);
        //recyclerView.setAdapter(firestoreAdapter);

        Button button1 = (Button) view.findViewById(R.id.button1);
        final TextView result = (TextView) view.findViewById(R.id.result);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chatbot chatbot = new Chatbot("Chatbot","hihi");
                arrayList.add(chatbot);


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

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public static final int msgleft = 0;
        public static final int msgright = 1;
        private Context context;
        FirebaseUser fuser;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(viewType, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            final CustomViewHolder customViewHolder = (CustomViewHolder) holder;
            final Message message = new Message();
            customViewHolder.botname.setText("Chatbot");
            customViewHolder.botcontent.setText("hihi");

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        //messageViewholder
        public class CustomViewHolder extends RecyclerView.ViewHolder {
            public TextView botname;
            public TextView botcontent;

            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                this.botname = (TextView) itemView.findViewById(R.id.msg_name);
                this.botcontent = (TextView) itemView.findViewById(R.id.msg_item);
            }
        }

        public void remove(int position)   {
            try {

            }catch (Exception e){

            }
        }
    }
}