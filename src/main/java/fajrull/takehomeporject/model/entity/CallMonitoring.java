package fajrull.takehomeporject.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "call_records")
public class CallMonitoring {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "call_id", nullable = false, length = 50, unique = true)
    private String callId;

    @Column(name = "call_timestamp", nullable = false)
    private OffsetDateTime callTimestamp;

    @Column(name = "cs_name", nullable = false, length = 100)
    private String csName;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Column(name = "sentiment_score", nullable = false, precision = 5, scale = 2)
    private BigDecimal sentimentScore;

    @Column(name = "created_at", insertable = false, updatable = false)
    private OffsetDateTime createdAt;
}
