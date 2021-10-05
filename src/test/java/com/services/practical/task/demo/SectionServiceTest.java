package com.services.practical.task.demo;

import com.services.practical.task.demo.model.ServiceRequest;
import com.services.practical.task.demo.model.enums.SelectionType;
import com.services.practical.task.demo.repository.IServiceRequestRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
public class SectionServiceTest {

    @Autowired
    private IServiceRequestRepo service;

    private ServiceRequest serviceRequest;

    @BeforeEach
    void setUp() {
        service.deleteAll();
        serviceRequest = new ServiceRequest("service 1", "Service", "body", SelectionType.Laptop, 10);
    }

    @Test
    @DisplayName("Add Service Request")
    @Transactional
    void addServiceRequest() {
        service.save(serviceRequest);
        assertEquals(1L, service.count());
        assertNotEquals(0, serviceRequest.getId());
    }

    @Test
    @DisplayName("Edit Service Request")
    @Transactional
    void editServiceRequest() {
        service.save(serviceRequest);
        serviceRequest.setSelection(SelectionType.PC);
        service.save(serviceRequest);
        ServiceRequest fromDB = service.getById(serviceRequest.getId());
        assertEquals(fromDB.getId(), serviceRequest.getId());
        assertEquals(SelectionType.PC, fromDB.getSelection());
    }

    @Test
    @DisplayName("Delete Service Request")
    @Transactional
    void deleteServiceRequest() {
        service.save(serviceRequest);
        long id = serviceRequest.getId();
        service.delete(serviceRequest);
        ServiceRequest deletedSection = service.findById(id).orElse(null);
        assertNull(deletedSection);
    }

}
