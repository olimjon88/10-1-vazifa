package uz.pdp.vazifa1.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter

public class RoomDto implements Serializable {

    private String number;

    private Short floor;

    private Short size;

    private Integer hotelId;
}
