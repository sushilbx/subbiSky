package com.webdigital.subbisky.model;

public class Searchlistnewmodel {


    public String type;
    public String name;
    public String id;
    public String extraid;



    public Searchlistnewmodel(String type, String name, String id, String extraid)
    {

        // Initialize the input variable from main
        // function to the global variable of the class

        // this keyword refers to current instance
        this.type = type;
        this.name = name;
        this.id = id;
        this.extraid = extraid;

    }
    public String getExtraid() {
        return extraid;
    }

    public void setExtraid(String extraid) {
        this.extraid = extraid;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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


}
