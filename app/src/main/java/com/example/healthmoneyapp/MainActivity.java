package com.example.healthmoneyapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.healthmoneyapp.database.DataBase;
import com.example.healthmoneyapp.database.Usuario;


public class MainActivity extends AppCompatActivity {
    TextView txtCadastrar;
    Button botaoEntrar,botaoSair;
    EditText editLogin, editSenha;
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

        //CAPTURO OS COMPONENTES DA TELA MAIn

        txtCadastrar = findViewById(R.id.txtCadastrar);
        editLogin = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editSenha);
        botaoEntrar = findViewById(R.id.botãoEntrar);
        botaoSair = findViewById(R.id.botãoSair);

        //ADICIONO OS EVENTOS DE BOTAO DA TELA DE LOGIN

        //chama a tela de cadastrar quando o  texto é pressionado
        txtCadastrar.setOnClickListener((View view)->{
            Intent intent = new Intent(MainActivity.this, TelaCadastro.class);
            startActivity(intent);
        });
        botaoSair.setOnClickListener((View view)->{
            finishAndRemoveTask(); // finaiza a task, sem matar o processo
        });
        botaoEntrar.setOnClickListener((View view)->{ // FORMA MODERNA - LAMBDA FUNCTION
            logar();
        });

    }


    private void logar() {
        // Verifica se os campos foram preenchidos
        if (!validarCampos()) {
            return;
        }
        // Capturar os textos dos componentes
        String login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();

        // Verifica se o login é válido
        if (!validarLogin(login, senha)) {
            showAlertDialog(this, "Erro de Login", "Dados de login incorretos");
            return;
        }
        // Muda a tela caso os dados estejam corretos e vai pra tela de admin
        Intent intent = new Intent(this, TelaAdministrador.class);
        startActivity(intent);
    }

    private boolean validarCampos() {
        if (editLogin.getText().toString().trim().equals("")) {
            editLogin.setError("Não vai entrar sem login!");
            return false;
        }

        if (editSenha.getText().toString().trim().equals("")) {
            editSenha.setError("E a senha, qual é?");
            return false;
        }

        return true;
    }

    private boolean validarLogin(String login, String senha) {
        DataBase db = Room.databaseBuilder(getApplicationContext(), DataBase.class, "Base de Dados")
                .allowMainThreadQueries().build();
        Usuario usuario = db.getUserDao().getUserLogin(login, senha);

        return usuario != null;
    }

    private void showAlertDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}