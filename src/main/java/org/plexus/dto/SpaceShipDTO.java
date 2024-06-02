package org.plexus.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class SpaceShipDTO extends InputSpaceShipDTO{

    private Integer id;
    private Timestamp createdAt;

}
