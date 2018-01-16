package com.shivamamin.android.passwordmanager;

import java.util.UUID;

/**
 * Created by Shivam Amin on 2018-01-03.
 */

public class Service {
    private UUID mID;
    private String mService;
    private String mUsername;
    private String mPassword;
    private String mDescription;
    public UUID getID() {
        return mID;
    }
    public String getService() {
        return mService;
    }
    public void setService(String service) {
        mService = service;
    }
    public String getUsername() {
        return mUsername;
    }
    public void setUsername(String username) {
        mUsername = username;
    }
    public String getPassword() {
        return mPassword;
    }
    public void setPassword(String password) {
        mPassword = password;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String description) {
        mDescription = description;
    }
    public Service() {
        mID = UUID.randomUUID();
    }
    public Service(UUID id) {
        mID = id;
    }
}
