package fajrull.takehomeporject.controller;

import fajrull.takehomeporject.model.dto.request.SearchRequest;
import fajrull.takehomeporject.model.dto.response.CallMonitoringResponse;
import fajrull.takehomeporject.model.dto.response.CommonResponse;
import fajrull.takehomeporject.model.dto.response.PagingResponse;
import fajrull.takehomeporject.service.CallMonitoringService;
import fajrull.takehomeporject.utils.validate.PagingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping(path = "/api/v1/call-monitoring")
@RequiredArgsConstructor
public class CallMonitoringController {
    private final CallMonitoringService callMonitoringService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<CallMonitoringResponse>>> getAllCallMonitoring(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(name = "sort", defaultValue = "csName") String sort,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(name = "sentiment", required = false) String sentiment
    ){
        page = PagingUtil.validatePage(page);
        size = PagingUtil.validateSize(size);
        direction = PagingUtil.validateDirection(direction);

        SearchRequest request = SearchRequest.builder()
                .size(size)
                .page(Math.max(page -1 ,0))
                .query(search)
                .sort(sort)
                .direction(direction)
                .startDate(startDate)
                .endDate(endDate)
                .sentiment(sentiment)
                .build();
        Page<CallMonitoringResponse> callMonitorings = callMonitoringService.getAll(request);
        PagingResponse paging = PagingResponse.builder()
                .totalPages(callMonitorings.getTotalPages())
                .totalElements(callMonitorings.getTotalElements())
                .page(page)
                .size(size)
                .hasNext(callMonitorings.hasNext())
                .hasPrevious(callMonitorings.hasPrevious())
                .build();

        CommonResponse<List<CallMonitoringResponse>> response = CommonResponse.<List<CallMonitoringResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Call Monitoring retrieved successfully")
                .data(callMonitorings.getContent())
                .paging(paging)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body(response);
    }
}
