package com.miniBOM.service;


import com.miniBOM.pojo.Part;

import java.util.List;


public interface PartService {
    void add(Part part);

    Part find(Part part);

    List<Part> listAllVersion(Part part);

    void update(Part part);

    void checkOut(Part part);

    void checkIn(Part part);

    void delete(Part part);

    List<Part> list(String searchKey);




}
