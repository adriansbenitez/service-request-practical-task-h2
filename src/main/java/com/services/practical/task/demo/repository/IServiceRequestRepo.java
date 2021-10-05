package com.services.practical.task.demo.repository;

import com.services.practical.task.demo.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IServiceRequestRepo extends JpaRepository<ServiceRequest, Long> {
}
