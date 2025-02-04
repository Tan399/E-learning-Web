package com.example.ProjectElearning.Model;

public class CourseDetails {
    private Long courseid;
    private String coursename;
    private String description;
    private String level;
    private Long instructorId;
    private String category;
    private int price;
    private String videoUrl;
    private Long enrolledCount;
    private byte[] courseImage;
    private Long duration;

    public CourseDetails() {
    }

    public CourseDetails(Long courseid, String coursename, String description, String level, Long instructorId, String category, int price, String videoUrl, Long enrolledCount, byte[] courseImage, Long duration) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.description = description;
        this.level = level;
        this.instructorId = instructorId;
        this.category = category;
        this.price = price;
        this.videoUrl = videoUrl;
        this.enrolledCount = enrolledCount;
        this.courseImage = courseImage;
        this.duration = duration;
    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getEnrolledCount() {
        return enrolledCount;
    }

    public void setEnrolledCount(Long enrolledCount) {
        this.enrolledCount = enrolledCount;
    }

    public byte[] getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(byte[] courseImage) {
        this.courseImage = courseImage;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
