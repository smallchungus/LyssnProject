package com.lyssn;

import java.sql.Timestamp;

public class User {
    private long userid;
    private String fname;  // first name
    private String name;   // last name - keeping the DB column name for consistency
    private Timestamp signupdate;

    // Empty constructor might be useful later
    public User() {}

    public User(long userid, String fname, String name, Timestamp signupdate) {
        this.userid = userid;
        this.fname = fname;
        this.name = name;
        this.signupdate = signupdate;
    }

    // Standard getters/setters - IDE generated these for me
    public long getUserid() { return userid; }
    public void setUserid(long userid) { this.userid = userid; }
    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Timestamp getSignupdate() { return signupdate; }
    public void setSignupdate(Timestamp signupdate) { this.signupdate = signupdate; }

    @Override
    public String toString() {
        return String.format("User[%d]: %s %s (joined: %s)",
                userid, fname, name, signupdate);
    }
}