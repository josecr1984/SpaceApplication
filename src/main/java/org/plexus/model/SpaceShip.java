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

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "space_gen")
    @TableGenerator(
            name = "space_gen",
            table = "hibernate_sequences",
            pkColumnName = "space_gen_seq",
            valueColumnName = "next_val",
            pkColumnValue = "default",
            initialValue = 1000,
            allocationSize = 1
    )
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Date released;
    @NonNull
    private SEEN seen;
    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;
}
