package gujc.directtalk9.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

import gujc.directtalk9.R;

import static android.content.ContentValues.TAG;

public class WriteFragment extends Fragment {

    private FirebaseFirestore firestore=null;
    private FirebaseUser myUid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_write, container, false);

        firestore = FirebaseFirestore.getInstance();
        myUid = FirebaseAuth.getInstance().getCurrentUser();

        Button button = (Button) rootView.findViewById(R.id.button);
        final EditText title_editText = (EditText) rootView.findViewById(R.id.title_edittext);
        final EditText name_editText = (EditText) rootView.findViewById(R.id.name_edittext);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id", myUid.getUid());
                //hashMap.put("name2",fuser.getDisplayName());
                hashMap.put("name",name_editText.getText().toString());
                hashMap.put("title", title_editText.getText().toString());
                hashMap.put("match",false);
                hashMap.put("timestamp", FieldValue.serverTimestamp());

                firestore.collection("Board").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    //데이터가 성공적으로 추가되었을 때
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            //에러가 발생했을 때
                                Log.w(TAG, "Error ", e);
                            }
                        });

                title_editText.setText("");
                name_editText.setText("");
                //Toast.makeText(context, "작성 완료!", Toast.LENGTH_SHORT).show();
            }

        });

        return rootView;

    }
}
