package gujc.directtalk9.fragment;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import gujc.directtalk9.PopupActivity;
import gujc.directtalk9.R;
import gujc.directtalk9.chat.ChatActivity;
import gujc.directtalk9.common.FirestoreAdapter;
import gujc.directtalk9.model.Board;
import gujc.directtalk9.model.UserModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class BoardFragment extends Fragment{

    private FirestoreAdapter firestoreAdapter;

    public BoardFragment() {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        firestoreAdapter = new BoardFragment.RecyclerViewAdapter(FirebaseFirestore.getInstance().collection("Board")); // orderby 추가해야함

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager( new LinearLayoutManager((inflater.getContext())));
        recyclerView.setAdapter(firestoreAdapter);

        return view;
    }

    class RecyclerViewAdapter extends FirestoreAdapter<CustomViewHolder> {
        final private RequestOptions requestOptions = new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(90));
        private StorageReference storageReference;
        private String myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        RecyclerViewAdapter(Query query) {
            super(query);
            storageReference  = FirebaseStorage.getInstance().getReference();
        }

        @Override
        public BoardFragment.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BoardFragment.CustomViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_board, parent, false));
        }

        @Override
        public void onBindViewHolder(BoardFragment.CustomViewHolder viewHolder, int position) {
            DocumentSnapshot documentSnapshot = getSnapshot(position);
            final Board board = documentSnapshot.toObject(Board.class);

            if (board.isMatch()) {
                viewHolder.itemView.setVisibility(View.GONE);
                viewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
            else{
                viewHolder.user_name.setText(board.getName());
                viewHolder.user_title.setText(board.getTitle());
            }

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getView().getContext(), PopupActivity.class);
                    intent.putExtra("toUid", board.getId());
                    intent.putExtra("title",board.getTitle());
                    startActivity(intent);
                }
            }); //누르면 채팅
        }
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {
        public ImageView user_photo;
        public TextView user_name;
        public TextView user_title;

        CustomViewHolder(View view) {
            super(view);
            user_photo = view.findViewById(R.id.user_photo);
            user_name = view.findViewById(R.id.user_name);
            user_title = view.findViewById(R.id.user_title);
        }
    }

}

