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

    //@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internalId;
    @NonNull
    @Id
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Timestamp createdDate;
    @NonNull
    private SEEN seen;
    @CreatedDate
    @Column(name = "created")
    private Date created;
}
