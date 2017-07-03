package edu.ucab.desarrollo.fitucab.common.entities;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by jaorr on 22/05/17.
 */
public class Diet extends Entity{
    private int _id;
    private int _calorie;
    private LocalDate _dateTime;
    private String _date;
    private Date  _fecha;
    private String _food;
    private String _moment;
    private String _username;
    public ArrayList<Diet> jsonArray;
    private Map<String, String> _response;


    public void setResponse(Map<String, String> response) {
        _response = response;
    }

    public Map<String, String> getResponse() {
        return _response;
    }

    public Diet() {}

    public Diet(String moment, String dateTime, String username){

        this._moment = moment;
        this._date = dateTime;
        this._username = username;

    }

    public Diet(int id, int calorie, LocalDate dateTime, String food) {
        this._id = id;
        this._calorie = calorie;
        this._dateTime = dateTime;
        this._food = food;
    }
    public Diet(String date, String username){

        this._date = date;
        this._username = username;

    }

    public Diet(int calorie, String food, String moment, String username) {
        this._calorie = calorie;
        this._food = food;
        this._moment = moment;
        this._username = username;
    }

    public Diet(String username){
        this._username = username;
    }

  /*  public Diet(String id, LocalDate calorie, String dateTime) {
        this._id = id;
        this._calorie = calorie;
        this._dateTime = dateTime;
    }*/

    public Diet(int calorie, LocalDate dateTime) {
        this._calorie = calorie;
        this._dateTime = dateTime;
    }

    public Diet(LocalDate dateTime) {
        this._dateTime = dateTime;
    }

    public Diet(int calorie) {
        this._calorie = calorie;
    }

    public Date get_date(){return _fecha;};

    public void set_date(Date date){this._fecha = date;}

    public int get_id() {
        return _id;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public int get_calorie() {
        return _calorie;
    }

    public void set_calorie(int calorie) {
        this._calorie = calorie;
    }

    public LocalDate get_dateTime() {
        return _dateTime;
    }

    public void set_dateTime(LocalDate dateTime) {
        this._dateTime = dateTime;
    }

    public String get_food() {
        return _food;
    }

    public void set_food(String food) {
        this._food = food;
    }

    public String get_moment() {
        return _moment;
    }

    public void set_moment(String moment) {
        this._moment = moment;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String username) {
        this._username = username;
    }
}
