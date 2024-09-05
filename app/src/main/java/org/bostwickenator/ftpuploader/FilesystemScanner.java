package org.bostwickenator.ftpuploader;

import com.github.ma1co.openmemories.framework.DateTime;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class FilesystemScanner {
    public static List<File> getRawsOnExternalStorage(Boolean todayOnly) {
        return getFilteredFileList(Environment.getExternalStorageDirectory(), todayOnly, ".arw");
    }

    public static List<File> getJpgsOnExternalStorage(Boolean todayOnly) {
        return getFilteredFileList(Environment.getExternalStorageDirectory(), todayOnly, ".jpg");
    }

    public static List<File> getVideosOnExternalStorage(Boolean todayOnly) {
        return getFilteredFileList(Environment.getExternalStorageDirectory(), todayOnly, ".mts", ".mp4");
    }

    // Method to get the filtered file list
    // Method to get the filtered file list
    private static List<File> getFilteredFileList(File directory, Boolean todayOnly, String... extensions) {
        File[] subFiles = directory.listFiles();
        List<File> filtered = new ArrayList<>();
        
        if (subFiles != null) {
            // Get current date using DateTime class
            Calendar todayCalendar = getDateTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault());
            String todayDate = dateFormat.format(todayCalendar.getTime()); // Format today's date

            for (File f : subFiles) {
                String filename = f.getName().toLowerCase();

                if (f.isFile()) {
                    boolean hasValidExtension = false;
                    for (String extension : extensions) {
                        if (filename.endsWith(extension)) {
                            hasValidExtension = true;
                            break;
                        }
                    }

                    if (hasValidExtension) {
                        if (todayOnly) {
                            // Use lastModified() to get the file's last modification date
                            Calendar fileCalendar = Calendar.getInstance();
                            fileCalendar.setTimeInMillis(f.lastModified());
                            String fileLastModifiedDate = dateFormat.format(fileCalendar.getTime()); // Format file last modified date

                            // Check if the file's last modified date matches today's date
                            if (fileLastModifiedDate.equals(todayDate)) {
                                filtered.add(f);
                            }
                        } else {
                            filtered.add(f);
                        }
                    }
                } else if (f.isDirectory()) {
                    filtered.addAll(getFilteredFileList(f, todayOnly, extensions));
                }
            }
        }
        return filtered;
    }

    public static Calendar getDateTime() {
        // Get the current time as a Calendar instance using the openmemories DateTime framework
        return com.github.ma1co.openmemories.framework.DateTime.getInstance().getCurrentTime();
    }
}
