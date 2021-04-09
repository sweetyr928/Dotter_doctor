package gujc.dotter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import gujc.dotter.model.Board;

public class ChartinfoActivity extends AppCompatActivity {
    private EditText doctor;
    private EditText hospital;
    private EditText timestamp;
    private EditText board;
    private EditText chartnote;
    private Button receipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chartinfo);

        doctor = findViewById(R.id.doctor);
        hospital = findViewById(R.id.hospital);
        timestamp = findViewById(R.id.timestamp);
        chartnote = findViewById(R.id.chartnote);
        board = findViewById(R.id.board);
        receipt = findViewById(R.id.receipt);

        receipt.setOnClickListener(receiptclick);

        doctor.setText(getIntent().getStringExtra("doctor"));
        hospital.setText(getIntent().getStringExtra("hospital"));
        timestamp.setText(getIntent().getStringExtra("timestamp"));
        board.setText(getIntent().getStringExtra("board"));


    }

    Button.OnClickListener receiptclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}