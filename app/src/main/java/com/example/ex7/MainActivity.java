package com.example.ex7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, age, getInfo;
    TextView textView;
    String id;
    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        getInfo = findViewById(R.id.getEditText);
        textView = findViewById(R.id.text);

        task = new Task();


    }

    public void save(View view) {
        SaveTask st = new SaveTask();
        st.execute();
    }

    class SaveTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //creating a task
            Task task = new Task();
            task.setName(name.getText().toString());
            task.setAge(age.getText().toString());

            //adding to database
            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().insert(task);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void get(View view) {

         id = getInfo.getText().toString();

         RTask rTask = new RTask();

         rTask.execute();

    }

    class RTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {


            //adding to database
            task = DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().taskDao().get(Integer.parseInt(id));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            textView.setText("Name: " + task.getName() + "\n"
                        + "Age: " + task.getAge());
        }
    }
}