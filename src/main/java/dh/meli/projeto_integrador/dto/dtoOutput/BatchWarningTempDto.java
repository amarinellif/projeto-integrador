package dh.meli.projeto_integrador.dto.dtoOutput;

import dh.meli.projeto_integrador.model.Batch;
import lombok.*;


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
 * Class used to create a Data Transfer Object for Warehouseservice.getWarningTempBatchesByWarehouse method, wrapped by WarningTempDto
 * @author Amanda Marinelli
 * @version 0.0.1
 * @see java.lang.Object
 */
public class BatchWarningTempDto {
    private long batchId;
    private String productName;
    private long orderNumber;
    private float currentTemperature;
    private float minimumTemperature;

    public BatchWarningTempDto(Batch batch) {
        this.batchId = batch.getId();
        this.productName = batch.getProduct().getName();
        this.orderNumber = batch.getOrderEntry().getId();
        this.currentTemperature = batch.getCurrentTemperature();
        this.minimumTemperature = batch.getMinimumTemperature();
    }
}
