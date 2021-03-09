package gujc.directtalk9.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gujc.directtalk9.CustomDialog;
import gujc.directtalk9.MainActivity;
import gujc.directtalk9.R;
import gujc.directtalk9.bot.BotAdapter;
import gujc.directtalk9.model.Board;
import gujc.directtalk9.model.Chatbot;
import gujc.directtalk9.model.UserModel;

import static android.content.ContentValues.TAG;


public class BotFragment extends Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Chatbot> arrayList = new ArrayList<>();
    private BotAdapter botAdapter;
    private UserModel user;
    private Board board;
    private String fuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String mcurrent ="nmtest";
    private String ucurrent = "";
    private String bcurrent = "";
    private List<String> arrayboard = new ArrayList<String>();
    public String doctor="";
    public String hospital="";
    SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
    Date time = new Date();
    public String time1 = time+fuser;
    public boolean request;
    ProgressDialog pd1;
    public BotFragment(){}
    private FirebaseFirestore db;


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
                Chatbot chatbot = new Chatbot("bot", "안녕하세요"+ user.getUsernm() + "님이 맞나요?");
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
                        arrayboard.add("문진");
                        button1.setText("상체");
                        button2.setText("하체");
                        break;
                    }
                    case "nmyes": {
                        mcurrent = "dnl";
                        ucurrent = "상체";
                        bcurrent = "더 자세히 알려주세요1";
                        arrayboard.add(ucurrent);
                        button1.setText("머리");
                        button2.setText("가슴");
                        button3.setText("배");
                        button3.setVisibility(View.VISIBLE);
                        break;
                    }
                    case "dnl":{
                        mcurrent = "head";
                        ucurrent = "머리";
                        bcurrent = "더 자세히 알려주세요2";
                        arrayboard.add(ucurrent);
                        button1.setText("얼굴");
                        button2.setText("얼굴 외");
                        break;
                    }
                    case "head": {
                        mcurrent = "face";
                        ucurrent = "얼굴";
                        bcurrent = "더 자세히 알려주세요3";
                        arrayboard.add(ucurrent);
                        button1.setText("눈");
                        button2.setText("코");
                        button3.setText("입");
                        break;
                    }
                    case "face": {
                        mcurrent = "card";
                        ucurrent = "눈";
                        bcurrent = "더 자세히 알려주세요4";
                        arrayboard.add(ucurrent);
                        button1.setText("결제");
                        break;
                    }
                    case "card": {
                        mcurrent = "cyes";
                        ucurrent = "이카드로 진행할까요?";
                        bcurrent = "이카드로 진행할까요?";
                        button1.setText("yes");
                        button2.setText("no");
                        button3.setVisibility(View.GONE);
                        break;
                    }
                    case "cyes": {
                        mcurrent = "match";
                        button1.setText("의사 매칭하기");
                        button2.setVisibility(View.GONE);
                        button3.setVisibility(View.GONE);
                        button4.setVisibility(View.GONE);
                        String strboard = String.valueOf(arrayboard);
                        Map<String,Object> boardcur = new HashMap<>();
                        boardcur.put("title",strboard);
                        boardcur.put("id",fuser);
                        boardcur.put("timestamp", new Timestamp(new Date()));
                        boardcur.put("name",user.getUsernm()+" 님");
                        boardcur.put("match",false);
                        boardcur.put("request",false);
                        boardcur.put("doctor","none");
                        boardcur.put("hospital","none");
                        boardcur.put("doctorid","none");

                        FirebaseFirestore.getInstance().collection("Board").document(time1).set(boardcur)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });
                        break;
                    }
                    case "match":{

                        pd1 = ProgressDialog.show(getContext(), "", "매칭 중");

                        DocumentReference ref = FirebaseFirestore.getInstance().collection("Board").document(time1);
                        ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                board = documentSnapshot.toObject(Board.class);

                                assert board != null;
                                doctor = board.getDoctor();
                                hospital = board.getHospital();

                                System.out.println(doctor);
                                System.out.println(hospital);
                            }
                        });

                        toDialog();

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
                recyclerView.scrollToPosition(arrayList.size()-1);
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

    private void toDialog()
    {
        final DocumentReference docRef = db.getInstance().collection("Board").document(time1);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                board = snapshot.toObject(Board.class);

                request = board.isRequest();
                System.out.println(request);

                if(request) {
                    pd1.dismiss();
                    CustomDialog customDialog = new CustomDialog(getContext());
                    customDialog.callFunction(doctor,hospital);
                }
            }
        });
    }

}