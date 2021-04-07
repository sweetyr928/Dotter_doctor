package gujc.dotter.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;

import gujc.dotter.R;
import gujc.dotter.bot.BotAdapter;
import gujc.dotter.common.FirestoreAdapter;
import gujc.dotter.model.Board;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChartFragment extends Fragment {

    private String myuid;


    public ChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        myuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
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
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {

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
        }
    }
}