package com.services.practical.task.demo.controller;

import com.services.practical.task.demo.exception.CustomException;
import com.services.practical.task.demo.model.ServiceRequest;
import com.services.practical.task.demo.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/requests")
public class ServiceRequestController {

    @Autowired
    private RequestService service;

    @GetMapping(value = "services", produces = "application/json")
    public ResponseEntity<List<ServiceRequest>> getAllDrones() {
        try {
            List<ServiceRequest> list = service.getAllRequests();

            if (list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/services/{id}", produces = "application/json")
    public ResponseEntity<ServiceRequest> getServiceRequestById(@PathVariable(value = "id") Long droneId)
            throws CustomException {
        return ResponseEntity.ok().body(service.getServiceRequestById(droneId));
    }

    @PutMapping(path = "/services/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ServiceRequest> updateServiceRequest(@PathVariable(value = "id") Long serviceRequestId,
                                                               @Valid @RequestBody ServiceRequest serviceRequestToSave) throws CustomException {
        return ResponseEntity.ok(service.updateServiceRequest(serviceRequestId, serviceRequestToSave));
    }

    @PostMapping(path = "/services", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ServiceRequest> saveServiceRequest(@Valid @RequestBody ServiceRequest serviceRequest) throws CustomException {
        return ResponseEntity.status(HttpStatus.CREATED.value()).contentType(MediaType.APPLICATION_JSON).body(service.saveServiceRequest(serviceRequest));
    }

    @DeleteMapping(path = "/services/{id}", consumes = "application/json", produces = "application/json")
    public Map<String, Boolean> deleteServiceRequest(@PathVariable(value = "id") Long droneId)
            throws CustomException {
        return service.deleteServiceRequest(droneId);
    }

}
