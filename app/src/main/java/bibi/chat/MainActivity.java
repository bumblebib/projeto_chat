package bibi.chat;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends Activity{
    private EditText cod;
    private EditText nome;
    private Button btn;

    Gerenciador dbm;
    Cursor dados;
    ListarDados ld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define banco
        dbm = new Gerenciador(getApplicationContext());

        //define elementos
        cod = findViewById(R.id.txtcod);
        nome = findViewById(R.id.txtnome);
        btn = findViewById(R.id.btn);

        //evento click
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String i = cod.getText().toString();
                String n = nome.getText().toString();
                dbm.addUsuario(i, n);

                cod.setText("Código");
                nome.setText("Nome");

                atualizarLista();

            }
        });

        //Listando Dados
        atualizarLista();
    }

        public void onItemClick(View view,int position){
            Toast.makeText(this,"Você clicou em: " + ld.getItem(position) + "N: "+position , Toast.LENGTH_SHORT).show();
        }

        public void atualizarLista(){
            // Criando lista de nomes
            ArrayList<String> lNomes = new ArrayList();
            dados = dbm.listarUsuarios();

            while(!dados.isLast()){
                String registro = "Cod: "+dados.getString(0);
                registro+=" - Nome: "+dados.getString(1);
                lNomes.add(registro);
                dados.moveToNext();
            }

            //Atualizando o RecycleView
            RecyclerView recyclerView = findViewById(R.id.lista);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ld = new ListarDados(this, lNomes);
            ld.setClickListener(this::onItemClick);
            recyclerView.setAdapter(ld);
    }
}