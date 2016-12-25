package lecture.mobile.final_project.ma01_20141105.search;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lecture.mobile.final_project.ma01_20141105.ListViewAdapter;
import lecture.mobile.final_project.ma01_20141105.MainActivity;
import lecture.mobile.final_project.ma01_20141105.MyLocation;
import lecture.mobile.final_project.ma01_20141105.MyXmlParser;
import lecture.mobile.final_project.ma01_20141105.R;
import lecture.mobile.final_project.ma01_20141105.model.Sights;

public class SearchMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapFragment mapFragment;
    double mylat;
    double mylong;
    ArrayList<Sights> resultList;
    ArrayList<Marker> markerArrayList = new ArrayList<Marker>();
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_maps);
        //처음 내 시작 위치 가져오기
        try {
            MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
                @Override
                public void gotLocation(Location location) {
                    Log.d("lat", "" + location.getLatitude());
                    mylat = location.getLatitude();
                    mylong = location.getLongitude();
                }
            };
            MyLocation myLocation = new MyLocation();
            myLocation.getLocation(getApplicationContext(), locationResult);
        } catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setMessage("Please Retry")
                    .setNegativeButton("Cancel", null).show();
        }
        //전체 마커 불러오기
        address = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?" +
                "ServiceKey=vdRPu4O8yDZcv8y1l9hyQNu%2BX03byFoolXir7S5%2F6yJynSYfX9F47m919NCjAz80nLp%2FnnuY1%2FNqxVSSFVz6RQ%3D%3D&" +
                "contentTypeId=12&areaCode=&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&" +
                "MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=500&pageNo=1";
        new NetworkAsyncTask().execute(address);
        mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.search_map);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng mylatlng = new LatLng(mylat, mylong);
        mMap.addMarker(new MarkerOptions().position(mylatlng).title("My position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mylatlng));
    }

    class NetworkAsyncTask extends AsyncTask<String, Integer, String> {

        public final static String TAG = "NetworkAsyncTask";
        public final static int TIME_OUT = 10000;

        ProgressDialog progressDlg;
        String address;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDlg = ProgressDialog.show(SearchMapsActivity.this, "Wait", "Maploading...");
        }

        @Override
        protected String doInBackground(String... strings) {
            address = strings[0];
            StringBuilder resultBuilder = new StringBuilder();

            try {
                URL url = new URL(address);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                if (conn != null) {
                    conn.setConnectTimeout(TIME_OUT);
                    conn.setUseCaches(false);
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        for (String line = br.readLine(); line != null; line = br.readLine()) {
                            resultBuilder.append(line + '\n');
                        }

                        br.close();
                    }
                    conn.disconnect();
                }

            } catch (MalformedURLException ex) {
                ex.printStackTrace();
                Toast.makeText(SearchMapsActivity.this, "Malformed URL", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return resultBuilder.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("resultBuilder", "" + result);
            SightsXmlParser parser = new SightsXmlParser();

//            어댑터에 이전에 보여준 데이터가 있을 경우 클리어
            if (!resultList.isEmpty()) resultList.clear();

            Log.d("bb", "" + resultList.get(0).getMapx());
            resultList = parser.parse(result);

//          resultList에 담긴 위도,경도로 맵에 뿌려주기...
            for (int i = 0; i < resultList.size(); i++) {

                //마커 설정
                MarkerOptions options = new MarkerOptions();
                options.position(
                        new LatLng(
                                Double.parseDouble(resultList.get(i).getMapy()),
                                Double.parseDouble(resultList.get(i).getMapx())))
                        .title(resultList.get(i).getTitle())
                        .snippet(resultList.get(i).getAddr1());

                //지도에 마커 추가
                Marker tempMarker = mMap.addMarker(options);
                markerArrayList.add(tempMarker);

                progressDlg.dismiss();
            }
        }
    }
}


