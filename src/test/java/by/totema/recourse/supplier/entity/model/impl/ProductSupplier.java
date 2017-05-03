package by.totema.recourse.supplier.entity.model.impl;

import by.totema.recourse.entity.model.Product;
import by.totema.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class ProductSupplier implements EntityIntegerPKSupplier<Product> {
    private ProductTypeSupplier productTypeSupplier = new ProductTypeSupplier();
    @Override
    public Product getValidEntityWithoutId() {
        Product product = new Product();

        product.setCode(12123);
        product.setColor("red");
        product.setName("red summer t-shirt");
        product.setSize("M");
        product.setType(productTypeSupplier.getValidEntityWithoutId());

        return product;
    }

    @Override
    public Product getInvalidEntity() {
        return new Product();
    }
}
