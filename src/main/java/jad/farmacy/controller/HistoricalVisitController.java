package jad.farmacy.controller;

import jad.farmacy.Service.HistoricalVisitService;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewHistoricalVisit;
import jad.farmacy.dto.UpdateHistoricalVisit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("historical-visits")
public class HistoricalVisitController {
    private final HistoricalVisitService historicalVisitService;

    public HistoricalVisitController(HistoricalVisitService historicalVisitService) {
        this.historicalVisitService = historicalVisitService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse> getAllHistoricalVisits() {
        return historicalVisitService.allHistoricalVisits();
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> addHistoricalVisit(@RequestBody NewHistoricalVisit newHistoricalVisit) {
        return historicalVisitService.addHistoricalVisit(newHistoricalVisit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalResponse> updateHistoricalVisit(@PathVariable Long id, @RequestBody UpdateHistoricalVisit updateHistoricalVisit) {
        updateHistoricalVisit.setHistoricalVisitId(id);
        return historicalVisitService.updateHistoricalVisit(updateHistoricalVisit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> getHistoricalVisitById(@PathVariable Long id) {
        return historicalVisitService.getHistoricalVisitById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> deleteHistoricalVisitById(@PathVariable Long id) {
        return historicalVisitService.deleteHistoricalVisitById(id);
    }
}
