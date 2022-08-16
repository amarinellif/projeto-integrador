package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoOutput.WarningDueDateDto;
import dh.meli.projeto_integrador.dto.dtoOutput.WarningTempDto;
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
     * Method for to possibles Warnings about incorrects temperatures by Warehouse Id
     *
     * @param id long
     * @return an object of type WarningTempDto
     */
    WarningTempDto getWarningTempBatchesByWarehouse(long id);

    /**
     * Method for to possibles Warnings about incorrects temperatures by Warehouse Id
     *
     * @param id long
     * @return an object of type WarningDueDateDto
     */
    WarningDueDateDto getWarningDueDateBatchesByWarehouse(int daysUntil, long id);
    /**
     * Method for to possibles Warnings about incorrects temperatures by Warehouse Id
     *
     * @param id long
     * @return an object of type WrongTempDto
     */
    WrongTempDto getWrongTempSection(Long warehouseId);

}
