package gujc.directtalk9.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.opengl.Visibility;
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
import com.google.firebase.auth.FirebaseAuth;
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
    private BotAdapter botAdapter;
    private String fuser = FirebaseAuth.getInstance().getCurrentUser().getUid();


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
        //final FragmentTransaction ft = getFragmentManager().beginTransaction();

        arrayList = new ArrayList<>();

        botAdapter = new BotAdapter(arrayList);
        recyclerView.setAdapter(botAdapter);


        final Button button1 = (Button) view.findViewById(R.id.button1);
//        final Button button2 = (Button) view.findViewById(R.id.button2);
//        final Button button3 = (Button) view.findViewById(R.id.button3);
        final TextView result = (TextView) view.findViewById(R.id.result);
//        button3.setVisibility(View.INVISIBLE);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                result.setText("111");
//                Chatbot chatbot;
//                Chatbot chatbot1;
//                chatbot = new Chatbot(fuser,"111");
//                chatbot1 = new Chatbot("Chatbot","hihi1");
//                button1.setText("222");
//                button3.setVisibility(View.VISIBLE);
//
//                //ft.detach(BotFragment.this).attach(BotFragment.this).commit();
//            }
//
//        });
//
//
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.setText("111");
                Chatbot chatbot = new Chatbot("bot","hihi");
                arrayList.add(chatbot);
                botAdapter.notifyDataSetChanged();
            }
        });

        return view;



    }

}