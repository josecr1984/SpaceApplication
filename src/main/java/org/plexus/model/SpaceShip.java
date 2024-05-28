package org.plexus.model;

import jakarta.persistence.*;
import lombok.*;
import org.plexus.model.enums.SEEN;

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
    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
}
