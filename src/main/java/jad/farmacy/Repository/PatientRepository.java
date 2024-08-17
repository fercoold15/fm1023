package jad.farmacy.Repository;
import jad.farmacy.Entity.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
