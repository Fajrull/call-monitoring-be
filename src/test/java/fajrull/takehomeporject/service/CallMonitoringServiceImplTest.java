package fajrull.takehomeporject.service;

import fajrull.takehomeporject.model.dto.request.SearchRequest;
import fajrull.takehomeporject.model.dto.response.CallMonitoringResponse;
import fajrull.takehomeporject.model.entity.CallMonitoring;
import fajrull.takehomeporject.repository.CallMonitoringRepository;
import fajrull.takehomeporject.service.impl.CallMonitoringServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CallMonitoringServiceImplTest {

    @Mock
    private CallMonitoringRepository callMonitoringRepository;

    @InjectMocks
    private CallMonitoringServiceImpl callMonitoringService;

    private CallMonitoring callMonitoring;

    @BeforeEach
    void setUp() {
        callMonitoring = CallMonitoring.builder()
                .callId("C-1234")
                .csName("fajrul")
                .customerName("John Doe")
                .sentimentScore(new BigDecimal("85.50"))
                .callTimestamp(OffsetDateTime.now())
                .createdAt(OffsetDateTime.now())
                .build();
    }

    @Test
    void testGetAllCallMonitoringSuccess() {
        List<CallMonitoring> callMonitorings = List.of(callMonitoring);
        Page<CallMonitoring> callMonitoringPage = new PageImpl<>(callMonitorings);

        // Search request
        SearchRequest searchRequest = SearchRequest.builder()
                .query("fajrul")
                .page(0)
                .size(5)
                .direction("asc")
                .sort("csName")
                .build();

        when(callMonitoringRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(callMonitoringPage);

        Page<CallMonitoringResponse> response = callMonitoringService.getAll(searchRequest);

        // Assertions
        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        assertEquals("fajrul", response.getContent().get(0).getCsName());
        assertEquals("C-1234", response.getContent().get(0).getCallId());
    }
}
