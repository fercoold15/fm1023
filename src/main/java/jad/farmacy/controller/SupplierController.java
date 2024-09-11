package jad.farmacy.controller;



import jad.farmacy.Service.SupplierService;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewSupplier;
import jad.farmacy.dto.UpdateSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Obtener todos los proveedores
    @GetMapping
    public ResponseEntity<GlobalResponse> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse> getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    // Agregar un nuevo proveedor
    @PostMapping
    public ResponseEntity<GlobalResponse> addSupplier(@RequestBody NewSupplier newSupplier) {
        return supplierService.addSupplier(newSupplier);
    }

    // Actualizar un proveedor existente
    @PutMapping
    public ResponseEntity<GlobalResponse> updateSupplier(@RequestBody UpdateSupplier updateSupplier) {
        return supplierService.updateSupplier(updateSupplier);
    }

    // Eliminar un proveedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse> deleteSupplierById(@PathVariable Long id) {
        return supplierService.deleteSupplierById(id);
    }
}
