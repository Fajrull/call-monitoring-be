package fajrull.takehomeporject.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class CallMonitoringResponse {
    private String callId;
    private OffsetDateTime callTimestamp;
    private String csName;
    private String customerName;
    private BigDecimal sentimentScore;
    private OffsetDateTime createdAt;
}
