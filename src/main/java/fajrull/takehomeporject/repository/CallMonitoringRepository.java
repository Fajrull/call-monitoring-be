package fajrull.takehomeporject.repository;

import fajrull.takehomeporject.model.entity.CallMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CallMonitoringRepository extends JpaRepository<CallMonitoring, String>, JpaSpecificationExecutor<CallMonitoring> {
}
