package by.totema.recourse.supplier.entity.model.impl;

import by.totema.recourse.entity.model.Country;
import by.totema.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class CountrySupplier implements EntityIntegerPKSupplier<Country> {
    @Override
    public Country getValidEntityWithoutId() {
        Country country = new Country();
        country.setName("Belarus");
        country.setIsoTwoLetterCode("BY");
        country.setIsoThreeLetterCode("BEL");
        country.setIsoThreeDigitCode(112);
        country.setCurrencyName("Belarusian ruble");

        return country;
    }

    @Override
    public Country getInvalidEntity() {
        return new Country();
    }
}
