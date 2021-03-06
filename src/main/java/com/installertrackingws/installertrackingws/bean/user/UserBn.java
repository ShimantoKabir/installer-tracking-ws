package com.installertrackingws.installertrackingws.bean.user;

import com.installertrackingws.installertrackingws.bean.location.LocationBn;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserBn {

    public int id;
    public String password;
    public String userEmail;
    public String userName;
    public String sessionId;
    public Integer isUserActive;
    public Integer isUserApproved;
    public Integer isOnline;
    public Integer deptId;
    public String token;
    public String ip;
    public Integer modifiedBy;
    public Integer isTyping;
    public Integer forWho;
    public Date createdDate;
    private Date modifyDate;
    private Date lastPresenceDate;
    public String deptName;
    public String newPassword;
    public String address;
    public double lat;
    public double lon;
    List<LocationBn> locationBnList = new ArrayList<>();
    public LocationBn locationBn;

    public UserBn() {}


    public LocationBn getLocationBn() {
        return locationBn;
    }

    public void setLocationBn(LocationBn locationBn) {
        this.locationBn = locationBn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getLastPresenceDate() {
        return lastPresenceDate;
    }

    public void setLastPresenceDate(Date lastPresenceDate) {
        this.lastPresenceDate = lastPresenceDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsUserActive() {
        return isUserActive;
    }

    public void setIsUserActive(Integer isUserActive) {
        this.isUserActive = isUserActive;
    }

    public Integer getIsUserApproved() {
        return isUserApproved;
    }

    public void setIsUserApproved(Integer isUserApproved) {
        this.isUserApproved = isUserApproved;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getIsTyping() {
        return isTyping;
    }

    public void setIsTyping(Integer isTyping) {
        this.isTyping = isTyping;
    }

    public Integer getForWho() {
        return forWho;
    }

    public void setForWho(Integer forWho) {
        this.forWho = forWho;
    }

    public List<LocationBn> getLocationBnList() {
        return locationBnList;
    }

    public void setLocationBnList(List<LocationBn> locationBnList) {
        this.locationBnList = locationBnList;
    }
}
