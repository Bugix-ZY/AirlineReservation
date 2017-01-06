package io.github.bugix_zy.airlinereservation;


import java.util.ArrayList;

public class Person {

    /*-------------------fields-------------------*/
    private String name;
    private String id;
    private String phone;
    private ArrayList<PersonRole> roles = new ArrayList<PersonRole>(0);

    /*-----------------constructors---------------*/
    public Person(String name, String id, String phone) {
        this.name = name;
        this.id = id;
        this.phone = phone;
    }

    /*-------------------methods------------------*/
    public boolean addRole(PersonRole role){
        if(this.roles.size() >= 2) //due to 1 to 0..2
            return false;
        else
            return this.roles.add(role);
    }

    public boolean removeRole(PersonRole role){
        return this.roles.remove(role);
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", id=" + id + ", phone=" + phone + ", roles=" + roles + "]";
    }

    /*-------------------Getters and Setters-------------------*/
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<PersonRole> getRoles() {
        return roles;
    }

    public PersonRole getCustomerRole(){
        for(int i = 0; i < this.roles.size(); i++){
            if(roles.get(i) instanceof CustomerRole)
                return roles.get(i);
        }
        return null;
    }

    public PersonRole getEmpRole(){
        for(int i = 0; i < this.roles.size(); i++){
            if(roles.get(i) instanceof EmployeeRole)
                return roles.get(i);
        }
        return null;
    }
}
