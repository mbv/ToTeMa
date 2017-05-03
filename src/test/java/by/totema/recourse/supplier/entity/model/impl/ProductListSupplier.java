package by.totema.recourse.supplier.entity.model.impl;

import by.totema.recourse.entity.model.ProductList;
import by.totema.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class ProductListSupplier implements EntityIntegerPKSupplier<ProductList> {
    private ProductSupplier productSupplier = new ProductSupplier();
    @Override
    public ProductList getValidEntityWithoutId() {
        ProductList productList = new ProductList();

        productList.setGrossMargin(50);
        productList.setOrderKey(1);
        productList.setProduct(productSupplier.getValidEntityWithoutId());
        productList.setQuantity(5);
        productList.setUnitCost(100);
        productList.setUnitPrice(150);

        return productList;
    }

    @Override
    public ProductList getInvalidEntity() {
        return new ProductList();
    }
}
