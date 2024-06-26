package com.medicalstore.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.medicalstore.controller.SalesController;
import com.medicalstore.dao.ClientDao;
import com.medicalstore.dao.MedicineDao;
import com.medicalstore.dto.ClientDto;
import com.medicalstore.dto.MedicinDto;
import com.medicalstore.entity.Sales;
import com.medicalstore.service.SalesService;

@ExtendWith(MockitoExtension.class)
public class SalesTests {

    @Mock
    private SalesService salesService;

    @Mock
    private ClientDao clientDao;

    @Mock
    private MedicineDao medicineDao;

    @InjectMocks
    private SalesController salesController;

    @Test
    public void testGetAllSales() {
        List<Sales> sales = new ArrayList<>();
        sales.add(new Sales(1L, 1L, 1L, 100.0, "2024-03-20"));
        sales.add(new Sales(2L, 2L, 2L, 200.0, "2024-03-21"));

        when(salesService.getAllSales()).thenReturn(sales);

        ResponseEntity<List<Sales>> response = salesController.getAllSales();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sales, response.getBody());
    }

    @Test
    public void testPurchaseMedicine() {
        Long clientId = 1L;
        Long medicineId = 1L;
        Integer quantitiesSold = 10;

        Sales newSales = new Sales(1L, clientId, medicineId, 100.0, "2024-03-20");

        when(salesService.purchaseMedicine(clientId, medicineId, quantitiesSold)).thenReturn(newSales);

        ResponseEntity<Sales> response = salesController.purchaseMedicine(clientId, medicineId, quantitiesSold);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newSales, response.getBody());
    }

    @Test
    public void testDeleteSale() {
        Long id = 1L;

        ResponseEntity<Void> response = salesController.deleteSale(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(salesService, times(1)).deleteSaleById(id);
    }

    @Test
    public void testGetClient() {
        Long clientId = 1L;
        ClientDto clientDto = new ClientDto();

        when(clientDao.getClientById(clientId)).thenReturn(ResponseEntity.ok(clientDto));

        assertEquals(clientDto, salesController.get(clientId));
    }

    @Test
    public void testGetMedicineById() {
        Long medicineId = 1L;
        MedicinDto medicineDto = new MedicinDto();

        when(medicineDao.getMedicineById(medicineId)).thenReturn(ResponseEntity.ok(medicineDto));

        assertEquals(medicineDto, salesController.getMedicineById(medicineId));
    }

    @Test
    public void testGetSalesByClientId() {
        Long clientId = 1L;
        List<Sales> sales = new ArrayList<>();
        sales.add(new Sales(1L, clientId, 1L, 100.0, "2024-03-20"));
        sales.add(new Sales(2L, clientId, 2L, 200.0, "2024-03-21"));

        when(salesService.getSalesByClientId(clientId)).thenReturn(sales);

        ResponseEntity<List<Sales>> response = salesController.getSalesByClientId(clientId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sales, response.getBody());
    }
}
