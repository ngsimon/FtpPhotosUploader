package org.bostwickenator.ftpuploader;

import com.github.ma1co.openmemories.framework.DateTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

class DateUtils {

    /**
     * Get the current date in a standard human-readable, filesystem-safe format.
     *
     * @return the date string
     */
    public static String getDate() {
        Calendar calendar = getDateTime(); // Use the newly defined method
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    public static Calendar getDateTime() {
        // Get the current time as a Calendar instance using the openmemories DateTime framework
        return com.github.ma1co.openmemories.framework.DateTime.getInstance().getCurrentTime();
    }
    
}
