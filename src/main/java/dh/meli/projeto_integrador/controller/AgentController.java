package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dto.dtoOutput.WarningDueDateDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WarningTempDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WrongPlaceBatchDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WrongTempDto;
import dh.meli.projeto_integrador.model.IWrongPlaceBatch;
import dh.meli.projeto_integrador.repository.IBatchRepository;
import dh.meli.projeto_integrador.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Class responsible for processing user's requests and generating appropriated HTTP responses;
 *
 * @author Amanda Marinelli
 * @version 0.0.1;
 */
@RestController
@RequestMapping("/api/v1")
public class AgentController {


    @Autowired
    WarehouseService warehouseService;

    @Autowired
    IBatchRepository repo;

    /**
     * Method to find warnings about wrong temperature on batches by id Warehouse;
     *
     * @param id of type long. Warehouse identifier;
     * @return an object of type WarningTempDto;
     */
    @GetMapping("fresh-products/agent/warning-temp-batches/{id}")
    public ResponseEntity<WarningTempDto> getWarningTempBatches(@PathVariable long id) {
        return ResponseEntity.ok(warehouseService.getWarningTempBatchesByWarehouse(id));
    }

    /**
     * Method to find searches for batch that are close to expiry according to the specified period on days, by Warehouse;
     *
     * @param id        of type long. Warehouse identifier;
     * @param daysUntil of type int. Specified period of days until batch expires.
     * @return an object of type WarningDueDateDto;
     */
    @GetMapping("fresh-products/agent/warning-duedate-batches/{id}")
    public ResponseEntity<WarningDueDateDto> getProductBatches(@PathVariable long id, @RequestParam int daysUntil) {
        return ResponseEntity.ok(warehouseService.getWarningDueDateBatchesByWarehouse(id, daysUntil));
    }

    /**
     * Method to find the sections that may have to control the temperature by id Warehouse;
     *
     * @param id of type long. Warehouse identifier;
     * @return an object of type WrongPlaceBatchDto;
     */
    @GetMapping("fresh-products/agent/warning-temp-section/{id}")
    public ResponseEntity<WrongTempDto> getWrongTempBatches(@PathVariable long id) {
        return ResponseEntity.ok(warehouseService.getWrongTempSection(id));
    }

    /**
     * Method to find batches that may have been stored in the wrong section by id Warehouse;
     *
     * @param id of type long. Warehouse identifier;
     * @return an object of type WrongPlaceBatchDto;
     */
    @GetMapping("fresh-products/agent/wrong-place-batches/{id}")
    public ResponseEntity<WrongPlaceBatchDto> getWrongPlaceBatches(@PathVariable long id) {
        return ResponseEntity.ok(warehouseService.getWrongPlaceBatches(id));
    }
}
