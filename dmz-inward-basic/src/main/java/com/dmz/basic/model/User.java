package com.dmz.basic.model;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_dmz_user.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_dmz_user.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_dmz_user.user_gender
     *
     * @mbggenerated
     */
    private String userGender;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_dmz_user.id
     *
     * @return the value of t_dmz_user.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_dmz_user.id
     *
     * @param id the value for t_dmz_user.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_dmz_user.user_name
     *
     * @return the value of t_dmz_user.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_dmz_user.user_name
     *
     * @param userName the value for t_dmz_user.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_dmz_user.user_gender
     *
     * @return the value of t_dmz_user.user_gender
     *
     * @mbggenerated
     */
    public String getUserGender() {
        return userGender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_dmz_user.user_gender
     *
     * @param userGender the value for t_dmz_user.user_gender
     *
     * @mbggenerated
     */
    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
}