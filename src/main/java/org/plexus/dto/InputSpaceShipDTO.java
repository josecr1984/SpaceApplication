package org.plexus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.plexus.model.enums.SEEN;

import java.util.Date;

@Data
@NoArgsConstructor
public class InputSpaceShipDTO {

    private String name;
    private Date released;
    private SEEN seen;


}
