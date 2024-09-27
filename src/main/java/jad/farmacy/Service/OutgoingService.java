package jad.farmacy.Service;

import jad.farmacy.Entity.Outgoing;
import jad.farmacy.Entity.Store;
import jad.farmacy.Entity.User;
import jad.farmacy.Exceptions.OutgoingNotFoundException;
import jad.farmacy.Repository.OutgoingRepository;
import jad.farmacy.Repository.Proyections.ITotalOutgoings;
import jad.farmacy.Repository.Proyections.ITotalSellings;
import jad.farmacy.Repository.UserRepository;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewOutgoing;
import jad.farmacy.dto.TotalDTO;
import jad.farmacy.dto.UpdateOutgoing;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OutgoingService {
    private final OutgoingRepository outgoingRepository;
    private final UserRepository userRepository;

    public OutgoingService(OutgoingRepository outgoingRepository, UserRepository userRepository) {
        this.outgoingRepository = outgoingRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<GlobalResponse> allOutgoings(HttpServletRequest request) {
        List<Outgoing> outgoings = new ArrayList<>();
        int rol = (Integer) request.getAttribute("rol");
        long store=(Integer) request.getAttribute("store");
        if(rol==1){
            outgoings.addAll(outgoingRepository.findAllByStoreId(store));
        }else{
            Iterable<Outgoing> iterable = outgoingRepository.findAll();
            iterable.forEach(outgoings::add);
        }
        GlobalResponse apiResponse = new GlobalResponse(200, "Registros Encontrados", "Registros encontrados exitosamente", outgoings);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> addOutgoing(NewOutgoing newOutgoing) {
        User user = new User();
        user.setId(newOutgoing.getUserID());

        Store store = new Store();
        store.setId(newOutgoing.getStoreID());
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(newOutgoing.getBillDate(), inputFormatter);
        Outgoing outgoing = new Outgoing();
        outgoing.setDescription(newOutgoing.getDescription());
        outgoing.setAmount(newOutgoing.getAmount());
        outgoing.setValue(newOutgoing.getValue());
        outgoing.setBillDate(date);
        outgoing.setStore(store);
        outgoing.setTotal(newOutgoing.getTotal());
        outgoing.setUser(user);

        Outgoing savedOutgoing = outgoingRepository.save(outgoing);
        GlobalResponse apiResponse = new GlobalResponse(200, "Gasto Agregado", "Gasto agregado exitosamente", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> updateOutgoing(UpdateOutgoing updateOutgoing) {
        if (updateOutgoing.getOutgoingID() == 0) {
            GlobalResponse apiResponse = new GlobalResponse(400, "Error", "ID de gasto inválido", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setId(updateOutgoing.getUserID());

        Store store = new Store();
        store.setId(updateOutgoing.getStoreID());

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(updateOutgoing.getBillDate(), inputFormatter);

        Outgoing outgoing = new Outgoing();
        outgoing.setId(updateOutgoing.getOutgoingID());
        outgoing.setDescription(updateOutgoing.getDescription());
        outgoing.setAmount(updateOutgoing.getAmount());
        outgoing.setValue(updateOutgoing.getValue());
        outgoing.setBillDate(date);
        outgoing.setTotal(outgoing.getTotal());
        outgoing.setStore(store);
        outgoing.setUser(user);

        Outgoing updatedOutgoing = outgoingRepository.save(outgoing);
        GlobalResponse apiResponse = new GlobalResponse(200, "Gasto Actualizado", "Gasto actualizado exitosamente", updatedOutgoing);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> getOutgoingById(Long id) {
        Outgoing outgoing = outgoingRepository.findById(id)
                .orElseThrow(() -> new OutgoingNotFoundException("Outgoing not found with id: " + id));
        GlobalResponse apiResponse = new GlobalResponse(200, "Gasto Encontrado", "Gasto encontrado exitosamente", outgoing);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> deleteOutgoingById(Long id) {
        if (outgoingRepository.existsById(id)) {
            outgoingRepository.deleteById(id);
            GlobalResponse apiResponse = new GlobalResponse(200, "Gasto Eliminado", "Gasto eliminado exitosamente", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            GlobalResponse apiResponse = new GlobalResponse(404, "Error", "Gasto no encontrado con id: " + id, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<GlobalResponse> getOutgoingsPerDay(TotalDTO totalDTO) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(totalDTO.getDate(), inputFormatter);
        LocalDateTime startTime = date.atStartOfDay();
        LocalDateTime endTime = date.atTime(23,59,00);

        ITotalOutgoings totalOutgoing = outgoingRepository.totalOutgoing(startTime.toString(),endTime.toString(),totalDTO.getStoreID());
        GlobalResponse response = new GlobalResponse(200, "Sale total Found", "Sale found successfully", totalOutgoing);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

