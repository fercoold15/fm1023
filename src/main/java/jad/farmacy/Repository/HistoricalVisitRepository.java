package jad.farmacy.Repository;

import jad.farmacy.Entity.HistoricalVisit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoricalVisitRepository extends CrudRepository<HistoricalVisit,Long> {

    @Query(value = "SELECT * FROM HISTORICAL_VISITS WHERE PATIENT_ID=:PATIENT",nativeQuery = true)
    List<HistoricalVisit> getHistoricalByPatient(@Param("PATIENT") long patient);
}
