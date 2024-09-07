package jad.farmacy.controller;

import jad.farmacy.Service.SellingService;
import jad.farmacy.configurations.GlobalResponse;
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



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSellingById(@PathVariable Long id) {
        sellingService.deleteSellingById(id);
        return ResponseEntity.noContent().build();
    }
}

