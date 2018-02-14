package looko.looksteam.demo.entity;

public class App {
    private Integer appid;

    private String appname;

    private String chname;

    private Integer price;

    private String imgIconUrl;

    private String imgLogoUrl;

    private String pic1;

    private String pic2;

    private String pic3;

    private String pic4;

    private String pic5;

    private String updatetime;

    private String extraVarchar;

    private Integer extraInt;

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname == null ? null : appname.trim();
    }

    public String getChname() {
        return chname;
    }

    public void setChname(String chname) {
        this.chname = chname == null ? null : chname.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImgIconUrl() {
        return imgIconUrl;
    }

    public void setImgIconUrl(String imgIconUrl) {
        this.imgIconUrl = imgIconUrl == null ? null : imgIconUrl.trim();
    }

    public String getImgLogoUrl() {
        return imgLogoUrl;
    }

    public void setImgLogoUrl(String imgLogoUrl) {
        this.imgLogoUrl = imgLogoUrl == null ? null : imgLogoUrl.trim();
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1 == null ? null : pic1.trim();
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2 == null ? null : pic2.trim();
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3 == null ? null : pic3.trim();
    }

    public String getPic4() {
        return pic4;
    }

    public void setPic4(String pic4) {
        this.pic4 = pic4 == null ? null : pic4.trim();
    }

    public String getPic5() {
        return pic5;
    }

    public void setPic5(String pic5) {
        this.pic5 = pic5 == null ? null : pic5.trim();
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime == null ? null : updatetime.trim();
    }

    public String getExtraVarchar() {
        return extraVarchar;
    }

    public void setExtraVarchar(String extraVarchar) {
        this.extraVarchar = extraVarchar == null ? null : extraVarchar.trim();
    }

    public Integer getExtraInt() {
        return extraInt;
    }

    public void setExtraInt(Integer extraInt) {
        this.extraInt = extraInt;
    }
}