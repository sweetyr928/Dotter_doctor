package gujc.directtalk9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

import gujc.directtalk9.chat.ChatActivity;
import gujc.directtalk9.model.Board;

public class PopupActivity extends Activity {

    private FirebaseFirestore firestore=null;
    private StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_activity);
    }

    public void mOnClose(View v){

        //데이터 받아오기
        Intent intent = getIntent();
        final String userid = intent.getStringExtra("toUid"); //userid 받아오기
        final String title = intent.getStringExtra("title");

        matchBoard(userid,title);

        //데이터 전달하기
        Intent intent_toMessage = new Intent(this, ChatActivity.class);
        intent_toMessage.putExtra("toUid",userid); // 작성자 id 넘겨주기
        startActivity(intent_toMessage);

    }

    public void mOnClose2(View v)
    {
        Intent intent_re = new Intent(this,MainActivity.class);
        startActivity(intent_re);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    private void matchBoard(final String toUid,final String title) {

        firestore.collection("Board").document(toUid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (!task.isSuccessful()) {return;}
                DocumentSnapshot document = task.getResult();
                Map<String, Boolean> board = (Map<String, Boolean>) document.get("Board");
                board.put("match", true);
                document.getReference().update("Board", board);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        super.onBackPressed();
    }
}
