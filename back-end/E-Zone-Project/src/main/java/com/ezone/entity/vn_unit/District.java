package com.ezone.entity.vn_unit;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "districts")
@Data
@NoArgsConstructor
public class District {
    @Id
    @Column(length = 20, nullable = false)
    private String code;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(name = "name_en", length = 255)
    private String nameEn;

    @Column(name = "full_name", length = 255)
    private String fullName;

    @Column(name = "full_name_en", length = 255)
    private String fullNameEn;

    @Column(name = "code_name", length = 255)
    private String codeName;

    @ManyToOne
    @JoinColumn(name = "province_code")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "administrative_unit_id")
    private AdminUnit adminUnit;

    @OneToMany(mappedBy = "district")
    private List<Ward> wards;
}
