package gujc.dotter.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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


    public ChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        return view;
    }

    class Adapter extends RecyclerView.Adapter<Holder> {
        ArrayList<Board> board;
        Adapter(ArrayList<Board> board ){
            this.board = board;
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
            ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    chart = documentSnapshot.toObject(Board.class);
                    dname = chart.getDoctor();
                    timestamp = chart.getTimestamp();
                }
            });

        }

        @Override
        public int getItemCount() {
            return board.size();
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