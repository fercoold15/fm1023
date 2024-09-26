package jad.farmacy.Service;

import jad.farmacy.Entity.Accounting;
import jad.farmacy.Entity.Outgoing;
import jad.farmacy.Entity.Store;
import jad.farmacy.Entity.User;
import jad.farmacy.Repository.AccountingRepository;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewAccounting;
import jad.farmacy.dto.NewOutgoing;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
public class AccountingService {

    private final AccountingRepository accountingRepository;

    public AccountingService(AccountingRepository accountingRepository) {
        this.accountingRepository = accountingRepository;
    }

    public ResponseEntity<GlobalResponse> allAccounting() {
        List<Accounting> outgoings = new ArrayList<>();
        Iterable<Accounting> iterable = accountingRepository.findAll();
        iterable.forEach(outgoings::add);
        GlobalResponse apiResponse = new GlobalResponse(200, "Registros Encontrados", "Registros encontrados exitosamente", outgoings);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> addAccounting(NewAccounting newAccounting) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(newAccounting.getComparisonDate(), inputFormatter);
        Accounting accounting = new Accounting();
        accounting.setComparisonDate(date);
        accounting.setTotalNet(newAccounting.getTotalNet());
        accounting.setTotalOutgoings(newAccounting.getTotalOutgoings());
        accounting.setTotalSellings(newAccounting.getTotalSellings());
        accountingRepository.save(accounting);
        GlobalResponse apiResponse = new GlobalResponse(200, "Gasto Agregado", "Gasto agregado exitosamente", null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> deleteAccounting(Long id) {
        if (accountingRepository.existsById(id)) {
            accountingRepository.deleteById(id);
            GlobalResponse apiResponse = new GlobalResponse(200, "Cotejo Eliminado", "Cotejo eliminado exitosamente", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            GlobalResponse apiResponse = new GlobalResponse(404, "Error", "Cotejo no encontrado con id: " + id, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
}
