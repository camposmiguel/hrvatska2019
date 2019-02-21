package com.miguelcr.a02_customlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // 1. ListView
    ListView popisView;
    List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. get the ListView reference
        popisView = findViewById(R.id.listViewStudents);

        // 3.1. New > Java Class... > Student

        // 3.2. add students elements
        students = new ArrayList<>();
        students.add(new Student("Mario", 5.0f, "Android Course", "https://s3.amazonaws.com/uifaces/faces/twitter/mattchevy/128.jpg"));
        students.add(new Student("Mateo", 1.0f, "Android Course", "https://pbs.twimg.com/profile_images/1036348009993383936/83S0m8vv_400x400.jpg"));
        students.add(new Student("Rikardo", 3.0f, "Android Course", "https://pbs.twimg.com/profile_images/527584017189982208/l3wwN-l-_400x400.jpeg"));
        students.add(new Student("rebecLeonardo", 3.5f, "Android Course", "https://s3.amazonaws.com/uifaces/faces/twitter/mattsince87/128.jpg"));
        students.add(new Student("Ivana", 5.0f, "Android Course", "https://s3.amazonaws.com/uifaces/faces/twitter/nzcode/128.jpg"));

        // 4. Adapter
        // 4.1. New > Java Class... > StudentAdapter (superclass ArrayAdapter)
        StudentAdapter adapter = new StudentAdapter(
          this,
                R.layout.student_item,
                students
        );

        // 5. Connect popisView and adapter
        popisView.setAdapter(adapter);
    }
}
