package sg.edu.rp.c346.id22016788.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class ModifyActivity extends AppCompatActivity {

    TextView tvContent1, tvContent2, tvContent3, tvContent4, tvContent5;
    EditText etContent1, etContent2, etContent3, etContent4;
    Button btnUpdate, btnDelete, btnCancel;
    NDPSongs song; // , singer, year, rating;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        btnDelete = findViewById(R.id.btnShowList);
        btnUpdate = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        tvContent1 = findViewById(R.id.tvContent1);
        tvContent2 = findViewById(R.id.tvContent2);
        tvContent3 = findViewById(R.id.tvContent3);
        tvContent4 = findViewById(R.id.tvContent4);
        tvContent5 = findViewById(R.id.tvContent5);
        etContent1 = findViewById(R.id.etContent1);
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
//        singer = (NDPSongs) i.getSerializableExtra("singer");
//        year = (NDPSongs) i.getSerializableExtra("year");
//        rating = (NDPSongs) i.getSerializableExtra("rating");

        tvContent1.setText("ID: " + song.getId());
        etContent1.setText(song.getNDPSongsTitle());
        etContent2.setText(song.getNDPSongsSinger());
        etContent3.setText(song.getNDPSongsYear());
        etContent4.setText(song.getNDPSongsRating());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                song.setNDPSongsContent(etContent1.getText().toString(), etContent2.getText().toString(), etContent3.getText().toString(), etContent4.getText().toString());
                dbh.updateNDPSongs(song);
                dbh.close();
                Intent intent = new Intent(ModifyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteNDPSongs(song.getId());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}