package com.miguelcr.a01_simplelist;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // 1. Define the ListView component & the String array
    ListView popisView;
    List<String> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. Assign to the popisView a value
        // The connection to the ListView component
        popisView = findViewById(R.id.listViewStudents);

        // 3. Add elements to the students list
        students = new ArrayList<>();
        students.add("Mario");
        students.add("Mateo");
        students.add("Rikardo");
        students.add("Filip I");
        students.add("Sven");
        students.add("Nikola");
        students.add("Tomislav I");
        students.add("Jan");
        students.add("Antonio");
        students.add("Daniel");
        students.add("Filip II");
        students.add("Andrija");
        students.add("Leon");
        students.add("Matej");
        students.add("Tomislav II");
        students.add("Luka I");
        students.add("Luka II");
        students.add("Antonela");
        students.add("Ivana");
        students.add("Leonardo");
        students.add("Ivan");

        // 4. Adapter
        ArrayAdapter adapterStudents = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                students
        );

        // 5. Connect ListView & Adapter
        popisView.setAdapter(adapterStudents);

        // 6. Item click event
        popisView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = students.get(position);
        Toast.makeText(
                this,
                "Student: " + name,
                Toast.LENGTH_SHORT).show();

        // Rotate the TexView where user clicked
        view.animate()
                .rotationXBy(360)
                .setDuration(1000)
                .start();
    }
}
