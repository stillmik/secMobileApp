package com.example.secmobileapp;

public class WeatherReportModel {

    private int id;
    private String weatherStateName;
    private String weatherStateAbbr;
    private String windDirectionCompass;
    private String created;
    private String applicableDate;
    private double minTemp;
    private double maxTemp;
    private double theTemp;
    private double windSpeed;
    private double windDirection;
    private int airPressure;
    private int humidity;
    private double visibility;
    private int predictability;


    public WeatherReportModel(int id, String weatherStateName, String weatherStateAbbr, String windDirectionCompass, String created, String applicableDate, double minTemp, double maxTemp, double theTemp, double windSpeed, double windDirection, int airPressure, int humidity, double visibility, int predictability) {
        this.id = id;
        this.weatherStateName = weatherStateName;
        this.weatherStateAbbr = weatherStateAbbr;
        this.windDirectionCompass = windDirectionCompass;
        this.created = created;
        this.applicableDate = applicableDate;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.theTemp = theTemp;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.airPressure = airPressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.predictability = predictability;
    }

    public WeatherReportModel(){

    }

    @Override
    public String toString() {
        return "WeatherReportModel{" +
                "id=" + id +
                ", weatherStateName='" + weatherStateName + '\'' +
                ", weatherStateAbbr='" + weatherStateAbbr + '\'' +
                ", windDirectionCompass='" + windDirectionCompass + '\'' +
                ", created='" + created + '\'' +
                ", applicableDate='" + applicableDate + '\'' +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", theTemp=" + theTemp +
                ", windSpeed=" + windSpeed +
                ", windDirection=" + windDirection +
                ", airPressure=" + airPressure +
                ", humidity=" + humidity +
                ", visibility=" + visibility +
                ", predictability=" + predictability +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeatherStateName() {
        return weatherStateName;
    }

    public void setWeatherStateName(String weatherStateName) {
        this.weatherStateName = weatherStateName;
    }

    public String getWeatherStateAbbr() {
        return weatherStateAbbr;
    }

    public void setWeatherStateAbbr(String weatherStateAbbr) {
        this.weatherStateAbbr = weatherStateAbbr;
    }

    public String getWindDirectionCompass() {
        return windDirectionCompass;
    }

    public void setWindDirectionCompass(String windDirectionCompass) {
        this.windDirectionCompass = windDirectionCompass;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getApplicableDate() {
        return applicableDate;
    }

    public void setApplicableDate(String applicableDate) {
        this.applicableDate = applicableDate;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getTheTemp() {
        return theTemp;
    }

    public void setTheTemp(double theTemp) {
        this.theTemp = theTemp;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = Math.round(windDirection*10.0)/10.0;
    }

    public int getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(int airPressure) {
        this.airPressure = airPressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public int getPredictability() {
        return predictability;
    }

    public void setPredictability(int predictability) {
        this.predictability = predictability;
    }
}
