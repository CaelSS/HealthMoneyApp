package com.example.healthmoneyapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.healthmoneyapp.database.DataBase;
import com.example.healthmoneyapp.database.Usuario;
import com.example.healthmoneyapp.database.UsuarioDao;

public class TelaCadastro extends AppCompatActivity {
    Button btnCancelarCadastro;
    Button btnCadastrarCadastro;

    EditText txtNomeCadastro, txtSenhaCadastro, txtCidadeCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        btnCadastrarCadastro = findViewById(R.id.btnCadastrarCadastro);
        btnCancelarCadastro = findViewById(R.id.btnCancelarCadastro);

        txtNomeCadastro = findViewById(R.id.txtNomeCadastro);
        txtSenhaCadastro = findViewById(R.id.txtSenhaCadastro);
        txtCidadeCadastro = findViewById(R.id.txtCidadeCadastro);

        btnCadastrarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });

        btnCadastrarCadastro.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnCadastrarCadastro.setTextColor(Color.BLACK);
                    btnCadastrarCadastro.setBackgroundColor(Color.GRAY);
                } else {
                    btnCadastrarCadastro.setTextColor(Color.WHITE);
                    btnCadastrarCadastro.setBackgroundColor(Color.GRAY);
                }
                return false;
            }
        });

        btnCancelarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void cadastrar() {
        if (!validarCampos()) return;

        String nome = txtNomeCadastro.getText().toString();
        String cidade = txtCidadeCadastro.getText().toString();
        String senha = txtSenhaCadastro.getText().toString();

        Usuario usuario = new Usuario();
        usuario.login = nome;
        usuario.cidade = cidade;
        usuario.senha = senha;

        DataBase db = Room.databaseBuilder(getApplicationContext(), DataBase.class, "Base de Dados").allowMainThreadQueries().build();
        UsuarioDao dao = db.getUserDao();

        Usuario repetido = dao.checkDuplicated(usuario.login);
        if (repetido != null) {
            Toast.makeText(this, "Ta usando sharingan, copiador?", Toast.LENGTH_SHORT).show();
            return;
        }

        dao.insereUsuario(usuario);
        new AlertDialog.Builder(this)
                .setTitle("Cadastro Concluído")
                .setMessage("Seu cadastro foi realizado com sucesso!")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Volta para a MainActivity
                    Intent intent = new Intent(TelaCadastro.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                })
                .show();
    }

    private boolean validarCampos() {
        if (txtNomeCadastro.getText().toString().isEmpty()) {
            txtNomeCadastro.setError("You shal not pass without a name");
            return false;
        }
        if (txtSenhaCadastro.getText().toString().isEmpty()) {
            txtSenhaCadastro.setError("E vai proteger tua conta como? bota uma senha, jovem.");
            return false;
        }
        if (txtCidadeCadastro.getText().toString().isEmpty()) {
            txtCidadeCadastro.setError("Mora onde, nômade?");
            return false;
        }
        return true;
    }
}
