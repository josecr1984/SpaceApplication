package org.plexus.model.enums;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SPACESHIPS")
public class SpaceShip {

    @NonNull
    @Id
    private Integer id;
    //@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internalId;
    @NonNull
    private String name;
    @NonNull
    private Timestamp createdDate;
    @NonNull
    private SEEN seen;
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
}
