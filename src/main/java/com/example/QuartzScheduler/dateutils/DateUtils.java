package com.example.QuartzScheduler.dateutils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";

    public static Date getUTCZonedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String sDate = simpleDateFormat.format(date);
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            return formatter.parse(sDate);
        } catch (ParseException e) {
            log.debug(e.getLocalizedMessage());
        }
        return null;
    }


}
