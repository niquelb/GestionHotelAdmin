package utils;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;

public class StringToDateConverter {
	/**
	 * Method for converting a string to a Date object using a LocalDate method.
	 * LocalDate is better than Date, but jdbc uses Date.
	 * We get the date from the string using LocalDate and then convert that into
	 * a Date object.
	 * 
	 * !! STRING MUST FOLLOW THE FORMAT YYYY-MM-DD !!
	 * 
	 * @param s String
	 * @return Date object
	 * @throws DateTimeException if the string doesn't have a valid format
	 */
	public static Date stringToDate(String s) throws DateTimeException{
		LocalDate ld=LocalDate.parse(s);
		return new Date(Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());

	}

}
