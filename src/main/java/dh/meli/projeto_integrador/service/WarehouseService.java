package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoOutput.WarningDueDateDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WarningTempDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WrongTempDto;
import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Warehouse;
import dh.meli.projeto_integrador.repository.IBatchRepository;
import dh.meli.projeto_integrador.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class responsible for business rules and communication with the Warehouse Repository layer;
 *
 * @author Diovana Valim;
 * @version 0.0.1
 */
@Service
public class WarehouseService implements IWarehouseService {

    /**
     * Dependency Injection of the Warehouse Repository.
     */
    @Autowired
    private IWarehouseRepository warehouseRepository;

    @Autowired
    private IBatchRepository batchRepository;

    /**
     * Method to find a warehouse by id;
     *
     * @param id of type long. Warehouse identifier;
     * @return an object of type Warehouse;
     */
    @Override
    public Warehouse findWarehouse(long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if (warehouse.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Could not find valid warehouse for id %d", id));
        }

        return warehouse.get();
    }

    /**
     * Method to find warnings about high temperatature on batches by id Warehouse;
     *
     * @param id of type long. Warehouse identifier;
     * @return an object of type WarningTempDto;
     */
    @Override
    public WarningTempDto getWarningTempBatchesByWarehouse(long id) {
        Warehouse warehouse = findWarehouse(id);
        List<Batch> batchList = batchRepository.getWarningTemperaturesByWarehouseId(id);

        if (batchList.isEmpty()) {
            throw new ResourceNotFoundException("Everything is okay with this Warehouse. No batch warnings found.");
        }
        return new WarningTempDto(warehouse, batchList);
    }

    /**
     * Method to find warnings about high temperatature on batches by id Warehouse;
     *
     * @param id of type long. Warehouse identifier;
     * @return an object of type WarningTempDto;
     */
    @Override
    public WarningDueDateDto getWarningDueDateBatchesByWarehouse(int daysUntil,long id) {
        Warehouse warehouse = findWarehouse(id);
        List<Batch> batchList = batchRepository.getWarningDueDateByWarehouseId(daysUntil,id);

        if (batchList.isEmpty()) {
            throw new ResourceNotFoundException("Everything is okay with this Warehouse. No batch warnings found.");
        }
        return new WarningDueDateDto(warehouse,batchList);
    }

    @Override
    public WrongTempDto getWrongTempSection(Long id){
        //TODO DESVINCULAR A QUERY DO BATCH, atrelar a sessão :)
        Warehouse warehouse = findWarehouse(id);
        List<Batch> batchList = batchRepository.getWrongTempSection(id);

        if (batchList.isEmpty()) {
            throw new ResourceNotFoundException("Everything is okay with this Warehouse. No batch warnings found.");
        }
        return new WrongTempDto(warehouse, batchList);
    }
}
