package jad.farmacy.controller;

import jad.farmacy.Service.SellingService;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.TotalDTO;
import jad.farmacy.dto.NewSelling;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sellings")
public class SellingController {
    private final SellingService sellingService;

    public SellingController(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> addSelling(@RequestBody NewSelling newSelling) {
        return sellingService.addSelling(newSelling);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> getSellingById(@PathVariable Long id) {
        return sellingService.getSellingById(id);
    }

    @GetMapping("/sellingsPerDay/{day}")
    public ResponseEntity<GlobalResponse> getSellings(@PathVariable String day) {
        return sellingService.getSellings(day);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> deleteSellingById(@PathVariable Long id) {
        return sellingService.cancelSelling(id);
    }

    @PostMapping("/totalSellings")
    public ResponseEntity<GlobalResponse> getTotalSellings(@RequestBody TotalDTO totalDTO) {
        return sellingService.getSellingPerDay(totalDTO);
    }
}

