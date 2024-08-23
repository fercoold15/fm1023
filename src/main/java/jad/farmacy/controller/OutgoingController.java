package jad.farmacy.controller;

import jad.farmacy.Service.OutgoingService;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewOutgoing;
import jad.farmacy.dto.UpdateOutgoing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/outgoings")
public class OutgoingController {
    private final OutgoingService outgoingService;

    public OutgoingController(OutgoingService outgoingService) {
        this.outgoingService = outgoingService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse> getAllOutgoings() {
        return outgoingService.allOutgoings();
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> addOutgoing(@RequestBody NewOutgoing newOutgoing) {
        return outgoingService.addOutgoing(newOutgoing);
    }

    @PutMapping("")
    public ResponseEntity<GlobalResponse> updateOutgoing(@RequestBody UpdateOutgoing updateOutgoing) {
        return outgoingService.updateOutgoing(updateOutgoing);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> getOutgoingById(@PathVariable Long id) {
        return outgoingService.getOutgoingById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> deleteOutgoingById(@PathVariable Long id) {
        return outgoingService.deleteOutgoingById(id);
    }
}

