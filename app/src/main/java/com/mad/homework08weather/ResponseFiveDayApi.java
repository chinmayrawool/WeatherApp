package com.mad.homework08weather;

import java.util.List;

/**
 * Created by neha5 on 05-04-2017.
 */

public class ResponseFiveDayApi {


    /**
     * Headline : {"EffectiveDate":"2017-04-05T14:00:00-04:00","EffectiveEpochDate":1491415200,"Severity":1,"Text":"Thunderstorms, some severe, this afternoon through late tonight; storms can bring downpours, large hail, damaging winds and a tornado","Category":"thunderstorm","EndDate":"2017-04-06T08:00:00-04:00","EndEpochDate":1491480000,"MobileLink":"http://m.accuweather.com/en/us/charlotte-nc/28202/extended-weather-forecast/349818?lang=en-us","Link":"http://www.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?lang=en-us"}
     * DailyForecasts : [{"Date":"2017-04-05T07:00:00-04:00","EpochDate":1491390000,"Temperature":{"Minimum":{"Value":56,"Unit":"F","UnitType":18},"Maximum":{"Value":68,"Unit":"F","UnitType":18}},"Day":{"Icon":13,"IconPhrase":"Mostly cloudy w/ showers"},"Night":{"Icon":15,"IconPhrase":"Thunderstorms"},"Sources":["AccuWeather"],"MobileLink":"http://m.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=1&lang=en-us","Link":"http://www.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=1&lang=en-us"},{"Date":"2017-04-06T07:00:00-04:00","EpochDate":1491476400,"Temperature":{"Minimum":{"Value":41,"Unit":"F","UnitType":18},"Maximum":{"Value":62,"Unit":"F","UnitType":18}},"Day":{"Icon":4,"IconPhrase":"Intermittent clouds"},"Night":{"Icon":35,"IconPhrase":"Partly cloudy"},"Sources":["AccuWeather"],"MobileLink":"http://m.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=2&lang=en-us","Link":"http://www.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=2&lang=en-us"},{"Date":"2017-04-07T07:00:00-04:00","EpochDate":1491562800,"Temperature":{"Minimum":{"Value":36,"Unit":"F","UnitType":18},"Maximum":{"Value":59,"Unit":"F","UnitType":18}},"Day":{"Icon":4,"IconPhrase":"Intermittent clouds"},"Night":{"Icon":34,"IconPhrase":"Mostly clear"},"Sources":["AccuWeather"],"MobileLink":"http://m.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=3&lang=en-us","Link":"http://www.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=3&lang=en-us"},{"Date":"2017-04-08T07:00:00-04:00","EpochDate":1491649200,"Temperature":{"Minimum":{"Value":38,"Unit":"F","UnitType":18},"Maximum":{"Value":66,"Unit":"F","UnitType":18}},"Day":{"Icon":1,"IconPhrase":"Sunny"},"Night":{"Icon":34,"IconPhrase":"Mostly clear"},"Sources":["AccuWeather"],"MobileLink":"http://m.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=4&lang=en-us","Link":"http://www.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=4&lang=en-us"},{"Date":"2017-04-09T07:00:00-04:00","EpochDate":1491735600,"Temperature":{"Minimum":{"Value":47,"Unit":"F","UnitType":18},"Maximum":{"Value":74,"Unit":"F","UnitType":18}},"Day":{"Icon":2,"IconPhrase":"Mostly sunny"},"Night":{"Icon":33,"IconPhrase":"Clear"},"Sources":["AccuWeather"],"MobileLink":"http://m.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=5&lang=en-us","Link":"http://www.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=5&lang=en-us"}]
     */

    private HeadlineBean Headline;
    private List<DailyForecastsBean> DailyForecasts;

    public HeadlineBean getHeadline() {
        return Headline;
    }

    public void setHeadline(HeadlineBean Headline) {
        this.Headline = Headline;
    }

    public List<DailyForecastsBean> getDailyForecasts() {
        return DailyForecasts;
    }

    public void setDailyForecasts(List<DailyForecastsBean> DailyForecasts) {
        this.DailyForecasts = DailyForecasts;
    }

    public static class HeadlineBean {
        /**
         * EffectiveDate : 2017-04-05T14:00:00-04:00
         * EffectiveEpochDate : 1491415200
         * Severity : 1
         * Text : Thunderstorms, some severe, this afternoon through late tonight; storms can bring downpours, large hail, damaging winds and a tornado
         * Category : thunderstorm
         * EndDate : 2017-04-06T08:00:00-04:00
         * EndEpochDate : 1491480000
         * MobileLink : http://m.accuweather.com/en/us/charlotte-nc/28202/extended-weather-forecast/349818?lang=en-us
         * Link : http://www.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?lang=en-us
         */

        private String EffectiveDate;
        private int EffectiveEpochDate;
        private int Severity;
        private String Text;
        private String Category;
        private String EndDate;
        private int EndEpochDate;
        private String MobileLink;
        private String Link;

        public String getEffectiveDate() {
            return EffectiveDate;
        }

        public void setEffectiveDate(String EffectiveDate) {
            this.EffectiveDate = EffectiveDate;
        }

        public int getEffectiveEpochDate() {
            return EffectiveEpochDate;
        }

        public void setEffectiveEpochDate(int EffectiveEpochDate) {
            this.EffectiveEpochDate = EffectiveEpochDate;
        }

        public int getSeverity() {
            return Severity;
        }

        public void setSeverity(int Severity) {
            this.Severity = Severity;
        }

        public String getText() {
            return Text;
        }

        public void setText(String Text) {
            this.Text = Text;
        }

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public int getEndEpochDate() {
            return EndEpochDate;
        }

        public void setEndEpochDate(int EndEpochDate) {
            this.EndEpochDate = EndEpochDate;
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

        @Override
        public String toString() {
            return "HeadlineBean{" +
                    "EffectiveDate='" + EffectiveDate + '\'' +
                    ", EffectiveEpochDate=" + EffectiveEpochDate +
                    ", Severity=" + Severity +
                    ", Text='" + Text + '\'' +
                    ", Category='" + Category + '\'' +
                    ", EndDate='" + EndDate + '\'' +
                    ", EndEpochDate=" + EndEpochDate +
                    ", MobileLink='" + MobileLink + '\'' +
                    ", Link='" + Link + '\'' +
                    '}';
        }
    }

    public static class DailyForecastsBean {
        /**
         * Date : 2017-04-05T07:00:00-04:00
         * EpochDate : 1491390000
         * Temperature : {"Minimum":{"Value":56,"Unit":"F","UnitType":18},"Maximum":{"Value":68,"Unit":"F","UnitType":18}}
         * Day : {"Icon":13,"IconPhrase":"Mostly cloudy w/ showers"}
         * Night : {"Icon":15,"IconPhrase":"Thunderstorms"}
         * Sources : ["AccuWeather"]
         * MobileLink : http://m.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=1&lang=en-us
         * Link : http://www.accuweather.com/en/us/charlotte-nc/28202/daily-weather-forecast/349818?day=1&lang=en-us
         */

        private String Date;
        private int EpochDate;
        private TemperatureBean Temperature;
        private DayBean Day;
        private NightBean Night;
        private String MobileLink;
        private String Link;
        private List<String> Sources;

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public int getEpochDate() {
            return EpochDate;
        }

        public void setEpochDate(int EpochDate) {
            this.EpochDate = EpochDate;
        }

        public TemperatureBean getTemperature() {
            return Temperature;
        }

        public void setTemperature(TemperatureBean Temperature) {
            this.Temperature = Temperature;
        }

        public DayBean getDay() {
            return Day;
        }

        public void setDay(DayBean Day) {
            this.Day = Day;
        }

        public NightBean getNight() {
            return Night;
        }

        public void setNight(NightBean Night) {
            this.Night = Night;
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

        public List<String> getSources() {
            return Sources;
        }

        public void setSources(List<String> Sources) {
            this.Sources = Sources;
        }

        public static class TemperatureBean {
            /**
             * Minimum : {"Value":56,"Unit":"F","UnitType":18}
             * Maximum : {"Value":68,"Unit":"F","UnitType":18}
             */

            private MinimumBean Minimum;
            private MaximumBean Maximum;

            public MinimumBean getMinimum() {
                return Minimum;
            }

            public void setMinimum(MinimumBean Minimum) {
                this.Minimum = Minimum;
            }

            public MaximumBean getMaximum() {
                return Maximum;
            }

            public void setMaximum(MaximumBean Maximum) {
                this.Maximum = Maximum;
            }

            public static class MinimumBean {
                /**
                 * Value : 56.0
                 * Unit : F
                 * UnitType : 18
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

            public static class MaximumBean {
                /**
                 * Value : 68.0
                 * Unit : F
                 * UnitType : 18
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
        }

        public static class DayBean {
            /**
             * Icon : 13
             * IconPhrase : Mostly cloudy w/ showers
             */

            private int Icon;
            private String IconPhrase;

            public int getIcon() {
                return Icon;
            }

            public void setIcon(int Icon) {
                this.Icon = Icon;
            }

            public String getIconPhrase() {
                return IconPhrase;
            }

            public void setIconPhrase(String IconPhrase) {
                this.IconPhrase = IconPhrase;
            }
        }

        public static class NightBean {
            /**
             * Icon : 15
             * IconPhrase : Thunderstorms
             */

            private int Icon;
            private String IconPhrase;

            public int getIcon() {
                return Icon;
            }

            public void setIcon(int Icon) {
                this.Icon = Icon;
            }

            public String getIconPhrase() {
                return IconPhrase;
            }

            public void setIconPhrase(String IconPhrase) {
                this.IconPhrase = IconPhrase;
            }
        }

        @Override
        public String toString() {
            return "DailyForecastsBean{" +
                    "Date='" + Date + '\'' +
                    ", EpochDate=" + EpochDate +
                    ", Temperature=" + Temperature +
                    ", Day=" + Day +
                    ", Night=" + Night +
                    ", MobileLink='" + MobileLink + '\'' +
                    ", Link='" + Link + '\'' +
                    ", Sources=" + Sources +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ResponseFiveDayApi{" +
                "Headline=" + Headline +
                ", DailyForecasts=" + DailyForecasts +
                '}';
    }
}
