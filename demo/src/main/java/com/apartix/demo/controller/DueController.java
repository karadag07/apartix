package com.apartix.demo.controller;

import com.apartix.demo.entity.Due;
import com.apartix.demo.repository.DueRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dues")
@CrossOrigin(origins = "*")
public class DueController {

    private final DueRepository dueRepository;

    public DueController(DueRepository dueRepository) {
        this.dueRepository = dueRepository;
    }

    @GetMapping("/{userId}")
    public List<Due> getUserDues(
            @PathVariable Integer userId) {

        return dueRepository
                .findByUserIdOrderByYearDescMonthDesc(userId);
    }

    @PutMapping("/{id}/pay")
        public Due payDue(@PathVariable Integer id) {

    Due due = dueRepository.findById(id)
            .orElseThrow();

    due.setStatus("paid");
    due.setRemainingAmount(java.math.BigDecimal.ZERO);
    due.setLateFee(java.math.BigDecimal.ZERO);
    due.setPaidAt(new java.sql.Timestamp(System.currentTimeMillis()));

    return dueRepository.save(due);
}

    @GetMapping("/site/{siteId}")
    public List<Due> getSiteDues(
        @PathVariable Integer siteId) {

    return dueRepository
            .findBySiteIdOrderByYearDescMonthDesc(siteId);
}
}