package jad.farmacy.controller;


import jad.farmacy.Service.StoreService;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewStore;
import jad.farmacy.dto.UpdateStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse> getAllStores() {
        return storeService.allStores();
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> addStore(@RequestBody NewStore newStore) {
        return storeService.addStore(newStore);

    }

    @PutMapping
    public ResponseEntity<GlobalResponse> updateStore(@RequestBody UpdateStore updateStore) {
        return storeService.updateStore(updateStore);

    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> getStoreById(@PathVariable Long id) {
        return storeService.getStoreById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStoreById(@PathVariable Long id) {
        storeService.deleteStoreById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
