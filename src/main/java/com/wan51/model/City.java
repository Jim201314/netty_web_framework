package com.wan51.model;

public class City {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.cityName
     *
     * @mbg.generated
     */
    private String cityname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.pinying
     *
     * @mbg.generated
     */
    private String pinying;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column city.status
     *
     * @mbg.generated
     */
    private Short status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.id
     *
     * @return the value of city.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.id
     *
     * @param id the value for city.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.cityName
     *
     * @return the value of city.cityName
     *
     * @mbg.generated
     */
    public String getCityname() {
        return cityname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.cityName
     *
     * @param cityname the value for city.cityName
     *
     * @mbg.generated
     */
    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.pinying
     *
     * @return the value of city.pinying
     *
     * @mbg.generated
     */
    public String getPinying() {
        return pinying;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.pinying
     *
     * @param pinying the value for city.pinying
     *
     * @mbg.generated
     */
    public void setPinying(String pinying) {
        this.pinying = pinying == null ? null : pinying.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column city.status
     *
     * @return the value of city.status
     *
     * @mbg.generated
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column city.status
     *
     * @param status the value for city.status
     *
     * @mbg.generated
     */
    public void setStatus(Short status) {
        this.status = status;
    }
}