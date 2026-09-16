package fajrull.takehomeporject.config;

import fajrull.takehomeporject.model.entity.CallMonitoring;
import fajrull.takehomeporject.repository.CallMonitoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final CallMonitoringRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            List<CallMonitoring> seedData = List.of(
                    CallMonitoring.builder()
                            .callId("CALL-001")
                            .callTimestamp(OffsetDateTime.of(2026, 1, 10, 10, 0, 0, 0, ZoneOffset.UTC))
                            .csName("Budi Santoso")
                            .customerName("Andi Wirawan")
                            .sentimentScore(new BigDecimal("85.50"))
                            .build(),
                    CallMonitoring.builder()
                            .callId("CALL-002")
                            .callTimestamp(OffsetDateTime.of(2026, 1, 15, 14, 30, 0, 0, ZoneOffset.UTC))
                            .csName("Siti Aminah")
                            .customerName("Rina Kartika")
                            .sentimentScore(new BigDecimal("65.00"))
                            .build(),
                    CallMonitoring.builder()
                            .callId("CALL-003")
                            .callTimestamp(OffsetDateTime.of(2026, 2, 5, 9, 15, 0, 0, ZoneOffset.UTC))
                            .csName("Budi Santoso")
                            .customerName("Dimas Aditya")
                            .sentimentScore(new BigDecimal("92.00"))
                            .build(),
                    CallMonitoring.builder()
                            .callId("CALL-004")
                            .callTimestamp(OffsetDateTime.of(2026, 3, 20, 11, 45, 0, 0, ZoneOffset.UTC))
                            .csName("Ahmad Faisal")
                            .customerName("Maya Sari")
                            .sentimentScore(new BigDecimal("50.25"))
                            .build(),
                    CallMonitoring.builder()
                            .callId("CALL-005")
                            .callTimestamp(OffsetDateTime.of(2026, 4, 2, 16, 20, 0, 0, ZoneOffset.UTC))
                            .csName("Siti Aminah")
                            .customerName("Hendra Gunawan")
                            .sentimentScore(new BigDecimal("78.40"))
                            .build(),
                    CallMonitoring.builder()
                            .callId("CALL-006")
                            .callTimestamp(OffsetDateTime.of(2026, 6, 10, 8, 30, 0, 0, ZoneOffset.UTC))
                            .csName("Diana Putri")
                            .customerName("Agus Setiawan")
                            .sentimentScore(new BigDecimal("45.00"))
                            .build(),
                    CallMonitoring.builder()
                            .callId("CALL-007")
                            .callTimestamp(OffsetDateTime.of(2026, 6, 25, 13, 10, 0, 0, ZoneOffset.UTC))
                            .csName("Budi Santoso")
                            .customerName("Tari Pratiwi")
                            .sentimentScore(new BigDecimal("88.90"))
                            .build(),
                    CallMonitoring.builder()
                            .callId("CALL-008")
                            .callTimestamp(OffsetDateTime.of(2026, 7, 15, 15, 55, 0, 0, ZoneOffset.UTC))
                            .csName("Ahmad Faisal")
                            .customerName("Yusuf Pratama")
                            .sentimentScore(new BigDecimal("72.10"))
                            .build(),
                    CallMonitoring.builder()
                            .callId("CALL-009")
                            .callTimestamp(OffsetDateTime.of(2026, 8, 5, 10, 25, 0, 0, ZoneOffset.UTC))
                            .csName("Diana Putri")
                            .customerName("Nadia Larasati")
                            .sentimentScore(new BigDecimal("95.00"))
                            .build(),
                    CallMonitoring.builder()
                            .callId("CALL-010")
                            .callTimestamp(OffsetDateTime.of(2026, 8, 20, 17, 40, 0, 0, ZoneOffset.UTC))
                            .csName("Siti Aminah")
                            .customerName("Rio Putra")
                            .sentimentScore(new BigDecimal("68.50"))
                            .build()
            );

            repository.saveAll(seedData);
            System.out.println("10 Call Monitoring seed data inserted successfully!");
        }
    }
}
