package com.miniBOM.service.impl;
import com.miniBOM.dao.PartDao;
import com.miniBOM.pojo.Part;
import com.miniBOM.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartDao partDao;

    @Override
    public void add(Part part) {
        partDao.add(part);
    }

    @Override
    public  Part find(Part part) {
        return partDao.find(part);
    }

    @Override
    public List<Part> listAllVersion(Part part) {
        return partDao.listAllVersion(part);
    }

    @Override
    public List<Part> list(String searchKey){return partDao.list(searchKey);}

    @Override
    public void update(Part part) {
        partDao.update(part);
    }

    @Override
    public void checkOut(Part part) {
        partDao.checkOut(part);
    }

    @Override
    public void checkIn(Part part) {
        partDao.checkIn(part);
    }

    @Override
    public void delete(Part part) {
        partDao.delete(part);
    }
}
