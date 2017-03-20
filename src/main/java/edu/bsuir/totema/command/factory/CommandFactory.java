package edu.bsuir.totema.command.factory;

import edu.bsuir.totema.command.factory.service.EmployeeCommandFactory;
import edu.bsuir.totema.command.factory.service.ServiceCommandFactory;
import edu.bsuir.totema.command.factory.service.UnresolvedCommand;
import edu.bsuir.totema.command.factory.service.UnresolvedServiceCommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;

import static edu.bsuir.totema.command.resource.Constants.SERVICE_EMPLOYEE;
import static edu.bsuir.totema.util.NullUtil.isNull;
import static edu.bsuir.totema.util.StringUtil.isNullOrEmpty;

public class CommandFactory {

    private static final Logger logger = Logger.getLogger(CommandFactory.class);

    private static final HashMap<String, ServiceCommandFactory> serviceCommandFactoriesList = new HashMap<>();
    static {

        serviceCommandFactoriesList.put(SERVICE_EMPLOYEE, new EmployeeCommandFactory());
    }

    /**
     * Defines CommandFactory {@link ServiceCommandFactory} implementation based on
     *
     * @param service for concrete command implementation
     * @return Concrete command implementation
     */
    public static ServiceCommandFactory defineCommand(String service) {
        ServiceCommandFactory resultServiceCommandFactory = new UnresolvedServiceCommandFactory();
        if (serviceCommandFactoriesList.containsKey(service)) {
            try {
                resultServiceCommandFactory = serviceCommandFactoriesList.get(service);
            } catch (IllegalArgumentException e) {
                logger.warn("Command with specified name not found. (name:)");
            }
        }

        return resultServiceCommandFactory;
    }

}