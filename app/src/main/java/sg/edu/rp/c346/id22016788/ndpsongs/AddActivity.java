package sg.edu.rp.c346.id22016788.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    TextView tvContent2, tvContent3, tvContent4, tvContent5;
    EditText etContent2, etContent3, etContent4;
    Button btnAdd, btnShowList;
    NDPSongs song;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnShowList = findViewById(R.id.btnShowList);
        btnAdd = findViewById(R.id.btnAdd);
        tvContent2 = findViewById(R.id.tvContent2);
        tvContent3 = findViewById(R.id.tvContent3);
        tvContent4 = findViewById(R.id.tvContent4);
        tvContent5 = findViewById(R.id.tvContent5);
        etContent2 = findViewById(R.id.etContent2);
        etContent3 = findViewById(R.id.etContent3);
        etContent4 = findViewById(R.id.etContent4);
        radioButton1 = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        radioButton5 = findViewById(R.id.radioButton5);

        Intent i = getIntent();
        song = (NDPSongs) i.getSerializableExtra("data");



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etContent2.getText().toString();
                String singer = etContent3.getText().toString();
                String year = etContent4.getText().toString();
                if (radioButton1.isChecked()){
                    String rating = radioButton1.getText().toString();
                    DBHelper dbh = new DBHelper(AddActivity.this);
                    long inserted_id = dbh.insertNDPSongs(title, singer, year, rating);
                } else if (radioButton2.isChecked()){
                    String rating = radioButton2.getText().toString();
                    DBHelper dbh = new DBHelper(AddActivity.this);
                    long inserted_id = dbh.insertNDPSongs(title, singer, year, rating);
                } else if (radioButton3.isChecked()){
                    String rating = radioButton3.getText().toString();
                    DBHelper dbh = new DBHelper(AddActivity.this);
                    long inserted_id = dbh.insertNDPSongs(title, singer, year, rating);
                } else if (radioButton4.isChecked()){
                    String rating = radioButton4.getText().toString();
                    DBHelper dbh = new DBHelper(AddActivity.this);
                    long inserted_id = dbh.insertNDPSongs(title, singer, year, rating);
                }
                else {
                    String rating = radioButton5.getText().toString();
                    DBHelper dbh = new DBHelper(AddActivity.this);
                    long inserted_id = dbh.insertNDPSongs(title, singer, year, rating);
                }
                }
            }
        );

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
