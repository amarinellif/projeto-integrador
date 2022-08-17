package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoOutput.*;
import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Section;
import dh.meli.projeto_integrador.model.Warehouse;
import dh.meli.projeto_integrador.repository.IBatchRepository;
import dh.meli.projeto_integrador.repository.ISectionRepository;
import dh.meli.projeto_integrador.repository.IWarehouseRepository;
import dh.meli.projeto_integrador.util.Generators;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WarehouseServiceTest {

    @InjectMocks
    WarehouseService warehouseService;

    @Mock
    IWarehouseRepository warehouseRepository;

    @Mock
    IBatchRepository batchRepository;

    @Mock
    ISectionRepository sectionRepository;

    @BeforeEach
    void setup() {
        BDDMockito.when(warehouseRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(Generators.getWarehouse()));
    }

    @Test
    void findWarehouseTest() {
        long id = 0;
        Warehouse warehouse = warehouseService.findWarehouse(id);

        assertThat(warehouse.getId()).isEqualTo(Generators.getWarehouse().getId());
        assertThat(warehouse.getAddress()).isEqualTo(Generators.getWarehouse().getAddress());
        assertThat(warehouse.getName()).isEqualTo(Generators.getWarehouse().getName());

        verify(warehouseRepository, atLeastOnce()).findById(id);
    }

    @Test
    void findWarehouse_WhenWarehouseDontExistsTest() {
        BDDMockito.when(warehouseRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        long id = 0;

        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            Warehouse warehouse = warehouseService.findWarehouse(id);
        });

        assertThat(exception.getMessage()).isEqualTo(String.format("Could not find valid warehouse for id %d", id));

        verify(warehouseRepository, atLeastOnce()).findById(id);
    }

    @Test
    void getWarningTempBatchesByWarehouse_WhenBatchListIsNotEmpty() {
        BDDMockito.when(batchRepository.getWarningTemperaturesByWarehouseId(anyLong()))
                .thenReturn(Generators.validBatchList());

        WarningTempDto warningTempDto = warehouseService.getWarningTempBatchesByWarehouse(1L);

        assertThat(warningTempDto.getIdWarehouse()).isEqualTo(1L);

        verify(batchRepository, atLeastOnce()).getWarningTemperaturesByWarehouseId(anyLong());
    }

    @Test
    void getWarningTempBatchesByWarehouse_WhenBatchListIsEmpty() {
        List<Batch> batchList = new ArrayList<>();
        BDDMockito.when(batchRepository.getWarningTemperaturesByWarehouseId(ArgumentMatchers.anyLong()))
                .thenReturn(batchList);


        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            WarningTempDto warningTempDto = warehouseService.getWarningTempBatchesByWarehouse(
                    Generators.getWarehouse().getId());
        });

        assertThat(exception.getMessage()).isEqualTo(String.format("Everything is okay with this Warehouse. No batch warnings found.",
                Generators.getProduct().getId()));
    }

    @Test
    void getWarningDueDateBatchesByWarehouse_WhenBatchListIsEmpty() {
        List<Batch> batchList = new ArrayList<>();
        BDDMockito.when(batchRepository.getWarningDueDateByWarehouseId(ArgumentMatchers.anyInt(), ArgumentMatchers.anyLong()))
                .thenReturn(batchList);


        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            WarningDueDateDto warningDueDateDto = warehouseService.getWarningDueDateBatchesByWarehouse(
                    Generators.getWarehouse().getId(), 60);
        });

        assertThat(exception.getMessage()).isEqualTo(String.format("Everything is okay with this Warehouse. No batch warnings found.",
                Generators.getProduct().getId()));
    }

    @Test
    void getWrongTempSection_WhenSectionListIsEmpty() {
        List<Section> sectionList = new ArrayList<>();
        BDDMockito.when(sectionRepository.getWrongTempSection(ArgumentMatchers.anyLong()))
                .thenReturn(sectionList);


        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            WrongTempDto wrongTempDto = warehouseService.getWrongTempSection(
                    Generators.getWarehouse().getId());
        });

        assertThat(exception.getMessage()).isEqualTo(String.format("Everything is okay with this Warehouse. No section warnings found.",
                Generators.getProduct().getId()));
    }

}
