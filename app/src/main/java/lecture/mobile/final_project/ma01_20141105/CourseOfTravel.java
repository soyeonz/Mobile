package lecture.mobile.final_project.ma01_20141105;

/**
 * Created by USER on 2016-10-12.
 */

public class CourseOfTravel {
    private int _id;
    private String title; //제목
    private String contentid; //테마별 id
    private String readcount; // 조횟수
    private String mapX; //지도의 x 좌표값
    private String mapY; //지도의 y 좌표값

    public CourseOfTravel(){}

    public CourseOfTravel(int _id, String title, String contentid, String readcount, String firstimage2) {
        this._id = _id;
        this.title = title;
        this.contentid = contentid;
        this.readcount = readcount;
        this.firstimage2 = firstimage2;
    }


    public String getFirstimage2() {
        return firstimage2;
    }

    public void setFirstimage2(String firstimage2) {
        this.firstimage2 = firstimage2;
    }

    private String firstimage2;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getReadcount() {
        return readcount;
    }

    public void setReadcount(String readcount) {
        this.readcount = readcount;
    }

    public String getMapX() {
        return mapX;
    }

    public void setMapX(String mapX) {
        this.mapX = mapX;
    }

    public String getMapY() {
        return mapY;
    }

    public void setMapY(String mapY) {
        this.mapY = mapY;
    }

}
