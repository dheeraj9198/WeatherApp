package models;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by dheeraj on 12/2/15.
 */
public class WeatherApiResponse {

    private ArrayList<ResponseItem> list;

    public ArrayList<ResponseItem> getList() {
        return list;
    }

    public void setList(ArrayList<ResponseItem> list) {
        this.list = list;
    }

    public String[] getStringsArray(){
        String[] strings = new String[list.size()];
        Iterator<ResponseItem> responseItemIterator = list.iterator();

        int index = 0;
        while(responseItemIterator.hasNext()){
            strings[index]=responseItemIterator.next().toString();
            index++;
        }
        return strings;
    }

    public static final class ResponseItem{
        private Long dt;
        private Temp temp;
        private float pressure;
        private float humidity;
        private ArrayList<Weather> weather;
        private float speed;
        private int deg;
        private int clouds;

        public Long getDt() {
            return dt;
        }

        public void setDt(Long dt) {
            this.dt = dt;
        }

        public Temp getTemp() {
            return temp;
        }

        public void setTemp(Temp temp) {
            this.temp = temp;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }

        public ArrayList<Weather> getWeather() {
            return weather;
        }

        public void setWeather(ArrayList<Weather> weather) {
            this.weather = weather;
        }

        public float getSpeed() {
            return speed;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public int getDeg() {
            return deg;
        }

        public void setDeg(int deg) {
            this.deg = deg;
        }

        public int getClouds() {
            return clouds;
        }

        public void setClouds(int clouds) {
            this.clouds = clouds;
        }

        @Override
        public String toString() {
            return "ResponseItem{" +
                    "dt=" + dt +
                    ", temp=" + temp +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", weather=" + weather +
                    ", speed=" + speed +
                    ", deg=" + deg +
                    ", clouds=" + clouds +
                    '}';
        }
    }

    public static final class Weather{
        private int id;
        private String main;
        private String description;
        private String icon;

        @Override
        public String toString() {
            return "Weather{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }

    public static final class Temp{
        private float min;
        private float max;
        private float night;
        private float eve;
        private float morn;

        public float getMax() {
            return max;
        }

        public void setMax(float max) {
            this.max = max;
        }

        public float getNight() {
            return night;
        }

        public void setNight(float night) {
            this.night = night;
        }

        public float getEve() {
            return eve;
        }

        public void setEve(float eve) {
            this.eve = eve;
        }

        public float getMorn() {
            return morn;
        }

        public void setMorn(float morn) {
            this.morn = morn;
        }

        public float getMin() {

            return min;
        }

        public void setMin(float min) {
            this.min = min;
        }

        @Override
        public String toString() {
            return "Temp{" +
                    "min=" + min +
                    ", max=" + max +
                    ", night=" + night +
                    ", eve=" + eve +
                    ", morn=" + morn +
                    '}';
        }
    }



}
