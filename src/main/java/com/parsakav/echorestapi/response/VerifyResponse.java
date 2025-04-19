package com.parsakav.echorestapi.response;

// VerifyResponse.java
public class VerifyResponse {
    private String message;
    private String filePath;
    private String biography;      // OTP from bio
    private String profilePicUrl;  // original URL

    public VerifyResponse(String message, String filePath, String biography, String profilePicUrl) {
        this.message = message;
        this.filePath = filePath;
        this.biography = biography;
        this.profilePicUrl = profilePicUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }
}
