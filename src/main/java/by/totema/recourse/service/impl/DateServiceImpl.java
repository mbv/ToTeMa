package by.totema.recourse.service.impl;

import by.totema.recourse.entity.dto.ErrorMessage;
import by.totema.recourse.entity.model.Date;
import by.totema.recourse.repository.DateRepository;
import by.totema.recourse.service.DateService;
import by.totema.recourse.service.exception.ServiceBadRequestException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static by.totema.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class DateServiceImpl implements DateService {

    private DateRepository dateRepository;

    public DateServiceImpl(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    @Override
    public Optional<Date> getOrCreate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(dateString);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            Optional<Integer> dateId = wrapJPACallToOptional(() -> dateRepository.getOrCreate(timestamp));
            if (!dateId.isPresent()) {
                throw new ServiceBadRequestException(new ErrorMessage("date", "Bad date"));
            } else {
                return wrapJPACallToOptional(() -> dateRepository.findOne(dateId.get()));
            }
        } catch (ParseException e) {
            throw new ServiceBadRequestException(new ErrorMessage("date", "Bad date"));
        }
    }
}
