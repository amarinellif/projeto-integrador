package dh.meli.projeto_integrador.dto.dtoOutput;

import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Section;
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
 * Class used to create a Data Transfer Object for Warehouseservice.getWarningTemperaturesByWarehouse method
 * @author Amanda Marinelli
 * @version 0.0.1
 * @see java.lang.Object
 */
public class WrongTempDto {
    private long idWarehouse;
    private String nameWarehouse;
    private List<SectionWarningTempDto> sectionWarningTempDtoList = new ArrayList<>();


    public WrongTempDto(Warehouse warehouse, List<Section> sectionList) {
        this.nameWarehouse = warehouse.getName();
        this.idWarehouse = warehouse.getId();

        for (Section s : sectionList) {
            this.sectionWarningTempDtoList.add(new SectionWarningTempDto(s));
        }
    }
}
