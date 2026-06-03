package com.apartix.demo.controller;

import com.apartix.demo.dto.CreateFaultRequest;
import com.apartix.demo.entity.Fault;
import com.apartix.demo.repository.FaultRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faults")
@CrossOrigin(origins = "*")
public class FaultController {

    private final FaultRepository faultRepository;

    public FaultController(FaultRepository faultRepository) {
        this.faultRepository = faultRepository;
    }

    @GetMapping
    public List<Fault> getFaults(@RequestParam Integer siteId) {
        return faultRepository.findBySiteIdOrderByCreatedAtDesc(siteId);
    }
    @PostMapping
    public Fault createFault(
        @RequestBody CreateFaultRequest request) {

    Fault fault = new Fault();

    fault.setSiteId(request.getSiteId());
    fault.setUserId(request.getUserId());
    fault.setTitle(request.getTitle());
    fault.setDescription(request.getDescription());

    fault.setStatus("open");

    fault.setCreatedAt(
            new java.sql.Timestamp(System.currentTimeMillis()));

    return faultRepository.save(fault);
}

    @PutMapping("/{id}/status")
    public Fault updateStatus(
        @PathVariable Integer id,
        @RequestParam String status) {

    Fault fault = faultRepository.findById(id)
            .orElseThrow();

    fault.setStatus(status);

    return faultRepository.save(fault);
}

    @DeleteMapping("/{id}")
    public void deleteFault(@PathVariable Integer id) {
    faultRepository.deleteById(id);
}
}