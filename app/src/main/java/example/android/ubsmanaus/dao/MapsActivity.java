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

import example.android.ubsmanaus.Model.Ubs;
import example.android.ubsmanaus.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Ubs ubs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //pega ubs passada pela main
        ubs = (Ubs) getIntent().getSerializableExtra("ubs");

        //inicializa mapFragment
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //coordenadas da ubs
        LatLng ubslatlng = new LatLng(Double.parseDouble(ubs.latitude), Double.parseDouble(ubs.longitude));

        //adiciona marcador e move a camera do mapa p ele
        googleMap.addMarker(new MarkerOptions().position(ubslatlng).title(ubs.nome));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubslatlng, 12.0f));
    }
}