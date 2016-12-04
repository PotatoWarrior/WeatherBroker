package com.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Entity
@Table(name = "Forecast", schema = "public", catalog = "WeatherBroker")
@IdClass(ForecastEntityPK.class)
@XmlRootElement(name = "Forecast")
@XmlAccessorType(XmlAccessType.NONE)
public class ForecastEntity {
    private String city;
    private String country;
    private String region;
    private Date date;
    private Short high;
    private Short low;

    @Id
    @Column(name = "city", nullable = false, length = 150)
    @XmlElement
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Id
    @Column(name = "country", nullable = false, length = 150)
    @XmlElement
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Id
    @Column(name = "region", nullable = false, length = 150)
    @XmlElement
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Id
    @Column(name = "date", nullable = false)
    @XmlTransient
    public Date getDate() {
        return date;
    }

    @Transient
    @XmlElement(name = "date")
    public String getDateAsString(){
        return new SimpleDateFormat("dd MMM yyyy", Locale.US).format(date);
    }

    public void setDateAsString(String date){
        try {
            this.date = new Date(new SimpleDateFormat("dd MMM yyyy", Locale.US).parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "high", nullable = true)
    @XmlElement
    public Short getHigh() {
        return high;
    }

    public void setHigh(Short high) {
        this.high = high;
    }

    @Basic
    @Column(name = "low", nullable = true)
    @XmlElement
    public Short getLow() {
        return low;
    }

    public void setLow(Short low) {
        this.low = low;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForecastEntity entity = (ForecastEntity) o;

        if (city != null ? !city.equals(entity.city) : entity.city != null) return false;
        if (country != null ? !country.equals(entity.country) : entity.country != null) return false;
        if (region != null ? !region.equals(entity.region) : entity.region != null) return false;
        if (date != null ? !date.equals(entity.date) : entity.date != null) return false;
        if (high != null ? !high.equals(entity.high) : entity.high != null) return false;
        if (low != null ? !low.equals(entity.low) : entity.low != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (high != null ? high.hashCode() : 0);
        result = 31 * result + (low != null ? low.hashCode() : 0);
        return result;
    }
}
