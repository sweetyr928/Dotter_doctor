package gujc.dotter.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import gujc.dotter.ChartinfoActivity;
import gujc.dotter.R;
import gujc.dotter.common.FirestoreAdapter;
import gujc.dotter.model.Board;
import gujc.dotter.model.ChatModel;
import gujc.dotter.model.ChatRoomModel;
import gujc.dotter.model.UserModel;

public class ChartFragment extends Fragment {
    private Board chart;
    private String myuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String dname;
    private String timestamp1;
    public LinearLayoutManager manager;
    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private FirestoreAdapter firestoreAdapter;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private String boardname="";
    private String note="";
    private FirebaseFirestore firestore;


    public ChartFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        if (firestoreAdapter != null) {
            firestoreAdapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firestoreAdapter != null) {
            firestoreAdapter.stopListening();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        firestoreAdapter = new Adapter(FirebaseFirestore.getInstance()
                .collection("Board").whereEqualTo("match", true).whereEqualTo("doctorid", myuid).orderBy("timestamp"));
        LinearLayoutManager manager = new LinearLayoutManager(inflater.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(firestoreAdapter);
        return view;
    }

    class Adapter extends FirestoreAdapter<Holder> {
        private StorageReference storageReference;
        ArrayList<Board> board;

        Adapter(Query query) {
            super(query);
            storageReference = FirebaseStorage.getInstance().getReference();
        }

        @Override
        public ChartFragment.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ChartFragment.Holder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chart, parent, false));
        }

        @Override
        public void onBindViewHolder(ChartFragment.Holder holder, int position) {
            final DocumentSnapshot documentSnapshot = getSnapshot(position);
            final Board board = documentSnapshot.toObject(Board.class);

            boardname = board.getName();
            holder.name.setText(board.getName());
            timestamp1 = simpleDateFormat.format(board.getTimestamp());
            holder.timestamp.setText(timestamp1);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getView().getContext(), ChartinfoActivity.class);
                    intent.putExtra("name", board.getName());
                    intent.putExtra("hospital", board.getHospital());
                    intent.putExtra("timestamp", timestamp1);
                    intent.putExtra("board", board.getTitle());
                    intent.putExtra("phone", board.getName());
                    intent.putExtra("note",board.getNote());

                    startActivity(intent);
                }
            });

        }

    }

    private class Holder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView timestamp;

        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            timestamp = itemView.findViewById(R.id.timestamp);
        }
    }
}