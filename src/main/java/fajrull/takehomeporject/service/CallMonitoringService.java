package fajrull.takehomeporject.service;

import fajrull.takehomeporject.model.dto.request.SearchRequest;
import fajrull.takehomeporject.model.dto.response.CallMonitoringResponse;
import org.springframework.data.domain.Page;

public interface CallMonitoringService {
    Page<CallMonitoringResponse> getAll(SearchRequest request);
}
