package gujc.dotter.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

import gujc.dotter.R;
import gujc.dotter.bot.BotAdapter;
import gujc.dotter.common.FirestoreAdapter;
import gujc.dotter.model.Board;
import gujc.dotter.model.UserModel;

public class ChartFragment extends Fragment {
    private Board chart;
    private String myuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String dname;
    private Date timestamp;
    private LinearLayoutManager manager;
    private RecyclerView recyclerView;
    private FirestoreAdapter firestoreAdapter;


    public ChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        firestoreAdapter = new ChartFragment.Adapter(FirebaseFirestore.getInstance().collection("Board").orderBy("timestamp"));

        LinearLayoutManager manager = new LinearLayoutManager(inflater.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setReverseLayout(true);
        manager.setStackFromEnd(false);
        recyclerView.setLayoutManager(manager); // timestamp 순으로 출력
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

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_chart,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            DocumentReference ref = FirebaseFirestore.getInstance().collection("Board").document();
            FirebaseFirestore.getInstance().collection("Board").whereEqualTo("id",myuid).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()){
                                chart = documentSnapshot.toObject(Board.class);
                                dname = chart.getDoctor();
                                timestamp = chart.getTimestamp();
                                System.out.println("print"+dname);
                            }

                        }
                    });
            holder.name.setText(dname);
            holder.timestamp.setText((CharSequence) timestamp);

        }

    }

    private class Holder extends RecyclerView.ViewHolder {
        TextView name;
        TextView timestamp;

        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            timestamp = itemView.findViewById(R.id.timestamp);
        }
    }
}