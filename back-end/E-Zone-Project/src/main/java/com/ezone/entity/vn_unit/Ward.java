package com.ezone.entity.vn_unit;

import com.ezone.entity.CustomerAddress;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wards")
@Data
@NoArgsConstructor
public class Ward {
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
    @JoinColumn(name = "district_code")
    private District district;

    @ManyToOne
    @JoinColumn(name = "administrative_unit_id")
    private AdminUnit adminUnit;

    @OneToMany(mappedBy = "ward")
    private List<CustomerAddress> customerAddresses;
}
