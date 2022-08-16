package dh.meli.projeto_integrador.dto.dtoOutput;

import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Warehouse;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Method Getter implemented by Lombok lib for get access the private attributes of the WarningTempDto Class
 */
@Getter

/**
 * Method Setter implemented by Lombok lib for set access the private attributes of the WarningTempDto Class
 */
@Setter
/**
 * Method builder implemented by Lombok lib
 */
@Builder
/**
 * Method Constructor with all arguments implemented by Lombok lib
 */
@AllArgsConstructor
/**
 * Method Default Constructor implemented by Lombok lib
 */
@NoArgsConstructor

/**
 * Class used to create a Data Transfer Object for Warehouseservice.getWarningTempBatchesByWarehouse method
 * @author Amanda Marinelli
 * @version 0.0.1
 * @see java.lang.Object
 */
public class WarningTempDto {
    private long idWarehouse;
    private String nameWarehouse;
    private List<BatchWarningTempDto> batchWarningTempDtoList = new ArrayList<>();


    public WarningTempDto(Warehouse warehouse,List<Batch> batchList) {
        this.nameWarehouse = warehouse.getName();
        this.idWarehouse = warehouse.getId();

        for (Batch b : batchList) {
            this.batchWarningTempDtoList.add(new BatchWarningTempDto(b));
        }
    }

}
