package com.cfs.bookMyShow.model;

import com.cfs.bookMyShow.model.type.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private  String name;

    @Column(nullable = false)
    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(
            name = "city_id"
    )
    private  City city;

    @OneToMany(
            mappedBy = "theater"  ,
            orphanRemoval = true ,
            cascade = CascadeType.ALL
    )
    private List<Screen > screen  = new ArrayList<>();
}
