package jad.farmacy.Service;


import jad.farmacy.Entity.Store;
import jad.farmacy.Exceptions.StoreNotFoundException;
import jad.farmacy.Repository.StoreRepository;
import jad.farmacy.dto.NewStore;
import jad.farmacy.dto.UpdateStore;
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

    public List<Store> allStores() {
        List<Store> stores = new ArrayList<>();
        storeRepository.findAll().forEach(stores::add);
        return stores;
    }

    public Store addStore(NewStore newStore) {

        Store store = new Store();
        store.setStoreName(newStore.getStoreName());
        store.setStoreDirection(newStore.getStoreDirection());

        return storeRepository.save(store);
    }

    public Store updateStore(UpdateStore updateStore) {
        if(updateStore.getStoreId() == 0){
            return null;
        }

        Store store = new Store();
        store.setStoreName(updateStore.getStoreName());
        store.setStoreDirection(updateStore.getStoreDirection());
        return storeRepository.save(store);
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + id));
    }

    public void deleteStoreById(Long id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
        } else {
            throw new StoreNotFoundException("Store not found with id: " + id);
        }
    }
}
