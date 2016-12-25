package lecture.mobile.final_project.ma01_20141105;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    Intent intent;
    Double lat, longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        intent = getIntent();
        //string 형태로 되어있는 x,y좌표를 double형인 위도, 경도 값으로 바꿔줌
        lat = Double.parseDouble(intent.getStringExtra("mapy"));
        longi = Double.parseDouble(intent.getStringExtra("mapx"));
        Log.d("aaaa",""+lat);
        Log.d("aaaa",""+longi);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    //맵 초기화 시 intent로 받은 위도 , 경도 값을 활용하여 지도에 마커를 표시해주고 그 위치로 줌인한다
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        try {
            Log.d("lat",""+lat);
            LatLng pos = new LatLng(lat, longi);
            Log.d("" + pos.toString(), "initMap");

            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 14));
            mGoogleMap.addMarker(new MarkerOptions().position(pos).title("Good Travel!"));
        } catch (Exception e) {
        }
    }
}
