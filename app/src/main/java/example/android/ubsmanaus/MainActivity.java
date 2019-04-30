package example.android.ubsmanaus;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.android.ubsmanaus.Adapter.Adapter;
import example.android.ubsmanaus.Model.Cidade;
import example.android.ubsmanaus.dao.Repositorio;
import example.android.ubsmanaus.util.HttpRetro;
import retrofit2.*;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Adapter adapter;
    private List<Cidade> cidadeList;
    private ListView listView;
    private SwipeRefreshLayout swiperefresh;
    Repositorio db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swiperefresh = (SwipeRefreshLayout) findViewById((R.id.swiperefresh));
        //seta cores
        swiperefresh.setColorScheme(R.color.colorPrimary, R.color.colorAccent);
        swiperefresh.setOnRefreshListener(this);

        listView = (ListView) findViewById(R.id.listView);

        cidadeList = new ArrayList<Cidade>();

        adapter = new Adapter(this, cidadeList);
        db = new Repositorio(getBaseContext());
        getDataRetro();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplication(), cidadeList.get(position).toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getDataSqlite() {
        cidadeList.clear();
        cidadeList.addAll(db.listarCidade());
        adapter.notifyDataSetChanged();
    }

    public void getDataRetro() {

        swiperefresh.setRefreshing(true);


        if (isConnected()) {
            HttpRetro.getCidadeClient().getCidade().enqueue(new Callback<List<Cidade>>() {
                public void onResponse(Call<List<Cidade>> call, Response<List<Cidade>> response) {
                    if (response.isSuccessful()) {
                        List<Cidade> cidadeBody = response.body();
                        cidadeList.clear();

                        db.excluirAll();

                        for (Cidade cidade : cidadeBody) {
                            cidadeList.add(cidade);
                            db.inserir(cidade);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        System.out.println(response.errorBody());
                    }
                    swiperefresh.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<List<Cidade>> call, Throwable t) {
                    t.printStackTrace();
                }

            });

        }else {
            swiperefresh.setRefreshing(false);
            Toast.makeText(this,"Sem Conexão, listando Cidades do banco...",Toast.LENGTH_SHORT).show();
            getDataSqlite();
        }

    }

    public Boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( cm != null ) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnected();
        }
        return false;
    }

    @Override
    public void onRefresh() {
        getDataRetro();
    }
}
