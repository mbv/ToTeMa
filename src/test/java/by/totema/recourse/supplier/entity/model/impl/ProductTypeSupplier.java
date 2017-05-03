package by.totema.recourse.supplier.entity.model.impl;

import by.totema.recourse.entity.model.ProductType;
import by.totema.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class ProductTypeSupplier implements EntityIntegerPKSupplier<ProductType> {
    @Override
    public ProductType getValidEntityWithoutId() {
        ProductType productType = new ProductType();

        productType.setGender("m");
        productType.setAge("adult");
        productType.setSeason("summer");
        productType.setType("t-shirt");

        return productType;
    }

    @Override
    public ProductType getInvalidEntity() {
        return new ProductType();
    }
}
