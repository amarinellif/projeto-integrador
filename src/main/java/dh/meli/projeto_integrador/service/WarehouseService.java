package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoOutput.WarningDueDateDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WarningTempDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WrongPlaceBatchDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WrongTempDto;
import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.IWrongPlaceBatch;
import dh.meli.projeto_integrador.model.Section;
import dh.meli.projeto_integrador.model.Warehouse;
import dh.meli.projeto_integrador.repository.IBatchRepository;
import dh.meli.projeto_integrador.repository.ISectionRepository;
import dh.meli.projeto_integrador.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class responsible for business rules and communication with the Warehouse Repository layer;
 *
 * @author Diovana Valim e Amanda mA;
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

    @Autowired
    private ISectionRepository sectionRepository;

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
     * Method to find warnings about wrong temperature on batches by id Warehouse;
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
     * Method to find searches for batch that are close to expiry according to the specified period on days, by Warehouse;
     *
     * @param id        of type long. Warehouse identifier;
     * @param daysUntil of type int. Specified period of days until batch expires.
     * @return an object of type WarningDueDateDto;
     */
    @Override
    public WarningDueDateDto getWarningDueDateBatchesByWarehouse(long id, int daysUntil) {
        Warehouse warehouse = findWarehouse(id);
        List<Batch> batchList = batchRepository.getWarningDueDateByWarehouseId(daysUntil, id);

        if (batchList.isEmpty()) {
            throw new ResourceNotFoundException("Everything is okay with this Warehouse. No batch warnings found.");
        }
        return new WarningDueDateDto(warehouse, batchList);
    }


    /**
     * Method to find the sections that may have to control the temperature by id Warehouse;
     *
     * @param id of type long. Warehouse identifier;
     * @return an object of type WrongPlaceBatchDto;
     */
    @Override
    public WrongTempDto getWrongTempSection(Long id) {
        Warehouse warehouse = findWarehouse(id);
        List<Section> sectionList = sectionRepository.getWrongTempSection(id);

        if (sectionList.isEmpty()) {
            throw new ResourceNotFoundException("Everything is okay with this Warehouse. No section warnings found.");
        }
        return new WrongTempDto(warehouse, sectionList);
    }


    /**
     * Method to find batches that may have been stored in the wrong section by id Warehouse;
     *
     * @param id of type long. Warehouse identifier;
     * @return an object of type WrongPlaceBatchDto;
     */
    @Override
    public WrongPlaceBatchDto getWrongPlaceBatches(long id) {
        Warehouse warehouse = findWarehouse(id);
        List<IWrongPlaceBatch> batchList = batchRepository.getWrongPlaceBatches(id);

        if (batchList.isEmpty()) {
            throw new ResourceNotFoundException("Everything is okay with this Warehouse. No batch warnings found.");
        }

        return new WrongPlaceBatchDto(warehouse, batchList);
    }
}
