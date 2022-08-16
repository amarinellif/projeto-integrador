package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoOutput.WarningDueDateDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WarningTempDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WrongPlaceBatchDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WrongTempDto;
import dh.meli.projeto_integrador.model.Warehouse;

/**
 * Interface to specify service methods implemented on WarehouseService class.
 *
 * @author Diovana Valim, Thiago Guimaraes and Amanda Marinelli
 * @version 0.0.1
 */
public interface IWarehouseService {
    /**
     * Method for to find Warehouse by Id
     *
     * @param id long
     * @return an object of type Warehouse
     */
    Warehouse findWarehouse(long id);

    /**
     * Method to find warnings about wrong temperature on batches by id Warehouse;
     *
     * @param id of type long. Warehouse identifier;
     * @return an object of type WarningTempDto;
     */
    WarningTempDto getWarningTempBatchesByWarehouse(long id);

    /**
     * Method to find searches for batch that are close to expiry according to the specified period on days, by Warehouse;
     *
     * @param id of type long. Warehouse identifier;
     * @param daysUntil of type int. Specified period of days until batch expires.
     * @return an object of type WarningDueDateDto;
     */
    WarningDueDateDto getWarningDueDateBatchesByWarehouse(long id, int daysUntil);

    /**
     * Method to find the sections that may have to control the temperature by id Warehouse;
     *
     * @param warehouseId of type long. Warehouse identifier;
     * @return an object of type WrongPlaceBatchDto;
     */
    WrongTempDto getWrongTempSection(Long warehouseId);

    /**
     * Method to find batches that may have been stored in the wrong section by id Warehouse;
     *
     * @param id of type long. Warehouse identifier;
     * @return an object of type WrongPlaceBatchDto;
     */
    WrongPlaceBatchDto getWrongPlaceBatches(long id);
}
