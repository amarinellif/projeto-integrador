package dh.meli.projeto_integrador.model;

/**
 * Interface used to receive the database query, for Warehouseservice.getWrongPlaceBatches method, wrapped by WrongPlaceBatchDto
 * @author Amanda Marinelli
 * @version 0.0.1
 * @see java.lang.Object
 */
public interface IWrongPlaceBatch {
    Long getBatch_id();

    Long getProduct_id();

    String getProduct_name();

    String getProduct_type();

    Float getMinimum_temperature();

    Float getMinimum_temperature_section();

    Float getMaximum_temperature_section();

}

