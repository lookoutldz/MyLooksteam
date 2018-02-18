package looko.looksteam.demo.entity;

public class Friend extends FriendKey {
    private String relationship;

    private Integer friendSince;

    private String updatetime;

    private String extraVarchar;

    private Integer extraInt;

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship == null ? null : relationship.trim();
    }

    public Integer getFriendSince() {
        return friendSince;
    }

    public void setFriendSince(Integer friendSince) {
        this.friendSince = friendSince;
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