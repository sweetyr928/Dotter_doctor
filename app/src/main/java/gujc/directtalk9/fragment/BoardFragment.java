package gujc.directtalk9.fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import gujc.directtalk9.R;
import gujc.directtalk9.chat.ChatActivity;
import gujc.directtalk9.common.FirestoreAdapter;
import gujc.directtalk9.model.Board;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class BoardFragment extends Fragment{

    private FirestoreAdapter firestoreAdapter;
    private static final String TAG = "BoardFragment";
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

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

        firestoreAdapter = new BoardFragment.RecyclerViewAdapter(FirebaseFirestore.getInstance().collection("Board").orderBy("timestamp")); // orderby 추가해야함

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        //recyclerView.setLayoutManager( new LinearLayoutManager((inflater.getContext()),LinearLayoutManager.HORIZONTAL, false));
        LinearLayoutManager manager = new LinearLayoutManager((inflater.getContext()));
        manager.setReverseLayout(true);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager); // timestamp 순으로 출력
        //LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        //linearSnapHelper.attachToRecyclerView(recyclerView);
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
            final DocumentSnapshot documentSnapshot = getSnapshot(position);
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

                    AlertDialog.Builder oDialog = new AlertDialog.Builder(getContext(),
                            android.R.style.Theme_DeviceDefault_Light_Dialog);

                    oDialog.setMessage("채팅을 시작하시겠습니까?")
                            .setTitle("             알림")
                            .setPositiveButton("아니오", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    Log.i("Dialog", "취소");
                                    //Toast.makeText(getContext(), "취소", Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNeutralButton("예", new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    documentSnapshot.getReference().update("match", true); // 매칭되면 true로 변경
                                    Intent intent = new Intent(getView().getContext(), ChatActivity.class);
                                    intent.putExtra("toUid", board.getId());
                                    intent.putExtra("title",board.getTitle());
                                    startActivity(intent);
                                }
                            })
                            .setCancelable(false) // 백버튼으로 팝업창이 닫히지 않도록 한다.


                            .show();

                    /*Intent intent = new Intent(getView().getContext(), ChatActivity.class);
                    intent.putExtra("toUid", board.getId());
                    intent.putExtra("title",board.getTitle());
                    startActivity(intent);*/
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

    public class SnapHelperOneByOne extends LinearSnapHelper {

        @Override
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {

            if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
                return RecyclerView.NO_POSITION;
            }

            final View currentView = findSnapView(layoutManager);

            if (currentView == null) {
                return RecyclerView.NO_POSITION;
            }

            LinearLayoutManager myLayoutManager = (LinearLayoutManager) layoutManager;

            int position1 = myLayoutManager.findFirstVisibleItemPosition();
            int position2 = myLayoutManager.findLastVisibleItemPosition();

            int currentPosition = layoutManager.getPosition(currentView);

            if (velocityX > 400) {
                currentPosition = position2;
            } else if (velocityX < 400) {
                currentPosition = position1;
            }

            if (currentPosition == RecyclerView.NO_POSITION) {
                return RecyclerView.NO_POSITION;
            }

            return currentPosition;
        }
    }

}

