package by.totema.recourse.supplier.entity.model.impl;

import by.totema.recourse.entity.model.Date;
import by.totema.recourse.supplier.entity.model.EntityIntegerPKSupplier;

import java.sql.Timestamp;

public class DateSupplier implements EntityIntegerPKSupplier<Date> {
    @Override
    public Date getValidEntityWithoutId() {
        Date date = new Date();

        date.setDay(10);
        date.setMonthInt(1);
        date.setMonthName("January");
        date.setQuarter(1);
        date.setTimeStamp(new Timestamp(1484064973));
        date.setWeek(2);
        date.setYear(2017);

        return date;
    }

    @Override
    public Date getInvalidEntity() {
        return new Date();
    }
}
