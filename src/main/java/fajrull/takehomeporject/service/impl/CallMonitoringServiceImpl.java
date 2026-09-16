package fajrull.takehomeporject.service.impl;

import fajrull.takehomeporject.model.dto.request.SearchRequest;
import fajrull.takehomeporject.model.dto.response.CallMonitoringResponse;
import fajrull.takehomeporject.model.entity.CallMonitoring;
import fajrull.takehomeporject.repository.CallMonitoringRepository;
import fajrull.takehomeporject.service.CallMonitoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CallMonitoringServiceImpl implements CallMonitoringService {
    private final CallMonitoringRepository callMonitoringRepository;

    @Override
    public Page<CallMonitoringResponse> getAll(SearchRequest request) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(request.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        String sortProperty = (request.getSort() != null && !request.getSort().isEmpty()) ? request.getSort() : "csName";
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(sortDirection, sortProperty));

        Specification<CallMonitoring> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getQuery() != null && !request.getQuery().isEmpty()) {
                String searchLike = "%" + request.getQuery().toLowerCase() + "%";
                Predicate idPred = cb.like(cb.lower(root.get("callId")), searchLike);
                Predicate csNamePred = cb.like(cb.lower(root.get("csName")), searchLike);
                Predicate custNamePred = cb.like(cb.lower(root.get("customerName")), searchLike);
                predicates.add(cb.or(idPred, csNamePred, custNamePred));
            }

            if (request.getStartDate() != null && request.getEndDate() != null) {
                predicates.add(cb.between(root.get("callTimestamp"), 
                        request.getStartDate().atStartOfDay().atOffset(ZoneOffset.UTC), 
                        request.getEndDate().atTime(LocalTime.MAX).atOffset(ZoneOffset.UTC)));
            } else if (request.getStartDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("callTimestamp"), 
                        request.getStartDate().atStartOfDay().atOffset(ZoneOffset.UTC)));
            } else if (request.getEndDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("callTimestamp"), 
                        request.getEndDate().atTime(LocalTime.MAX).atOffset(ZoneOffset.UTC)));
            }

            if (request.getSentiment() != null) {
                if ("BELOW_70".equalsIgnoreCase(request.getSentiment()) || "Di bawah 70%".equalsIgnoreCase(request.getSentiment())) {
                    predicates.add(cb.lessThan(root.get("sentimentScore"), new BigDecimal("70.00")));
                } else if ("ABOVE_OR_EQUAL_70".equalsIgnoreCase(request.getSentiment()) || "70% atau lebih".equalsIgnoreCase(request.getSentiment())) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("sentimentScore"), new BigDecimal("70.00")));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<CallMonitoring> callMonitoringPage = callMonitoringRepository.findAll(spec, pageable);
        return callMonitoringPage.map(this::mapToResponse);
    }

    private CallMonitoringResponse mapToResponse(CallMonitoring callMonitoring) {
        return CallMonitoringResponse.builder()
                .callId(callMonitoring.getCallId())
                .callTimestamp(callMonitoring.getCallTimestamp())
                .csName(callMonitoring.getCsName())
                .customerName(callMonitoring.getCustomerName())
                .sentimentScore(callMonitoring.getSentimentScore())
                .createdAt(callMonitoring.getCreatedAt())
                .build();
    }
}

