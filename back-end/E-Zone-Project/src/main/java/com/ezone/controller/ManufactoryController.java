package com.ezone.controller;

import com.ezone.dto.ManufactoryDTO;
import com.ezone.entity.Manufactory;
import com.ezone.form.create.CreatingManufactoryForm;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.update.UpdatingManufactoryForm;
import com.ezone.service.IManufactoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/manufactories")
@CrossOrigin("*")
public class ManufactoryController {
    @Autowired
    private IManufactoryService manufactoryService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<ManufactoryDTO> getAllManufactory(Pageable pageable, FilterForm form) {
        Page<Manufactory> manufactoryPage = manufactoryService.getAllManufactory(pageable, form);
        List<Manufactory> manufactories = manufactoryPage.getContent();
        List<ManufactoryDTO> manufactoryDTOS = modelMapper.map(manufactories, new TypeToken<List<ManufactoryDTO>>() {
        }.getType());
        return new PageImpl<>(manufactoryDTOS, pageable, manufactoryPage.getTotalElements());
    }

    @GetMapping(value = "/{manufactoryId}")
    public ManufactoryDTO getManufactoryById(@PathVariable(name = "manufactoryId") int manufactoryId) {
        return modelMapper.map(manufactoryService.getManufactoryById(manufactoryId), ManufactoryDTO.class);
    }

    @PostMapping
    public void createNewManufactory(@RequestBody CreatingManufactoryForm form) {
        manufactoryService.createNewManufactory(form);
    }

    @PutMapping(value = "/{manufactoryId}")
    public void updateManufactory(@PathVariable(name = "manufactoryId") int manufactoryId, @RequestBody UpdatingManufactoryForm form) {
        form.setId(manufactoryId);
        manufactoryService.updateManufactory(form);
    }

    @DeleteMapping(value = "/{manufactoryId}")
    void deleteManufactoryById(@PathVariable(name = "manufactoryId") int manufactoryId) {
        manufactoryService.deleteManufactoryById(manufactoryId);
    }
}
