package jad.farmacy.Service;


import jad.farmacy.Entity.Store;
import jad.farmacy.Exceptions.StoreNotFoundException;
import jad.farmacy.Repository.StoreRepository;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewStore;
import jad.farmacy.dto.UpdateStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository){
        this.storeRepository=storeRepository;
    }

    public ResponseEntity<GlobalResponse> allStores() {
        List<Store> stores = new ArrayList<>();
        storeRepository.findAll().forEach(stores::add);
        GlobalResponse apiResponse = new GlobalResponse(200,"Registros Encontrados", "Registros Encontrados", stores);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    public ResponseEntity<GlobalResponse> addStore(NewStore newStore) {

        Store store = new Store();
        store.setStoreName(newStore.getStoreName());
        store.setStoreDirection(newStore.getStoreDirection());
        store=storeRepository.save(store);
        GlobalResponse apiResponse = new GlobalResponse(200,"Registros Encontrados", "Registros Encontrados", store);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> updateStore(UpdateStore updateStore) {
        if(updateStore.getStoreId() == 0){
            return null;
        }

        Store store = new Store();
        store.setId(updateStore.getStoreId());
        store.setStoreName(updateStore.getStoreName());
        store.setStoreDirection(updateStore.getStoreDirection());
        store=storeRepository.save(store);
        GlobalResponse apiResponse = new GlobalResponse(200,"Registros Encontrados", "Registros Encontrados", store);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> getStoreById(Long id) {
        Optional<Store> store = storeRepository.findById(id);
        GlobalResponse apiResponse = new GlobalResponse(200,"Registros Encontrados", "Registros Encontrados", store.isPresent()?store.get():"");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public void deleteStoreById(Long id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
        } else {
            throw new StoreNotFoundException("Store not found with id: " + id);
        }
    }
}
