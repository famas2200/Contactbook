package com.example.cwl.contactbook;

/**
 * Created by CWL on 2017/11/16.
 */

public class Contact {
    public int id;
    public String name;
    public String tel;
    public String addr;

    public Contact()
    {

    }

    public Contact(int id, String name, String tel, String addr)
    {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.addr = addr;
    }

    public Contact(String name, String tel, String addr)
    {
        this.name = name;
        this.tel = tel;
        this.addr = addr;
    }
}
