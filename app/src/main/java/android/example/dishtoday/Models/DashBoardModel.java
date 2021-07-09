package android.example.dishtoday.Models;



public class DashBoardModel {
    int profile, dashboardImg,save;
    String user_name, bio, like, comment, share;

    public DashBoardModel(int prfile, int dashboardImg,int save, String user_name, String bio, String like, String comment, String share) {
        this.profile = prfile;
        this.dashboardImg = dashboardImg;
        this.user_name = user_name;
        this.bio = bio;
        this.like = like;
        this.comment = comment;
        this.share = share;
        this.save=save;
    }

    public int getPrfile() {
        return profile;
    }

    public void setPrfile(int prfile) {
        this.profile = prfile;
    }

    public int getDashboardImg() {
        return dashboardImg;
    }

    public void setDashboardImg(int dashboardImg) {
        this.dashboardImg = dashboardImg;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public void setSave(int save) {
        this.save = save;
    }

    public  int getSave(){
        return save;

    }


}

