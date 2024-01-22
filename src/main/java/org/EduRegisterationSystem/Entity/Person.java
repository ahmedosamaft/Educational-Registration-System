package org.EduRegisterationSystem.Entity;

import java.util.Objects;

public class Person {
    private String userName, password, name, id, email;
    public Person(){}
    public Person(String user_name, String password, String name, String id, String email) {
        this.userName = user_name;
        this.password = password;
        this.name = name;
        this.id = id;
        this.name = name;
    }
    public String getUserName() {
        return userName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o; // down casting
        return id.equals(person.id);
    }

    public boolean equals(String username, String password) {
        return this.userName.equals(username) && this.password.equals(password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person {" +
                "user_name = '" + userName + '\'' +
                ", password = '" + password + '\'' +
                ", name = '" + name + '\'' +
                ", email = '" + email + '\'' +
                ", id = '" + id + '\'' +
                "}";
    }

}
