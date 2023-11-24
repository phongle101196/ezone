package com.ezone.entity.vn_unit;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "administrative_regions")
@Data
@NoArgsConstructor
public class AdminRegion {
    @Id
    @Column(nullable = false)
    private int id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(name = "name_en", length = 255)
    private String nameEn;

    @Column(name = "code_name", length = 255)
    private String codeName;

    @Column(name = "code_name_en", length = 255)
    private String codeNameEn;

    @OneToMany(mappedBy = "adminRegion")
    List<Province> provinces;
}
