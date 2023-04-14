package com.webdigital.subbisky.model;
public  class Addroompojo {
    private String adult;
    private String sno;
    private String children;
    private String check;

    public Addroompojo(String sadults, String schildren,String ssno,String check) {
        this.adult = sadults;
        this.children = schildren;
        this.sno = ssno;
        this.check = check;
    }



    public String getadult() {
        return adult;
    }

    public void setadult(String adult) {
        this.adult = adult;
    }

    public String getcheck() {
        return check;
    }

    public void setcheck(String check) {
        this.check = check;
    }


    public String getsno() {
        return sno;
    }

    public void setsno(String sno) {
        this.sno = sno;
    }


    public String getchildren() {
        return children;
    }

    public void setchildren(String children) {
        this.children = children;
    }}