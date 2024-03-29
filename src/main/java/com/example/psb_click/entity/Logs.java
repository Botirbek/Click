package com.example.psb_click.entity;

import com.example.psb_click.enums.LogType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String traceId;

    private UUID logId;

    @Enumerated(value = EnumType.STRING)
    private LogType type;

    @JdbcTypeCode(SqlTypes.JSON)
    private String body;

    private String method;

    private String path;

    private LocalDateTime time;

}
