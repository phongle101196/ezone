package com.ezone.specification;

import com.ezone.entity.ProductInventory;
import com.ezone.entity.ProductStatus;
import com.ezone.form.filter.ProductFilterForm;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class ProductInventorySpecification {
    private static final String STATUS = "status";
    private static final String SEARCH = "search";
    private static final String PRODUCT_ID = "productId";
    private static final String CATEGORY_ID = "categoryId";
    private static final String MANUFACTORY_ID = "manufactoryId";
    private static final String LOWEST_PRICE = "lowestPrice";
    private static final String HIGHEST_PRICE = "highestPrice";

    private static final String LOWEST_SALE_PRICE = "lowestSalePrice";
    private static final String HIGHEST_SALE_PRICE = "highestSalePrice";

    private static final String RAM = "ram";
    private static final String ROM = "rom";

    public static Specification<ProductInventory> buildWhere(ProductFilterForm form) {
        if (form == null) {
            return null;
        }

        Specification<ProductInventory> whereStatus = new CustomSpecification(STATUS, form.getStatus());
        Specification<ProductInventory> whereSearch = new CustomSpecification(SEARCH, form.getSearch());
        Specification<ProductInventory> whereProductId = new CustomSpecification(PRODUCT_ID, form.getProductId());
        Specification<ProductInventory> whereCategoryId = new CustomSpecification(CATEGORY_ID, form.getCategoryId());
        Specification<ProductInventory> whereManufactoryId = new CustomSpecification(MANUFACTORY_ID, form.getManufactoryId());
        Specification<ProductInventory> whereLowestPrice = new CustomSpecification(LOWEST_PRICE, form.getLowestPrice());
        Specification<ProductInventory> whereHighestPrice = new CustomSpecification(HIGHEST_PRICE, form.getHighestPrice());

        Specification<ProductInventory> whereLowestSalePrice = new CustomSpecification(LOWEST_SALE_PRICE, form.getLowestPrice());
        Specification<ProductInventory> whereHighestSalePrice = new CustomSpecification(HIGHEST_SALE_PRICE, form.getHighestPrice());

        Specification<ProductInventory> whereRam = new CustomSpecification(RAM, form.getRam());
        Specification<ProductInventory> whereRom = new CustomSpecification(ROM, form.getRom());


        return Specification.where(whereStatus).and(whereSearch).and(whereCategoryId).and(whereManufactoryId).and(whereProductId).and(whereHighestPrice.and(whereHighestSalePrice)).and(whereLowestPrice.and(whereLowestSalePrice)).and(whereRam).and(whereRom);
    }


    @AllArgsConstructor
    static class CustomSpecification implements Specification<ProductInventory> {
        private String key;
        private Object value;

        @Override
        public Predicate toPredicate(Root<ProductInventory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            if (value == null || value == "") {
                return null;
            }

            switch (key) {
                case STATUS:
                    return criteriaBuilder.equal(root.get("product").get("status"), ProductStatus.valueOf(value.toString()));
                case SEARCH:
                    return criteriaBuilder.like(root.get("product").get("name"), "%" + value.toString().trim() + "%");
                case CATEGORY_ID:
                    return criteriaBuilder.equal(root.get("product").get("manufactory").get("category").get("id"), Integer.parseInt(value.toString()));
                case MANUFACTORY_ID:
                    return criteriaBuilder.equal(root.get("product").get("manufactory").get("id"), Integer.parseInt(value.toString()));
                case PRODUCT_ID:
                    return criteriaBuilder.equal(root.get("product").get("id"), Integer.parseInt(value.toString()));
                case LOWEST_PRICE:
                    if (root.get("productInventorySale") != null) {
                        return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), Integer.parseInt(value.toString()));
                    } else {
                        return null;
                    }
                case HIGHEST_PRICE:
                    if (root.get("productInventorySale") != null) {
                        return criteriaBuilder.lessThanOrEqualTo(root.get("price"), Integer.parseInt(value.toString()));
                    } else {
                        return null;
                    }
                case LOWEST_SALE_PRICE:
                    if (root.get("productInventorySale") != null) {
                        return null;
                    } else {
                        return criteriaBuilder.greaterThanOrEqualTo(root.get("productInventorySale").get("salePrice"), Integer.parseInt(value.toString()));
                    }
                case HIGHEST_SALE_PRICE:
                    if (root.get("productInventorySale") != null) {
                        return null;
                    } else {
                        return criteriaBuilder.lessThanOrEqualTo(root.get("productInventorySale").get("salePrice"), Integer.parseInt(value.toString()));
                    }
                case RAM:
                    return criteriaBuilder.like(root.get("product").get("ram"), "%" + value.toString().trim() + "%");
                case ROM:
                    return criteriaBuilder.like(root.get("product").get("rom"), "%" + value.toString().trim() + "%");
                default:
                    return null;
            }
        }
    }
}
