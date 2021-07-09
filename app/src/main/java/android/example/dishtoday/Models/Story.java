package android.example.dishtoday.Models;

public class Story {
    int addStoryImg, profile_image,storyType;
    String name;

    public Story(int addStoryImg, int profile_image, int storyType, String name) {
        this.addStoryImg = addStoryImg;
        this.profile_image = profile_image;
        this.storyType = storyType;
        this.name = name;
    }

    public int getAddStoryImg() {
        return addStoryImg;
    }

    public void setAddStoryImg(int addStoryImg) {
        this.addStoryImg = addStoryImg;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(int profile_image) {
        this.profile_image = profile_image;
    }

    public int getStoryType() {
        return storyType;
    }

    public void setStoryType(int storyType) {
        this.storyType = storyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
