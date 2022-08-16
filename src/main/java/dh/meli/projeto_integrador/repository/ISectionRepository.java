package dh.meli.projeto_integrador.repository;

import dh.meli.projeto_integrador.model.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interface ISectionRepository will manage data persistence for Section object instances.
 * Will read, save, update and delete data through the GET, POST, PUT and DELETE requests.
 * @author Diovana Valim
 * @version 0.0.1
 */
public interface ISectionRepository extends CrudRepository<Section, Long> {

    @Query(value = "SELECT * from section " +
            "WHERE warehouse_id = ?1 " +
            "AND ((section.current_temperature_section > section.maximum_temperature_section) " +
            "OR (section.current_temperature_section < section.minimum_temperature_section))", nativeQuery = true)
    List<Section> getWrongTempSection(Long warehouseId);

}
