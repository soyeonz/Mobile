package lecture.mobile.final_project.ma01_20141105;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lecture.mobile.final_project.ma01_20141105.model.CourseOfTravel;

/**
 * Created by USER on 2016-10-12.
 */
public class ListViewAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    int layout;
    ArrayList<CourseOfTravel> list;


    public ListViewAdapter(Context context, int resource, ArrayList<CourseOfTravel> list) {
        this.context = context;
        this.layout = resource;
        this.list = list;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    //    원본 데이터의 전체 개수 반환
    @Override
    public int getCount() {
        return list.size();
    }

    //    원본 데이터의 특정 항목 반환
    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    //    원본 데이터 특정 항목의 아이디 반환
    @Override
    public long getItemId(int i) {
        return list.get(i).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_listview, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView contentId = (TextView) convertView.findViewById(R.id.contentId);
        TextView readcount = (TextView) convertView.findViewById(R.id.readcount);

        title.setText(list.get(pos).getTitle());
        contentId.setText(list.get(pos).getContentid());
        readcount.setText(list.get(pos).getReadcount());

        return convertView;
    }
}
