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

    @GetMapping("fresh-products/agent/warning-temp-batches/{id}")
    public ResponseEntity<WarningTempDto> getWarningTempBatches(@PathVariable long id){
        return ResponseEntity.ok(warehouseService.getWarningTempBatchesByWarehouse(id));
    }

    @GetMapping("fresh-products/agent/warning-duedate-batches/{id}")
    public ResponseEntity<WarningDueDateDto> getProductBatches(@PathVariable long id, @RequestParam int daysUntil){
        return ResponseEntity.ok(warehouseService.getWarningDueDateBatchesByWarehouse(id,daysUntil));
    }

    @GetMapping("fresh-products/agent/warning-temp-section/{id}")
    public ResponseEntity<WrongTempDto> getWrongTempBatches(@PathVariable long id){
       return ResponseEntity.ok(warehouseService.getWrongTempSection(id));
    }

    @GetMapping("fresh-products/agent/wrong-place-batches/{id}")
    public ResponseEntity<WrongPlaceBatchDto> getWrongPlaceBatches(@PathVariable long id){
        return ResponseEntity.ok(warehouseService.getWrongPlaceBatches(id));
    }
}
