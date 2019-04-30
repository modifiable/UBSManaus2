package example.android.ubsmanaus;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import example.android.ubsmanaus.dao.MapsActivity;
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

                hasPermission();,

                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("cidade",cidadeList.get(position));
                startActivity(intent);
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

        // se tiver conexao faz get, senao pega do sqlite
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
            Toast.makeText(this,"Sem Conexão, listando Cidade do banco...",Toast.LENGTH_SHORT).show();
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

    void hasPermission(){
        //pede permissao de localizacao
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // ja pediu permissao?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {

                // solicita permissao de localizacao
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }
        }
    }

    @Override
    public void onRefresh() {
        getDataRetro();
    }
}
