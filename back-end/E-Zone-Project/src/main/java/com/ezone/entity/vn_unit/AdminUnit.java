package com.ezone.entity.vn_unit;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "administrative_units")
@Data
@NoArgsConstructor
public class AdminUnit {
    @Id
    @Column(nullable = false)
    private int id;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "full_name_en", length = 255)
    private String fullNameEn;

    @Column(name = "short_name", length = 255)
    private String shortName;

    @Column(name = "short_name_en", length = 255)
    private String shortNameEn;

    @Column(name = "code_name", length = 255)
    private String codeName;

    @Column(name = "code_name_en", length = 255)
    private String codeNameEn;

    @OneToMany(mappedBy = "adminUnit")
    private List<Ward> wards;

    @OneToMany(mappedBy = "adminUnit")
    private List<District> districts;

    @OneToMany(mappedBy = "adminUnit")
    private List<Province> provinces;

}
