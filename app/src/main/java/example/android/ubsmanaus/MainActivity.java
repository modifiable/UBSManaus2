package example.android.ubsmanaus;

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
import example.android.ubsmanaus.Model.Ubs;
import example.android.ubsmanaus.util.HttpRetro;
import retrofit2.*;

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

        getDataRetro();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplication(), ubsList.get(position).toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getDataRetro(){
        HttpRetro.getUbsClient().getUbs().enqueue(new Callback<List<Ubs>>() {
            public void onResponse(Call<List<Ubs>> call, Response<List<Ubs>> response) {
                if (response.isSuccessful()) {
                    List<Ubs> ubsBody = response.body();
                    ubsList.clear();
                    for (Ubs ubs : ubsBody) {
                        ubsList.add(ubs);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    System.out.println(response.errorBody());
                }
                swiperefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Ubs>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onRefresh() {
        getDataRetro();
    }
}
