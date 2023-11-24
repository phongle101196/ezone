package com.ezone.service;

import com.ezone.entity.Manufactory;
import com.ezone.form.create.CreatingManufactoryForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingManufactoryForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IManufactoryService {
    Page<Manufactory> getAllManufactory(Pageable pageable, FilterForm form);

    Manufactory getManufactoryById(int manufactoryId);

    void createNewManufactory(CreatingManufactoryForm form);

    void updateManufactory(UpdatingManufactoryForm form);

    void deleteManufactoryById(int manufactoryId);
}
