package dh.meli.projeto_integrador.dto.dtoOutput;

import dh.meli.projeto_integrador.model.Section;
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
 * Class used to create a Data Transfer Object for Warehouseservice.getWrongTempSection method, wrapped by WrongTempDto class.
 * @author Amanda Marinelli
 * @version 0.0.1
 * @see java.lang.Object
 */
public class SectionWarningTempDto {
    private long sectionId;
    private String sectionType;
    private float currentTemperature;
    private float minimumTemperature;
    private float maximumTemperature;

    public SectionWarningTempDto(Section section) {
        this.sectionId = section.getId();
        this.sectionType = section.getProductType();
        this.currentTemperature = section.getCurrentTemperature();
        this.minimumTemperature = section.getMinimumTemperature();
        this.maximumTemperature = section.getMaxTemperature();
    }
}
