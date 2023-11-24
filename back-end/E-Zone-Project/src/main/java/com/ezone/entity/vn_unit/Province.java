package com.ezone.entity.vn_unit;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provinces")
@Data
@NoArgsConstructor
public class Province {
    @Id
    @Column(length = 20, nullable = false)
    private String code;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(name = "name_en", length = 255)
    private String nameEn;

    @Column(name = "full_name", length = 255, nullable = false)
    private String fullName;

    @Column(name = "full_name_en", length = 255)
    private String fullNameEn;

    @Column(name = "code_name", length = 255)
    private String codeName;

    @ManyToOne
    @JoinColumn(name = "administrative_unit_id")
    private AdminUnit adminUnit;

    @ManyToOne
    @JoinColumn(name = "administrative_region_id")
    private AdminRegion adminRegion;

    @OneToMany(mappedBy = "province")
    private List<District> districts;
}
