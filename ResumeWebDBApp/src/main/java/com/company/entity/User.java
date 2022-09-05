package com.company.entity;

import java.sql.Date;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String profileDesc;
    private String address;
    private String phone;
    private Date birthDate;
    private Country nationality;
    private Country birthPlace;
    private List<UserSkill> skills;
    private List<EmployementHistory> history;
    
    
    public User(){
        
    }

    public User(int id) {
        this.id = id;
    }
    

    public User(int id, String name, String surname, String email, String profileDesc, String address, String phone, Date birthDate, Country nationality, Country birthPlace) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.profileDesc = profileDesc;
        this.address = address;
        this.phone = phone;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.birthPlace = birthPlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Country getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Country birthPlace) {
        this.birthPlace = birthPlace;
    }

    public List<UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkill> skills) {
        this.skills = skills;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<EmployementHistory> getHistory() {
        return history;
    }

    public void setHistory(List<EmployementHistory> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", profileDesc=" + profileDesc + ", address=" + address + ", phone=" + phone + ", birthDate=" + birthDate + ", nationality=" + nationality + ", birthPlace=" + birthPlace + ", skills=" + skills + ", history=" + history + '}';
    }

}
