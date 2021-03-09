package gujc.directtalk9;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import gujc.directtalk9.chat.ChatActivity;
import gujc.directtalk9.model.Board;
import gujc.directtalk9.model.Chatbot;
import gujc.directtalk9.model.UserModel;

public class CustomDialog extends Dialog{

    private Context context;
    String doctoruid="";
    String documentid="";
    private Board board;


    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }

    // 호출할 다이얼로그 함수를 정의
    public void callFunction(String docid,String doctor,String hospital,String doctorid) {

        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.custom_dialog);
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final TextView doctortxt = (TextView)dlg.findViewById(R.id.doctor);
        final TextView hospitaltxt = (TextView)dlg.findViewById(R.id.hospital);
        final Button okButton = (Button) dlg.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dlg.findViewById(R.id.cancelButton);

        doctortxt.setText(doctor);
        hospitaltxt.setText(hospital);

        doctoruid = doctorid;
        documentid = docid;

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra("toUid", doctoruid);
                getContext().startActivity(intent);
                dlg.dismiss();
                DocumentReference ref = FirebaseFirestore.getInstance().collection("Board").document(documentid);
                ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        board = documentSnapshot.toObject(Board.class);
                        documentSnapshot.getReference().update("match",true);
                    }
                });


            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentReference ref = FirebaseFirestore.getInstance().collection("Board").document(documentid);
                ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        board = documentSnapshot.toObject(Board.class);
                        documentSnapshot.getReference().update("request",false);
                    }
                });
                dlg.dismiss();
            }
        });
    }
}
