package com.ezone.service;

import com.ezone.entity.Manufactory;
import com.ezone.form.create.CreatingManufactoryForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingManufactoryForm;
import com.ezone.repository.IManufactoryRepository;
import com.ezone.specification.ManufactorySpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactoryService implements IManufactoryService {
    @Autowired
    private IManufactoryRepository manufactoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Manufactory> getAllManufactory(Pageable pageable, FilterForm form) {
        Specification<Manufactory> where = ManufactorySpecification.buildWhere(form);
        return manufactoryRepository.findAll(where, pageable);
    }

    @Override
    public Manufactory getManufactoryById(int manufactoryId) {
        return manufactoryRepository.findById(manufactoryId).get();
    }

    @Override
    public void createNewManufactory(CreatingManufactoryForm form) {
        TypeMap<CreatingManufactoryForm, Manufactory> typeMap = modelMapper.getTypeMap(CreatingManufactoryForm.class, Manufactory.class);
        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<CreatingManufactoryForm, Manufactory>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        Manufactory manufactory = modelMapper.map(form, Manufactory.class);
        manufactoryRepository.save(manufactory);
    }

    @Override
    public void updateManufactory(UpdatingManufactoryForm form) {
        Manufactory manufactory = modelMapper.map(form, Manufactory.class);
        manufactoryRepository.save(manufactory);
    }

    @Override
    public void deleteManufactoryById(int manufactoryId) {
        manufactoryRepository.deleteById(manufactoryId);
    }
}
