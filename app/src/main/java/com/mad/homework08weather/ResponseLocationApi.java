package com.mad.homework08weather;

import java.util.List;

/**
 * Created by neha5 on 06-04-2017.
 */

public class ResponseLocationApi {


    /**
     * Version : 1
     * Key : 349818
     * Type : City
     * Rank : 35
     * LocalizedName : Charlotte
     * EnglishName : Charlotte
     * PrimaryPostalCode : 28202
     * Region : {"ID":"NAM","LocalizedName":"North America","EnglishName":"North America"}
     * Country : {"ID":"US","LocalizedName":"United States","EnglishName":"United States"}
     * AdministrativeArea : {"ID":"NC","LocalizedName":"North Carolina","EnglishName":"North Carolina","Level":1,"LocalizedType":"State","EnglishType":"State","CountryID":"US"}
     * TimeZone : {"Code":"EDT","Name":"America/New_York","GmtOffset":-4,"IsDaylightSaving":true,"NextOffsetChange":"2017-11-05T06:00:00Z"}
     * GeoPosition : {"Latitude":35.227,"Longitude":-80.843,"Elevation":{"Metric":{"Value":218,"Unit":"m","UnitType":5},"Imperial":{"Value":715,"Unit":"ft","UnitType":0}}}
     * IsAlias : false
     * SupplementalAdminAreas : [{"Level":2,"LocalizedName":"Mecklenburg","EnglishName":"Mecklenburg"}]
     * DataSets : ["Alerts","ForecastConfidence","MinuteCast"]
     */

    private int Version;
    private String Key;
    private String Type;
    private int Rank;
    private String LocalizedName;
    private String EnglishName;
    private String PrimaryPostalCode;
    private RegionBean Region;
    private CountryBean Country;
    private AdministrativeAreaBean AdministrativeArea;
    private TimeZoneBean TimeZone;
    private GeoPositionBean GeoPosition;
    private boolean IsAlias;
    private List<SupplementalAdminAreasBean> SupplementalAdminAreas;
    private List<String> DataSets;

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int Rank) {
        this.Rank = Rank;
    }

    public String getLocalizedName() {
        return LocalizedName;
    }

    public void setLocalizedName(String LocalizedName) {
        this.LocalizedName = LocalizedName;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String EnglishName) {
        this.EnglishName = EnglishName;
    }

    public String getPrimaryPostalCode() {
        return PrimaryPostalCode;
    }

    public void setPrimaryPostalCode(String PrimaryPostalCode) {
        this.PrimaryPostalCode = PrimaryPostalCode;
    }

    public RegionBean getRegion() {
        return Region;
    }

    public void setRegion(RegionBean Region) {
        this.Region = Region;
    }

    public CountryBean getCountry() {
        return Country;
    }

    public void setCountry(CountryBean Country) {
        this.Country = Country;
    }

    public AdministrativeAreaBean getAdministrativeArea() {
        return AdministrativeArea;
    }

    public void setAdministrativeArea(AdministrativeAreaBean AdministrativeArea) {
        this.AdministrativeArea = AdministrativeArea;
    }

    public TimeZoneBean getTimeZone() {
        return TimeZone;
    }

    public void setTimeZone(TimeZoneBean TimeZone) {
        this.TimeZone = TimeZone;
    }

    public GeoPositionBean getGeoPosition() {
        return GeoPosition;
    }

    public void setGeoPosition(GeoPositionBean GeoPosition) {
        this.GeoPosition = GeoPosition;
    }

    public boolean isIsAlias() {
        return IsAlias;
    }

    public void setIsAlias(boolean IsAlias) {
        this.IsAlias = IsAlias;
    }

    public List<SupplementalAdminAreasBean> getSupplementalAdminAreas() {
        return SupplementalAdminAreas;
    }

    public void setSupplementalAdminAreas(List<SupplementalAdminAreasBean> SupplementalAdminAreas) {
        this.SupplementalAdminAreas = SupplementalAdminAreas;
    }

    public List<String> getDataSets() {
        return DataSets;
    }

    public void setDataSets(List<String> DataSets) {
        this.DataSets = DataSets;
    }

    public static class RegionBean {
        /**
         * ID : NAM
         * LocalizedName : North America
         * EnglishName : North America
         */

        private String ID;
        private String LocalizedName;
        private String EnglishName;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getLocalizedName() {
            return LocalizedName;
        }

        public void setLocalizedName(String LocalizedName) {
            this.LocalizedName = LocalizedName;
        }

        public String getEnglishName() {
            return EnglishName;
        }

        public void setEnglishName(String EnglishName) {
            this.EnglishName = EnglishName;
        }
    }

    public static class CountryBean {
        /**
         * ID : US
         * LocalizedName : United States
         * EnglishName : United States
         */

        private String ID;
        private String LocalizedName;
        private String EnglishName;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getLocalizedName() {
            return LocalizedName;
        }

        public void setLocalizedName(String LocalizedName) {
            this.LocalizedName = LocalizedName;
        }

        public String getEnglishName() {
            return EnglishName;
        }

        public void setEnglishName(String EnglishName) {
            this.EnglishName = EnglishName;
        }
    }

    public static class AdministrativeAreaBean {
        /**
         * ID : NC
         * LocalizedName : North Carolina
         * EnglishName : North Carolina
         * Level : 1
         * LocalizedType : State
         * EnglishType : State
         * CountryID : US
         */

        private String ID;
        private String LocalizedName;
        private String EnglishName;
        private int Level;
        private String LocalizedType;
        private String EnglishType;
        private String CountryID;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getLocalizedName() {
            return LocalizedName;
        }

        public void setLocalizedName(String LocalizedName) {
            this.LocalizedName = LocalizedName;
        }

        public String getEnglishName() {
            return EnglishName;
        }

        public void setEnglishName(String EnglishName) {
            this.EnglishName = EnglishName;
        }

        public int getLevel() {
            return Level;
        }

        public void setLevel(int Level) {
            this.Level = Level;
        }

        public String getLocalizedType() {
            return LocalizedType;
        }

        public void setLocalizedType(String LocalizedType) {
            this.LocalizedType = LocalizedType;
        }

        public String getEnglishType() {
            return EnglishType;
        }

        public void setEnglishType(String EnglishType) {
            this.EnglishType = EnglishType;
        }

        public String getCountryID() {
            return CountryID;
        }

        public void setCountryID(String CountryID) {
            this.CountryID = CountryID;
        }
    }

    public static class TimeZoneBean {
        /**
         * Code : EDT
         * Name : America/New_York
         * GmtOffset : -4
         * IsDaylightSaving : true
         * NextOffsetChange : 2017-11-05T06:00:00Z
         */

        private String Code;
        private String Name;
        private int GmtOffset;
        private boolean IsDaylightSaving;
        private String NextOffsetChange;

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getGmtOffset() {
            return GmtOffset;
        }

        public void setGmtOffset(int GmtOffset) {
            this.GmtOffset = GmtOffset;
        }

        public boolean isIsDaylightSaving() {
            return IsDaylightSaving;
        }

        public void setIsDaylightSaving(boolean IsDaylightSaving) {
            this.IsDaylightSaving = IsDaylightSaving;
        }

        public String getNextOffsetChange() {
            return NextOffsetChange;
        }

        public void setNextOffsetChange(String NextOffsetChange) {
            this.NextOffsetChange = NextOffsetChange;
        }
    }

    public static class GeoPositionBean {
        /**
         * Latitude : 35.227
         * Longitude : -80.843
         * Elevation : {"Metric":{"Value":218,"Unit":"m","UnitType":5},"Imperial":{"Value":715,"Unit":"ft","UnitType":0}}
         */

        private double Latitude;
        private double Longitude;
        private ElevationBean Elevation;

        public double getLatitude() {
            return Latitude;
        }

        public void setLatitude(double Latitude) {
            this.Latitude = Latitude;
        }

        public double getLongitude() {
            return Longitude;
        }

        public void setLongitude(double Longitude) {
            this.Longitude = Longitude;
        }

        public ElevationBean getElevation() {
            return Elevation;
        }

        public void setElevation(ElevationBean Elevation) {
            this.Elevation = Elevation;
        }

        public static class ElevationBean {
            /**
             * Metric : {"Value":218,"Unit":"m","UnitType":5}
             * Imperial : {"Value":715,"Unit":"ft","UnitType":0}
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
                 * Value : 218
                 * Unit : m
                 * UnitType : 5
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

            public static class ImperialBean {
                /**
                 * Value : 715
                 * Unit : ft
                 * UnitType : 0
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
    }

    public static class SupplementalAdminAreasBean {
        /**
         * Level : 2
         * LocalizedName : Mecklenburg
         * EnglishName : Mecklenburg
         */

        private int Level;
        private String LocalizedName;
        private String EnglishName;

        public int getLevel() {
            return Level;
        }

        public void setLevel(int Level) {
            this.Level = Level;
        }

        public String getLocalizedName() {
            return LocalizedName;
        }

        public void setLocalizedName(String LocalizedName) {
            this.LocalizedName = LocalizedName;
        }

        public String getEnglishName() {
            return EnglishName;
        }

        public void setEnglishName(String EnglishName) {
            this.EnglishName = EnglishName;
        }
    }
}
