package gujc.directtalk9.fragment;

import android.app.ProgressDialog;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import gujc.directtalk9.R;
import gujc.directtalk9.bot.BotAdapter;
import gujc.directtalk9.common.FirestoreAdapter;
import gujc.directtalk9.model.Chatbot;
import gujc.directtalk9.model.Message;
import gujc.directtalk9.model.UserModel;
import okhttp3.internal.cache.DiskLruCache;


public class BotFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Chatbot> arrayList = new ArrayList<>();
    private BotAdapter botAdapter;
    private UserModel user;
    private String fuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String mcurrent ="nmtest";
    private String ucurrent = "";
    private String bcurrent = "";

    public BotFragment(){}


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bot, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayList = new ArrayList<>();
        botAdapter = new BotAdapter(arrayList);
        recyclerView.setAdapter(botAdapter);

        final Button button1 = (Button) view.findViewById(R.id.button1);
        final Button button2 = (Button) view.findViewById(R.id.button2);
        final Button button3 = (Button) view.findViewById(R.id.button3);
        final Button button4 = (Button) view.findViewById(R.id.button4);

        DocumentReference ref = FirebaseFirestore.getInstance().collection("users").document(fuser);
        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(UserModel.class);
                Chatbot chatbot = new Chatbot("bot", "안녕하세요 "+ user.getUsernm() + " 님이 맞나요?");
                arrayList.add(chatbot);
                botAdapter.notifyDataSetChanged();
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chatbot chatbot = new Chatbot(fuser, mcurrent);
                switch (chatbot.getCurrent()) {
                    case "nmtest": {
                        mcurrent = "nmyes";
                        ucurrent = "네";
                        bcurrent = "어디가 아프신가요?";
                        button1.setText("상체");
                        button2.setText("하체");
                        chatbot = new Chatbot(fuser, ucurrent);
                        Chatbot chatbot1 = new Chatbot("bot", bcurrent);
                        arrayList.add(chatbot);
                        arrayList.add(chatbot1);
                        botAdapter.notifyDataSetChanged();
                        break;
                    }
                    case "nmyes": {
                        mcurrent = "dnl";
                        ucurrent = "상체";
                        bcurrent = "더 자세히 알려주세요1";
                        button1.setText("머리");
                        button2.setText("가슴");
                        button3.setText("배");
                        button3.setVisibility(View.VISIBLE);
                        chatbot = new Chatbot(fuser, ucurrent);
                        Chatbot chatbot1 = new Chatbot("bot", bcurrent);
                        arrayList.add(chatbot);
                        arrayList.add(chatbot1);
                        botAdapter.notifyDataSetChanged();
                        break;
                    }
                    case "dnl":{
                        mcurrent = "head";
                        ucurrent = "머리";
                        bcurrent = "더 자세히 알려주세요2";
                        button1.setText("얼굴");
                        button2.setText("얼굴 외");
                        chatbot = new Chatbot(fuser, ucurrent);
                        Chatbot chatbot1 = new Chatbot("bot", bcurrent);
                        arrayList.add(chatbot);
                        arrayList.add(chatbot1);
                        botAdapter.notifyDataSetChanged();
                        break;
                    }
                    case "head": {
                        mcurrent = "face";
                        ucurrent = "얼굴";
                        bcurrent = "더 자세히 알려주세요3";
                        button1.setText("눈");
                        button2.setText("코");
                        button3.setText("입");
                        chatbot = new Chatbot(fuser, ucurrent);
                        Chatbot chatbot1 = new Chatbot("bot", bcurrent);
                        arrayList.add(chatbot);
                        arrayList.add(chatbot1);
                        botAdapter.notifyDataSetChanged();
                        break;
                    }
                    case "face": {
                        mcurrent = "card";
                        ucurrent = "눈";
                        bcurrent = "더 자세히 알려주세요4";
                        button1.setText("결제");
                        chatbot = new Chatbot(fuser, ucurrent);
                        Chatbot chatbot1 = new Chatbot("bot", bcurrent);
                        arrayList.add(chatbot);
                        arrayList.add(chatbot1);
                        botAdapter.notifyDataSetChanged();
                        break;
                    }
                    case "card": {
                        mcurrent = "cyes";
                        ucurrent = "이카드로 진행할까요?";
                        bcurrent = "이카드로 진행할까요?";
                        button1.setText("yes");
                        button2.setText("no");
                        button3.setVisibility(View.GONE);
                        chatbot = new Chatbot(fuser, ucurrent);
                        Chatbot chatbot1 = new Chatbot("bot", bcurrent);
                        arrayList.add(chatbot);
                        arrayList.add(chatbot1);
                        botAdapter.notifyDataSetChanged();
                        break;
                    }
                    case "cyes":
                    {
                        mcurrent = "match";
                        button1.setText("의사 매칭하기");
                        button2.setVisibility(View.GONE);
                        button3.setVisibility(View.GONE);
                        break;
                    }
                    case "match":{
                        ProgressDialog pd = ProgressDialog.show(getContext(), "", "매칭 중");
                        pd.show();
                    }
                    default: {
                        break;
                    }
                }

            }
        });

        //버튼2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Chatbot chatbot = new Chatbot(fuser, mcurrent);
                switch (chatbot.getCurrent()) {
                    case "nmtest": {
                        mcurrent = "nmno";
                        ucurrent = "아니요";
                        bcurrent = "끝";
                        break;
                    }
                    default: {
                        break;
                    }
                }
                chatbot = new Chatbot(fuser, ucurrent);
                Chatbot chatbot1 = new Chatbot("bot", bcurrent);
                arrayList.add(chatbot);
                arrayList.add(chatbot1);
                botAdapter.notifyDataSetChanged();
            }
        });



        return view;



    }

}