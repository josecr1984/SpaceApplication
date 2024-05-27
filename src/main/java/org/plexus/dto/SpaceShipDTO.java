package org.plexus.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.plexus.model.enums.SEEN;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class SpaceShipDTO {

    private Integer id;
    private String name;
    private Timestamp createdDate;
    private SEEN seen;


}
