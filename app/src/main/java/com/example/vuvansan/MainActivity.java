package com.example.vuvansan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edName,edMail,edContent;
    Spinner spinner;
    CheckBox checkBox;
    Button btSend;
    AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=AppDatabase.getAppDatabase(this);

        initView();

    }
    public void initView(){
        edName=findViewById(R.id.edName);
        edMail=findViewById(R.id.edGmail);
        edContent=findViewById(R.id.edContent);
        spinner=findViewById(R.id.spinner);
        checkBox=findViewById(R.id.ck);
        btSend=findViewById(R.id.btSend);
        String[] gripe= {"Gripe1","Gripe2","Gripe3"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,gripe);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edName.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter Username",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edMail.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter Email",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!checkBox.isChecked()){
                    Toast.makeText(MainActivity.this,"Please agree rules",Toast.LENGTH_LONG).show();
                    return;
                }
                User user=new User();
                user.setName(edName.getText().toString());
                user.setEmail(edMail.getText().toString());
                user.setContent(edContent.getText().toString());
                database.userDao().insertUser(user);
                Log.d("TAG", "onClick: " +database.userDao().count());
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Count user is : " +database.userDao().count());
                builder.setCancelable(true);
                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();

            }
        });






    }


}