package com.mad.homework08weather;

/**
 * Created by Chinmay Rawool on 4/6/2017.
 */

public class ResponseCurrentForecastApi {

    /**
     * LocalObservationDateTime : 2017-04-06T16:01:00-04:00
     * EpochTime : 1491508860
     * WeatherText : Cloudy
     * WeatherIcon : 7
     * IsDayTime : true
     * Temperature : {"Metric":{"Value":16.1,"Unit":"C","UnitType":17},"Imperial":{"Value":61,"Unit":"F","UnitType":18}}
     * MobileLink : http://m.accuweather.com/en/us/charlotte-nc/28202/current-weather/349818?lang=en-us
     * Link : http://www.accuweather.com/en/us/charlotte-nc/28202/current-weather/349818?lang=en-us
     */

    private String LocalObservationDateTime;
    private int EpochTime;
    private String WeatherText;
    private int WeatherIcon;
    private boolean IsDayTime;
    private TemperatureBean Temperature;
    private String MobileLink;
    private String Link;

    public String getLocalObservationDateTime() {
        return LocalObservationDateTime;
    }

    public void setLocalObservationDateTime(String LocalObservationDateTime) {
        this.LocalObservationDateTime = LocalObservationDateTime;
    }

    public int getEpochTime() {
        return EpochTime;
    }

    public void setEpochTime(int EpochTime) {
        this.EpochTime = EpochTime;
    }

    public String getWeatherText() {
        return WeatherText;
    }

    public void setWeatherText(String WeatherText) {
        this.WeatherText = WeatherText;
    }

    public int getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(int WeatherIcon) {
        this.WeatherIcon = WeatherIcon;
    }

    public boolean isIsDayTime() {
        return IsDayTime;
    }

    public void setIsDayTime(boolean IsDayTime) {
        this.IsDayTime = IsDayTime;
    }

    public TemperatureBean getTemperature() {
        return Temperature;
    }

    public void setTemperature(TemperatureBean Temperature) {
        this.Temperature = Temperature;
    }

    public String getMobileLink() {
        return MobileLink;
    }

    public void setMobileLink(String MobileLink) {
        this.MobileLink = MobileLink;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }

    public static class TemperatureBean {
        /**
         * Metric : {"Value":16.1,"Unit":"C","UnitType":17}
         * Imperial : {"Value":61,"Unit":"F","UnitType":18}
         */

        private MetricBean Metric;
        private ImperialBean Imperial;

        public MetricBean getMetric() {
            return Metric;
        }

        public void setMetric(MetricBean Metric) {
            this.Metric = Metric;
        }

        public ImperialBean getImperial() {
            return Imperial;
        }

        public void setImperial(ImperialBean Imperial) {
            this.Imperial = Imperial;
        }

        public static class MetricBean {
            /**
             * Value : 16.1
             * Unit : C
             * UnitType : 17
             */

            private double Value;
            private String Unit;
            private int UnitType;

            public double getValue() {
                return Value;
            }

            public void setValue(double Value) {
                this.Value = Value;
            }

            public String getUnit() {
                return Unit;
            }

            public void setUnit(String Unit) {
                this.Unit = Unit;
            }

            public int getUnitType() {
                return UnitType;
            }

            public void setUnitType(int UnitType) {
                this.UnitType = UnitType;
            }
        }

        public static class ImperialBean {
            /**
             * Value : 61
             * Unit : F
             * UnitType : 18
             */

            private int Value;
            private String Unit;
            private int UnitType;

            public int getValue() {
                return Value;
            }

            public void setValue(int Value) {
                this.Value = Value;
            }

            public String getUnit() {
                return Unit;
            }

            public void setUnit(String Unit) {
                this.Unit = Unit;
            }

            public int getUnitType() {
                return UnitType;
            }

            public void setUnitType(int UnitType) {
                this.UnitType = UnitType;
            }
        }
    }

    @Override
    public String toString() {
        return "ResponseCurrentForecastApi{" +
                "LocalObservationDateTime='" + LocalObservationDateTime + '\'' +
                ", EpochTime=" + EpochTime +
                ", WeatherText='" + WeatherText + '\'' +
                ", WeatherIcon=" + WeatherIcon +
                ", IsDayTime=" + IsDayTime +
                ", Temperature=" + Temperature +
                ", MobileLink='" + MobileLink + '\'' +
                ", Link='" + Link + '\'' +
                '}';
    }
}
