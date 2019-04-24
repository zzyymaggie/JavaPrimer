package xyz.zzyymaggie.lambda;

public class Artist {
    String name;
    String country;
    Integer age;

    public Artist(String name, String country, Integer age) {
        this.name = name;
        this.country = country;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isFrom(String country) {
        if(country == null) {
            return false;
        }
        return country.equalsIgnoreCase(this.country);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
