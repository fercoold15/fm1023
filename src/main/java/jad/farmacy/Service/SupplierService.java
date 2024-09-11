package jad.farmacy.Service;


import jad.farmacy.Entity.Supplier;
import jad.farmacy.Repository.SupplierRepository;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.NewSupplier;
import jad.farmacy.dto.UpdateSupplier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // Obtener todos los proveedores
    public ResponseEntity<GlobalResponse> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        supplierRepository.findAll().forEach(suppliers::add);
        GlobalResponse apiResponse = new GlobalResponse(200, "Proveedores Encontrados",
                "Lista de proveedores obtenida exitosamente", suppliers);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Obtener un proveedor por ID
    public ResponseEntity<GlobalResponse> getSupplierById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            GlobalResponse apiResponse = new GlobalResponse(200, "Proveedor Encontrado",
                    "Proveedor encontrado exitosamente", supplier.get());
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            GlobalResponse apiResponse = new GlobalResponse(404, "Proveedor no encontrado",
                    "No se encontró el proveedor con ID: " + id, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    // Agregar un nuevo proveedor
    public ResponseEntity<GlobalResponse> addSupplier(NewSupplier newSupplier) {
        Supplier supplier = new Supplier();
        supplier.setDescription(newSupplier.getDescription());
        supplier.setName(newSupplier.getName());
        supplier.setPhone(newSupplier.getPhone());
        supplier.setEmail(newSupplier.getEmail());
        Supplier savedSupplier = supplierRepository.save(supplier);
        GlobalResponse apiResponse = new GlobalResponse(201, "Proveedor Agregado",
                "Proveedor agregado exitosamente", savedSupplier);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    // Actualizar un proveedor existente
    public ResponseEntity<GlobalResponse> updateSupplier(UpdateSupplier updateSupplier) {
        Optional<Supplier> existingSupplier = supplierRepository.findById(updateSupplier.getSupplierID());
        if (existingSupplier.isPresent()) {
            Supplier supplier = existingSupplier.get();
            supplier.setName(updateSupplier.getName());
            supplier.setEmail(updateSupplier.getEmail());
            supplier.setPhone(updateSupplier.getPhone());
            supplier.setDescription(updateSupplier.getDescription());
            Supplier updatedSupplier = supplierRepository.save(supplier);
            GlobalResponse apiResponse = new GlobalResponse(200, "Proveedor Actualizado",
                    "Proveedor actualizado exitosamente", updatedSupplier);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            GlobalResponse apiResponse = new GlobalResponse(404, "Proveedor no encontrado",
                    "No se encontró el proveedor con ID: " + updateSupplier.getSupplierID(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un proveedor por ID
    public ResponseEntity<GlobalResponse> deleteSupplierById(Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            GlobalResponse apiResponse = new GlobalResponse(200, "Proveedor Eliminado",
                    "Proveedor eliminado exitosamente", null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } else {
            GlobalResponse apiResponse = new GlobalResponse(404, "Proveedor no encontrado",
                    "No se encontró el proveedor con ID: " + id, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
}
