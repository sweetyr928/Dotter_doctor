package gujc.dotter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import gujc.dotter.common.Util9;
import gujc.dotter.model.UserModel;

public class SignupActivity extends AppCompatActivity {
    private EditText user_id;
    private EditText user_pw;
    private EditText user_phone;
    private EditText user_name;
    private EditText user_pwtest;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        user_id = findViewById(R.id.user_id);
        user_pw = findViewById(R.id.user_pw);
        user_phone = findViewById(R.id.user_phone);
        user_pwtest = findViewById(R.id.user_pwtest);
        user_name = findViewById(R.id.user_name);
        Button signupBtn = findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(signupClick);

        sharedPreferences = getSharedPreferences("gujc", Activity.MODE_PRIVATE);
        String id = sharedPreferences.getString("user_id", "");
        if (!"".equals(id)) {
            user_id.setText(id);
        }
    }

    Button.OnClickListener signupClick = new View.OnClickListener() {
        public void onClick(View view) {
            if (!validateForm()) return;
            final String id = user_id.getText().toString();
            final String phone = user_phone.getText().toString();
            final String name = user_name.getText().toString();

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(id,user_pw.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        sharedPreferences.edit().putString("user_id", id).commit();
                        final String uid = FirebaseAuth.getInstance().getUid();

                        UserModel userModel = new UserModel();
                        userModel.setUid(uid);
                        userModel.setUserid(id);
                        userModel.setUsernm(name);
                        userModel.setUsernm(extractIDFromEmail(id));
                        userModel.setPhone(phone);
                        userModel.setUsermsg("...");

                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("users").document(uid)
                                .set(userModel)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Log.d(String.valueOf(R.string.app_name), "DocumentSnapshot added with ID: " + uid);
                                    }
                                });
                    } else {
                        Util9.showMessage(getApplicationContext(), task.getException().getMessage());
                    }
                }
            });
        }
    };

    String extractIDFromEmail(String email){
        String[] parts = email.split("@");
        return parts[0];
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = user_id.getText().toString();
        if (TextUtils.isEmpty(email)) {
            user_id.setError("Required.");
            valid = false;
        } else {
            user_id.setError(null);
        }

        String password = user_pw.getText().toString();
        if (TextUtils.isEmpty(password)) {
            user_pw.setError("Required.");
            valid = false;
        } else {
            user_pw.setError(null);
        }

        String password2 = user_pwtest.getText().toString();
        if (TextUtils.isEmpty(password2)){
            user_pwtest.setError("Required.");
            valid = false;
        } else if (!password.equals(password2)){
            user_pwtest.setError("password not correct");
        }
        else {
            user_pw.setError(null);
        }

        String phone = user_phone.getText().toString();
        if (TextUtils.isEmpty(phone)){
            user_phone.setError("Required");
            valid = false;
        }else{
            user_phone.setError(null);
        }

        String name = user_name.getText().toString();
        if (TextUtils.isEmpty(name)){
            user_name.setError("Required");
            valid = false;
        }else{
            user_name.setError(null);
        }

        return valid;
    }
}
