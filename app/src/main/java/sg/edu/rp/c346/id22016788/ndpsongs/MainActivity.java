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
    ArrayList<NDPSongs> al = new ArrayList<NDPSongs>();
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn5stars = findViewById(R.id.btn5stars);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        lv = findViewById(R.id.lv);

        al = new ArrayList<NDPSongs>();

        //aa = new ArrayAdapter<NDPSongs>(this,
                //R.layout.row, al);

        al.add(new NDPSongs(0, "Title1", "Singer1", "2022", "3"));
        al.add(new NDPSongs(1, "Title2", "Singer2", "2022", "5"));

        CustomAdapter adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);

        DBHelper dbh = new DBHelper(MainActivity.this);
        al.clear();
        al.addAll(dbh.getAllNDPSongs());
        adapter.notifyDataSetChanged();
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
                al.addAll(dbh.getAllNDPSongs("5"));
                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);
            }
        }
        );
    }
}
