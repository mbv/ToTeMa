package by.totema.recourse.supplier.entity.model.impl;

import by.totema.recourse.entity.model.ConversionRate;
import by.totema.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class ConversionRateSupplier implements EntityIntegerPKSupplier<ConversionRate> {
    private CountrySupplier countrySupplier = new CountrySupplier();
    private DateSupplier dateSupplier = new DateSupplier();
    @Override
    public ConversionRate getValidEntityWithoutId() {
        ConversionRate conversionRate = new ConversionRate();

        conversionRate.setConversionToLocal(187);
        conversionRate.setCountry(countrySupplier.getValidEntityWithoutId());
        conversionRate.setPeriod(dateSupplier.getValidEntityWithoutId());

        return conversionRate;
    }

    @Override
    public ConversionRate getInvalidEntity() {
        return new ConversionRate();
    }
}
