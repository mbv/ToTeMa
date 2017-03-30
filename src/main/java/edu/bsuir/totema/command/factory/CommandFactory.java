package edu.bsuir.totema.command.factory;

import edu.bsuir.totema.command.Command;
import edu.bsuir.totema.command.factory.service.ConvertionRateCommandFactory;
import edu.bsuir.totema.command.factory.service.EmployeeCommandFactory;
import edu.bsuir.totema.command.factory.service.ServiceCommandFactory;
import edu.bsuir.totema.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.bsuir.totema.command.resource.Constants.*;

public class CommandFactory {

    private static final Logger logger = Logger.getLogger(CommandFactory.class);

    private static final HashMap<String, ServiceCommandFactory> serviceCommandFactoriesList = new HashMap<>();

    static {

        serviceCommandFactoriesList.put(SERVICE_CONVERTION_RATE, new ConvertionRateCommandFactory());
        serviceCommandFactoriesList.put(SERVICE_EMPLOYEE, new EmployeeCommandFactory());
    }

    /**
     * Defines CommandFactory {@link ServiceCommandFactory} implementation based on
     *
     * @param path   for concrete command implementation
     * @param method for concrete command implementation
     * @return Concrete command implementation
     */
    public static Command defineCommand(String path, String method) {
        RestRequestParser resourceValues = new RestRequestParser(path);
        Command resultCommand = new UnresolvedCommand();
        if (serviceCommandFactoriesList.containsKey(resourceValues.getService())) {
            ServiceCommandFactory serviceCommandFactory = serviceCommandFactoriesList.get(resourceValues.getService());
            resultCommand = serviceCommandFactory.defineCommand(method, resourceValues.getType(), resourceValues.getId());
        } else {
            logger.warn("CommandFactory for specified service not found. (service: \"" + resourceValues.getService() + "\")");
        }

        return resultCommand;
    }

    private static class RestRequestParser {
        private String serviceListString;
        private Pattern regExAllPattern;
        private Pattern regExIdPattern;

        private String service;
        private Integer id;
        private String type;

        {
            StringBuilder result = new StringBuilder();
            result.append("(");
            boolean isFirst = true;
            for (String service : ServiceFactory.getServices()) {
                if (!isFirst) {
                    result.append("|");
                }
                result.append(service);
                isFirst = false;
            }
            result.append(")");

            serviceListString = result.toString();
            regExAllPattern = Pattern.compile("/" + serviceListString + "$");
            regExIdPattern = Pattern.compile("/" + serviceListString + "/(\\d+)$");
        }

        RestRequestParser(String pathInfo) {
            Matcher matcher;

            matcher = regExIdPattern.matcher(pathInfo);
            if (matcher.find()) {
                service = matcher.group(1);
                id = Integer.parseInt(matcher.group(2));
                type = COMMAND_TYPE_RESOURCE;
                return;
            }

            matcher = regExAllPattern.matcher(pathInfo);
            if (matcher.find()) {
                service = matcher.group(1);
                type = COMMAND_TYPE_ALL;
                return;
            }

            //throw new ServletException("Invalid URI");
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}