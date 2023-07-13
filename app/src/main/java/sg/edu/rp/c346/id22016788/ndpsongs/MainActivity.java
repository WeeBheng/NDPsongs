package sg.edu.rp.c346.id22016788.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnEdit, btn5stars;
    ArrayList<NDPSongs> al;
    ListView lv;
    ArrayAdapter<NDPSongs> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn5stars = findViewById(R.id.btn5stars);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        lv = findViewById(R.id.lv);

        al = new ArrayList<NDPSongs>();
        aa = new ArrayAdapter<NDPSongs>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        DBHelper dbh = new DBHelper(MainActivity.this);
        al.clear();
        al.addAll(dbh.getAllNDPSongs());
        aa.notifyDataSetChanged();
        Log.d("hope this works", al.toString());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                NDPSongs data = al.get(position);
                Intent i = new Intent(MainActivity.this,
                        ModifyActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                }
            }
        );

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
                 startActivity(intent);
                 }
            }
        );

        btn5stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                al.clear();
                al.addAll(dbh.getAllNDPSongs("*****"));
                aa.notifyDataSetChanged();
                lv.setAdapter(aa);
            }
        }
        );
    }
}
