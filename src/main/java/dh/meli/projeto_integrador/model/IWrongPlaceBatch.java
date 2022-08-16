package dh.meli.projeto_integrador.model;

public interface IWrongPlaceBatch {
    Long getBatch_id();

    Long getProduct_id();

    String getProduct_name();

    String getProduct_type();

    Float getMinimum_temperature();

    Float getMinimum_temperature_section();

    Float getMaximum_temperature_section();

}

