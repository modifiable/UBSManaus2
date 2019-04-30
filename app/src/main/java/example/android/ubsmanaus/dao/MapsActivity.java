package example.android.ubsmanaus.dao;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import example.android.ubsmanaus.Model.Cidade;
import example.android.ubsmanaus.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Cidade cidade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //pega ubs passada pela main
        cidade = (Cidade) getIntent().getSerializableExtra("cidade");

        //inicializa mapFragment
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        double lat = 0.0;
        double lng = 0.0;
        // colocar o parser
        //coordenadas da ubs
        LatLng cidadelatlng = new LatLng(lat, lng);

        //adiciona marcador e move a camera do mapa p ele
        googleMap.addMarker(new MarkerOptions().position(cidadelatlng).title(cidade.name));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cidadelatlng, 12.0f));
    }
}