package com.example.android.lifecycle.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StatusTracker {
    private static final StatusTracker ourInstance = new StatusTracker();
    private static final String STATUS_SUFFIX = "ed";
    private final Map<String, String> mStatusMap;
    private final List<String> mMethodList;

    private StatusTracker() {
        mStatusMap = new LinkedHashMap<>();
        mMethodList = new ArrayList<>();
    }

    public static StatusTracker getInstance() {
        return ourInstance;
    }

    List<String> getMethodList() {
        return mMethodList;
    }

    public void clear() {
        mMethodList.clear();
        mStatusMap.clear();
    }

    /**
     * Adds the status value for the given activity name into the map.
     *
     * @param activityName the name to be added to the map.
     * @param status       the status of the activity.
     */
    public void setStatus(String activityName, String status) {
        mMethodList.add(activityName + "." + status + "()");
        mStatusMap.remove(activityName);
        mStatusMap.put(activityName, status);
    }

    /**
     * Gets the status value for the given activity name.
     *
     * @param activityName the activity name to get a status for.
     * @return the status of the activity.
     */
    String getStatus(String activityName) {
        String status = mStatusMap.get(activityName);

        if (status != null) {
            status = status.substring(2);

            // String manipulation to ensure the status value is spelled correctly.
            if (status.endsWith("e")) {
                status = status.substring(0, status.length() - 1);
            }

            if (status.endsWith("p")) {
                status = status + "p";
            }
            status = status + STATUS_SUFFIX;
        }

        return status;
    }

    Set<String> keySet() {
        return mStatusMap.keySet();
    }
}
