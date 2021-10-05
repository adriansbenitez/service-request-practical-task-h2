package com.services.practical.task.demo.service;

import com.services.practical.task.demo.exception.CustomException;
import com.services.practical.task.demo.model.ServiceRequest;
import com.services.practical.task.demo.repository.IServiceRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestService {

    @Autowired
    IServiceRequestRepo serviceRequestRepo;

    public List<ServiceRequest> getAllRequests() {
        return serviceRequestRepo.findAll();
    }

    public ServiceRequest updateServiceRequest(Long serviceRequestId, ServiceRequest serviceRequestToSave) throws CustomException {
        final ServiceRequest serviceRequest = serviceRequestRepo.findById(serviceRequestId)
                .orElseThrow(() -> new CustomException("Service Request not found for this id :: " + serviceRequestId));

        //fill elements
        serviceRequest.setName(serviceRequestToSave.getName());
        serviceRequest.setBody(serviceRequestToSave.getBody());
        serviceRequest.setHeading(serviceRequestToSave.getHeading());
        serviceRequest.setPriority(serviceRequestToSave.getPriority());
        serviceRequest.setSelection(serviceRequestToSave.getSelection());

        return serviceRequestRepo.save(serviceRequest);
    }

    public ServiceRequest saveServiceRequest(ServiceRequest serviceRequestToSave) throws CustomException {
        return serviceRequestRepo.save(serviceRequestToSave);
    }

    public ServiceRequest getServiceRequestById(Long serviceRequestId) throws CustomException {
        return serviceRequestRepo.findById(serviceRequestId)
                .orElseThrow(() -> new CustomException("Service Request not found for this id :: " + serviceRequestId));
    }

    public Map<String, Boolean> deleteServiceRequest(Long serviceRequestId) throws CustomException {
        ServiceRequest drone = serviceRequestRepo.findById(serviceRequestId)
                .orElseThrow(() -> new CustomException("Service Request not found for this id :: " + serviceRequestId));
        serviceRequestRepo.delete(drone);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
