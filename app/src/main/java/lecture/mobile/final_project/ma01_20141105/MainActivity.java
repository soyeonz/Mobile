// 과제01
// 작성일: 2016. 10. 13
// 작성자: 01분반 20141105 임소연 (예: 01분반 20140000 동덕)
package lecture.mobile.final_project.ma01_20141105;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    String address;

    ListViewAdapter adapter;

    ArrayList<CourseOfTravel> resultList;
    ArrayList<CourseOfTravel> searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView)findViewById(R.id.list_view);


        resultList = new ArrayList<CourseOfTravel>();
        adapter = new ListViewAdapter(this, R.layout.custom_listview, resultList);
        address="http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchCourse?" +
                "ServiceKey=vdRPu4O8yDZcv8y1l9hyQNu%2BX03byFoolXir7S5%2F6yJynSYfX9F47m919NCjAz80nLp%2FnnuY1%2FNqxVSSFVz6RQ%3D%3D&numOfRows=100&pageSize=100&pageNo=1&startPage=1&arrange=C&listYN=Y&contentTypeId=25&MobileOS=ETC&MobileApp=공유자원포털";
        //전체 리스트뷰 불러오기
        new NetworkAsyncTask().execute(address);

        //리스트뷰 항목 하나 클릭했을 경우 해당 테마의 위치 보여주기-> 구글맵으로 넘기기
        //이걸 반대로해야됨. 구글맵의 마커 클릭하면 해당 테마 뷰 보여주는것으로
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("mapx",resultList.get(i).getMapX()); //x좌표값, 경도
                intent.putExtra("mapy",resultList.get(i).getMapY()); //y좌표값, 위도
                Log.d("x",resultList.get(i).getMapX());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setTitleTextAppearance(this, R.style.AppTheme);
        listview = (ListView)findViewById(R.id.list_view);
        //테마의 제목에 포함된 단어로 검색시 리스트뷰를 다시 셋 하는 searchview 구현
        searchList = new ArrayList<CourseOfTravel>();
        SearchView mSearchView;
        mSearchView = (SearchView) toolbar.getMenu().findItem(R.id.action_search).getActionView();
        mSearchView.setQueryHint("테마의 제목에 포함된 단어로 검색 가능");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //최초 검색시
            @Override
            public boolean onQueryTextSubmit(String s) {
                final String key = s;
                Log.d("sss",key);
                for(int i=0;i<resultList.size();i++) {
                    Log.d("fff",resultList.get(i).getTitle());
                    if (resultList.get(i).getTitle().contains(key)) {
                        searchList.add(new CourseOfTravel(resultList.get(i).get_id(), resultList.get(i).getTitle(), resultList.get(i).getContentid(), resultList.get(i).getReadcount(), resultList.get(i).getFirstimage2()));
                    }
                }
                Log.d("nnn",searchList.get(0).getTitle());
                adapter = new ListViewAdapter(MainActivity.this,R.layout.custom_listview,searchList);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                return false;
            }

            //검색어 수정시
            @Override
            public boolean onQueryTextChange(String s) {
                final String key = s;
                if (!searchList.isEmpty()) searchList.clear();
                for(int i=0;i<resultList.size();i++) {
                    if (resultList.get(i).getTitle().contains(key)) {
                        searchList.add(new CourseOfTravel(resultList.get(i).get_id(), resultList.get(i).getTitle(), resultList.get(i).getContentid(), resultList.get(i).getReadcount(), resultList.get(i).getFirstimage2()));
                    }
                }
                adapter = new ListViewAdapter(MainActivity.this,R.layout.custom_listview,searchList);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return false;
            }

        });
        return true;
    }

    class NetworkAsyncTask extends AsyncTask<String, Integer, String> {

        public final static String TAG = "NetworkAsyncTask";
        public final static int TIME_OUT = 10000;

        ProgressDialog progressDlg;
        String address;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDlg = ProgressDialog.show(MainActivity.this, "Wait", "Downloading...");
        }

        @Override
        protected String doInBackground(String... strings) {
            address = strings[0];
            StringBuilder resultBuilder = new StringBuilder();

            try {
                URL url = new URL(address);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

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
                Toast.makeText(MainActivity.this, "Malformed URL", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return resultBuilder.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("resultBuilder",""+result);
            MyXmlParser parser = new MyXmlParser();

//            어댑터에 이전에 보여준 데이터가 있을 경우 클리어
            if (!resultList.isEmpty()) resultList.clear();

            resultList = parser.parse(result);
            Log.d("bb",""+resultList.get(0).getMapX());

//            리스트뷰에 연결되어 있는 어댑터에 parsing 결과 ArrayList 를 추가
            adapter = new ListViewAdapter(MainActivity.this, R.layout.custom_listview, resultList);
            listview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            progressDlg.dismiss();
        }
    }



}
