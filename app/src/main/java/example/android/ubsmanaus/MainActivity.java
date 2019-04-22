package example.android.ubsmanaus;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import example.android.ubsmanaus.Adapter.Adapter;
import example.android.ubsmanaus.Model.Ubs;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Adapter adapter;
    private List<Ubs> ubsList;
    private ListView listView;
    private SwipeRefreshLayout swiperefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swiperefresh = (SwipeRefreshLayout) findViewById((R.id.swiperefresh));
        //seta cores
        swiperefresh.setColorScheme(R.color.colorPrimary, R.color.colorAccent);
        swiperefresh.setOnRefreshListener(this);

        listView = (ListView) findViewById(R.id.listView);

        ubsList = new ArrayList<Ubs>();

        adapter = new Adapter(this, ubsList);

        getData();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplication(), ubsList.get(position).toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getData() {

        String[] nomes = {"UBS LUIZ MONTENEGRO", "UBS AJURICABA", "UBS REDENÇÃO", "UBS SANTOS DUMONT"};
        String[] bairros = {"CHAPADA", "LÍRIO DO VALE", "COMPENSA", "PLANALTO"};

        ubsList.clear();
        for (int i=0; i<20; i++){
            int nextInt = new Random().nextInt(4);
            Ubs ubs = new Ubs(i, nomes[nextInt], "",  bairros[nextInt], "", "", "", "", "");
            ubsList.add(ubs);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        getData();

    }
}
