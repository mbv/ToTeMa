package edu.bsuir.totema.command.factory.service;

import edu.bsuir.totema.service.Service;
import edu.bsuir.totema.service.factory.ServiceFactory;

public class ConvertionRateCommandFactory extends ServiceCommandFactory {
    @Override
    Service getService() {
        return ServiceFactory.getInstance().getConvertionRateService();
    }
}
