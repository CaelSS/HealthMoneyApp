package com.example.healthmoneyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.view.View;
import android.view.View.OnClickListener;




public class MainActivity extends AppCompatActivity {
    TextView txtCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/


        txtCadastrar = findViewById(R.id.txtCadastrar);
        txtCadastrar.setOnClickListener((View view)->{
            Intent intent = new Intent(MainActivity.this, TelaCadastro.class);
            startActivity(intent);
        });


    }
}