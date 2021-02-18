package gujc.directtalk9.fragment;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import gujc.directtalk9.R;
import gujc.directtalk9.common.FirestoreAdapter;
import gujc.directtalk9.model.Board;

public class BotFragment extends Fragment {
    FirestoreAdapter firestoreAdapter;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;

    public BotFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bot, container, false);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        //recyclerView.setLayoutManager( new LinearLayoutManager((inflater.getContext()),LinearLayoutManager.HORIZONTAL, false));
        LinearLayoutManager manager = new LinearLayoutManager((inflater.getContext()));
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        //recyclerView.setLayoutManager(manager); // timestamp 순으로 출력
        //LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        //linearSnapHelper.attachToRecyclerView(recyclerView);
        //recyclerView.setAdapter(firestoreAdapter);

        Button button1 = (Button) view.findViewById(R.id.button1);
        final TextView result = (TextView) view.findViewById(R.id.result);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result.setText("111");
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

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @NonNull
        @Override
        public BotFragment.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final RequestOptions requestOptions = new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(90));

            View view = LayoutInflater.from(parent.getContext()).inflate(R.id.item_botmsg_left.parent,false);
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView botname;
        protected TextView botitem;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.botname = (TextView) itemView.findViewById(R.id.msg_name);
            this.botitem = (TextView) itemView.findViewById(R.id.msg_item);
        }
    }
}