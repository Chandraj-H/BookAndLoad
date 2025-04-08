package com.example.loadBooking.service;

import com.example.loadBooking.model.Load;
import com.example.loadBooking.repository.LoadRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoadService {
    @Autowired  //this helps spring connect dependency
    private LoadRepository loadRepository;

    public Load createLoad(Load load) {
        return loadRepository.save(load);
    }

    public List<Load> getAllLoads() {
        return loadRepository.findAll();
    }

    public Optional<Load> getLoadById(UUID id) {    //Optional class handles null object
        return loadRepository.findById(id);
    }

    public List<Load> getLoadsByShipperId(String shipperId) {
        return loadRepository.findByShipperId(shipperId);
    }

    public List<Load> getLoadsByTruckType(String truckType) {
        return loadRepository.findByTruckType(truckType);
    }

    public Load updateLoad(UUID id, Load updatedLoad) {
        return loadRepository.findById(id).map(load -> {
            load.setShipperId(updatedLoad.getShipperId());
            load.setFacility(updatedLoad.getFacility());
            load.setProductType(updatedLoad.getProductType());
            load.setTruckType(updatedLoad.getTruckType());
            load.setNoOfTrucks(updatedLoad.getNoOfTrucks());
            load.setWeight(updatedLoad.getWeight());
            load.setComment(updatedLoad.getComment());
            return loadRepository.save(load);
        }).orElseThrow(() -> new RuntimeException("Load not found with ID: " + id));
    }

    public void deleteLoad(UUID id) {
        loadRepository.deleteById(id);
    }

    @Transactional
    public void updateLoadStatus(UUID loadId, Load.Status status) {
        loadRepository.findById(loadId).ifPresent(load -> {
            load.setStatus(status);
            loadRepository.save(load);
        });
    }
}
