package jad.farmacy.controller;

import jad.farmacy.Service.AccountingService;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewAccounting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounting")
public class AccountingController {
    private final AccountingService accountingService;

    public AccountingController(AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse> getAllAccounting() {
        return accountingService.allAccounting();
    }

    @PostMapping
    public ResponseEntity<GlobalResponse> addAccounting(@RequestBody NewAccounting newAccounting) {
        return accountingService.addAccounting(newAccounting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> deleteAccountingById(@PathVariable Long id) {
        return accountingService.deleteAccounting(id);
    }
}
