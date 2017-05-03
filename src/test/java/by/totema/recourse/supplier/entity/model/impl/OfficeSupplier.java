package by.totema.recourse.supplier.entity.model.impl;

import by.totema.recourse.entity.model.Office;
import by.totema.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class OfficeSupplier implements EntityIntegerPKSupplier<Office> {
    private CountrySupplier countrySupplier = new CountrySupplier();
    @Override
    public Office getValidEntityWithoutId() {
        Office office = new Office();

        office.setAddress("bla bla");
        office.setCity("Minsk");
        office.setCountry(countrySupplier.getValidEntityWithoutId());
        office.setFax("1982323");
        office.setPhone("1982323");
        office.setPostalCode("22045");

        return office;
    }

    @Override
    public Office getInvalidEntity() {
        return new Office();
    }
}
