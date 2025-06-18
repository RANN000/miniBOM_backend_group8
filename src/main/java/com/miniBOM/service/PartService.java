package com.miniBOM.service;


import com.miniBOM.pojo.Part;

import java.util.List;


public interface PartService {
    void add(Part part);

    List<Part> list(String searchKey);

    void update(Part part);

    void delete(Part part);
}
