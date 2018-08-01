/*
 * @(#)User.java
 *
create table sophia(
id number(11),
name varchar(20),
age number(3),
gender number(1),
email varchar(60));
 */
package xyz.zzyymaggie.mybatis.model;

public class User {  
    private Integer id;//自动增长的ID  
    private String name;//用户名  
    private Integer age;//年龄  
    private Integer gender;//性别，0-男 ； 1-女  
    private String email;
  
    public Integer getId() {  
        return id;  
    }  
  
    public void setId(Integer id) {  
        this.id = id;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
    
    public Integer getAge() {  
        return age;  
    }  
  
    public void setAge(Integer age) {  
        this.age = age;  
    }  
  
    public Integer getGender() {  
        return gender;  
    }  
  
    public void setGender(Integer gender) {  
        this.gender = gender;  
    }  
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override  
    public String toString() {  
        return "User [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender  
                + ",email:" + email + "]";  
    }  
  
}  
