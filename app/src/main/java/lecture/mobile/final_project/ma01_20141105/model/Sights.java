package lecture.mobile.final_project.ma01_20141105.model;

/**
 * Created by USER on 2016-12-25.
 */
//관광지 정보
    /*<addr1>강원도 고성군 죽왕면 심층수길 124-19</addr1>
<addr2>(죽왕면)</addr2>
<areacode>32</areacode>
<cat1>A02</cat1>
<cat2>A0204</cat2>
<cat3>A02040600</cat3>
<contentid>2360786</contentid>
<contenttypeid>12</contenttypeid>
<createdtime>20160111104045</createdtime>
<firstimage>http://tong.visitkorea.or.kr/cms/resource/75/2360775_image2_1.jpg</firstimage>
<firstimage2>http://tong.visitkorea.or.kr/cms/resource/75/2360775_image2_1.jpg</firstimage2>
<mapx>128.5216381039</mapx>
<mapy>38.3343742648</mapy>
<mlevel>6</mlevel>
<modifiedtime>20160111104045</modifiedtime>
<readcount>1340</readcount>
<sigungucode>2</sigungucode>
<tel>033-698-9900</tel>
<title>㈜강원심층수</title>
<zipcode>24747</zipcode>*/
public class Sights {
    private String addr1;
    private String addr2;
    private int areacode;
    private String cat1;
    private String cat2;
    private String cat3;
    private int contentid;
    private int contenttypeid;
    private int createdtime;
    private String firstimage;
    private String firstimage2;
    private String mapx;
    private String mapy;
    private int mlevel;
    private int modifiedtime;
    private int readcount;
    private int sigungucode;
    private String tel;
    private String title;
    private int zipcode;

    public Sights() {
    }

    public Sights(String addr1, String addr2, int areacode, String cat1, String cat2, String cat3, int contentid, int contenttypeid, int createdtime, String firstimage, String firstimage2, String mapx, String mapy, int mlevel, int modifiedtime, int readcount, int sigungucode, String tel, String title, int zipcode) {
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.areacode = areacode;
        this.cat1 = cat1;
        this.cat2 = cat2;
        this.cat3 = cat3;
        this.contentid = contentid;
        this.contenttypeid = contenttypeid;
        this.createdtime = createdtime;
        this.firstimage = firstimage;
        this.firstimage2 = firstimage2;
        this.mapx = mapx;
        this.mapy = mapy;
        this.mlevel = mlevel;
        this.modifiedtime = modifiedtime;
        this.readcount = readcount;
        this.sigungucode = sigungucode;
        this.tel = tel;
        this.title = title;
        this.zipcode = zipcode;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public int getAreacode() {
        return areacode;
    }

    public void setAreacode(int areacode) {
        this.areacode = areacode;
    }

    public String getCat1() {
        return cat1;
    }

    public void setCat1(String cat1) {
        this.cat1 = cat1;
    }

    public String getCat2() {
        return cat2;
    }

    public void setCat2(String cat2) {
        this.cat2 = cat2;
    }

    public String getCat3() {
        return cat3;
    }

    public void setCat3(String cat3) {
        this.cat3 = cat3;
    }

    public int getContentid() {
        return contentid;
    }

    public void setContentid(int contentid) {
        this.contentid = contentid;
    }

    public int getContenttypeid() {
        return contenttypeid;
    }

    public void setContenttypeid(int contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    public int getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(int createdtime) {
        this.createdtime = createdtime;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getFirstimage2() {
        return firstimage2;
    }

    public void setFirstimage2(String firstimage2) {
        this.firstimage2 = firstimage2;
    }

    public String getMapx() {
        return mapx;
    }

    public void setMapx(String mapx) {
        this.mapx = mapx;
    }

    public String getMapy() {
        return mapy;
    }

    public void setMapy(String mapy) {
        this.mapy = mapy;
    }

    public int getMlevel() {
        return mlevel;
    }

    public void setMlevel(int mlevel) {
        this.mlevel = mlevel;
    }

    public int getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(int modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public int getReadcount() {
        return readcount;
    }

    public void setReadcount(int readcount) {
        this.readcount = readcount;
    }

    public int getSigungucode() {
        return sigungucode;
    }

    public void setSigungucode(int sigungucode) {
        this.sigungucode = sigungucode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}
