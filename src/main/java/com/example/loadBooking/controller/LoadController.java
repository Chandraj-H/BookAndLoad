package com.example.loadBooking.controller;

import com.example.loadBooking.model.Load;
import com.example.loadBooking.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/load")
public class LoadController {
    @Autowired
    private LoadService loadService;

    @PostMapping
    public Load createLoad(@RequestBody Load load) {
        return loadService.createLoad(load);
    }

    @GetMapping
    public List<Load> getLoads(@RequestParam(required = false) String shipperId,
                               @RequestParam(required = false) String truckType) {
        if (shipperId != null) {
            return loadService.getLoadsByShipperId(shipperId);
        } else if (truckType != null) {
            return loadService.getLoadsByTruckType(truckType);
        } else {
            return loadService.getAllLoads();
        }
    }

    @GetMapping("/{id}")
    public Optional<Load> getLoadById(@PathVariable UUID id) {
        return loadService.getLoadById(id);
    }

    @PutMapping("/{id}")
    public Load updateLoad(@PathVariable UUID id, @RequestBody Load load) {
        return loadService.updateLoad(id, load);
    }

    @DeleteMapping("/{id}")
    public void deleteLoad(@PathVariable UUID id) {
        loadService.deleteLoad(id);
    }
}
