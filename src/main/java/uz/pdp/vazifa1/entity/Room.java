package uz.pdp.vazifa1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private String number;

    @Column
    private short floor;

    @Column
    private short size;

    @ManyToOne(optional = false)
    private Hotel hotel;

}
