package com.ezone.specification;

import com.ezone.entity.*;
import com.ezone.form.filter.FilterForm;
import com.ezone.form.filter.ProductFilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class ProductSpecification {
    private static final String STATUS = "status";
    private static final String SEARCH = "search";
    private static final String CATEGORY_ID = "categoryId";
    private static final String MANUFACTORY_ID = "manufactoryId";
    private static final String RAM = "ram";
    private static final String ROM = "rom";


    public static Specification<Product> buildWhere(ProductFilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<Product> whereStatus = new CustomSpecification(STATUS, form.getStatus());
        Specification<Product> whereSearch = new CustomSpecification(SEARCH, form.getSearch());
        Specification<Product> whereCategoryId = new CustomSpecification(CATEGORY_ID, form.getCategoryId());
        Specification<Product> whereManufactoryId = new CustomSpecification(MANUFACTORY_ID, form.getManufactoryId());
        Specification<Product> whereRam = new CustomSpecification(RAM, form.getRam());
        Specification<Product> whereRom = new CustomSpecification(ROM, form.getRom());

        return Specification.where(whereStatus).and(whereSearch).and(whereCategoryId).and(whereManufactoryId).and(whereRam).and(whereRom);
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<Product> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case STATUS:
                    return criteriaBuilder.equal(root.get("status"), ProductStatus.valueOf(value.toString()));
                case SEARCH:
                    return criteriaBuilder.like(root.get("name"), "%" + value.toString().trim() + "%");
                case CATEGORY_ID:
                    return criteriaBuilder.equal(root.get("manufactory").get("category").get("id"), Integer.parseInt(value.toString()));
                case MANUFACTORY_ID:
                    return criteriaBuilder.equal(root.get("manufactory").get("id"), Integer.parseInt(value.toString()));
                case RAM:
                    return criteriaBuilder.like(root.get("ram"), "%" + value.toString().trim() + "%");
                case ROM:
                    return criteriaBuilder.like(root.get("rom"), "%" + value.toString().trim() + "%");
                default:
                    return null;
            }
        }
    }
}
