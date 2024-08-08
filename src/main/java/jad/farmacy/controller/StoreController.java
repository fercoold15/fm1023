package jad.farmacy.Controller;

import jad.farmacy.Entity.Store;
import jad.farmacy.Service.StoreService;
import jad.farmacy.dto.NewStore;
import jad.farmacy.dto.UpdateStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> stores = storeService.allStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Store> addStore(@RequestBody NewStore newStore) {
        Store store = storeService.addStore(newStore);
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Store> updateStore(@RequestBody UpdateStore updateStore) {
        Store store = storeService.updateStore(updateStore);
        if (store != null) {
            return new ResponseEntity<>(store, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        Store store = storeService.getStoreById(id);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStoreById(@PathVariable Long id) {
        storeService.deleteStoreById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
