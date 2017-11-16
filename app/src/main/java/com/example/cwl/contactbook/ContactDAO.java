package com.example.cwl.contactbook;

/**
 * Created by CWL on 2017/11/16.
 */

public interface ContactDAO {
    void add(Contact c);
    void delete(Contact c);
    void edit(Contact c);
    Contact[] getList();
    Contact getOne(int id);
}
