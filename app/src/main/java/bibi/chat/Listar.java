package bibi.chat;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class Listar extends Activity {

    private TextView showtxt;

    Gerenciador dbm;
    Cursor dados;
    ListarDados ld;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        dbm = new Gerenciador(getApplicationContext());

        atualizarLista();

    }
    public void onItemClick(View view,int position){
        showtxt = findViewById(R.id.txt);
        showtxt.setText(ld.getItem(position));
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

        RecyclerView recyclerView = findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ld = new ListarDados(this, lNomes);
        ld.setClickListener(this::onItemClick);
        recyclerView.setAdapter(ld);
    }
}