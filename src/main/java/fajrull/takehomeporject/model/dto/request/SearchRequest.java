package fajrull.takehomeporject.model.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SearchRequest {
    private String query;
    private Integer page;
    private Integer size;
    private String sort;
    private String direction;
    private LocalDate startDate;
    private LocalDate endDate;
    private String sentiment;
}