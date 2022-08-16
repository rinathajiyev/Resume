package com.company.entity;

public class UserSkill {
    private Integer id;
    private User userId;
    private Skill skillId;
    private int power;

    public UserSkill() {
    }

    public UserSkill(Integer id, User userId, Skill skillId, int power) {
        this.id = id;
        this.userId = userId;
        this.skillId = skillId;
        this.power = power;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Skill getSkillId() {
        return skillId;
    }

    public void setSkillId(Skill skillId) {
        this.skillId = skillId;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "UserSkill{" + "id=" + id + ", userId=" + userId + ", skillId=" + skillId + ", power=" + power + '}';
    }
  
}
