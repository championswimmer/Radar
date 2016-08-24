package airtel.comviva.mahindra.radar.db;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by championswimmer on 24/08/16.
 */
public class DbUtils {
    public static final String TAG = "DbUtils";

    private static String LIST_SEPARATOR = "__,__";

    public static String convertIntListToString(List<Integer> intList) {
        StringBuilder stringBuffer = new StringBuilder();
        for (Integer str : intList) {
            stringBuffer.append(str).append(LIST_SEPARATOR);
        }

        // Remove last separator
        int lastIndex = stringBuffer.lastIndexOf(LIST_SEPARATOR);
        stringBuffer.delete(lastIndex, lastIndex + LIST_SEPARATOR.length() + 1);

        return stringBuffer.toString();
    }

    public static List<Integer> convertStringToIntList(String str) {
        if (str == null) {
            str = "0";
        }
        String[] strList = (str.split(LIST_SEPARATOR));
        List<Integer> intList = new ArrayList<Integer>(strList.length);
        for (String s : strList) {
            Log.d(TAG, "convertStringToIntList: " + s);
            intList.add(Integer.valueOf(s));
        }
        return intList;
    }

    public static String convertLongListToString(List<Long> longList) {
        StringBuilder stringBuffer = new StringBuilder();
        for (Long str : longList) {
            stringBuffer.append(str).append(LIST_SEPARATOR);
        }

        // Remove last separator
        int lastIndex = stringBuffer.lastIndexOf(LIST_SEPARATOR);
        stringBuffer.delete(lastIndex, lastIndex + LIST_SEPARATOR.length() + 1);

        return stringBuffer.toString();
    }

    public static List<Long> convertStringToLongList(String str) {
        if (str == null) {
            str = "0";
        }
        String[] strList = (str.split(LIST_SEPARATOR));
        List<Long> longList = new ArrayList<Long>(strList.length);
        for (String s : strList) {
            Log.d(TAG, "convertStringToIntList: " + s);
            longList.add(Long.valueOf(s));
        }
        return longList;
    }

    public static String convertStrListToString(List<String> stringList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : stringList) {
            stringBuffer.append(str).append(LIST_SEPARATOR);
        }

        // Remove last separator
        int lastIndex = stringBuffer.lastIndexOf(LIST_SEPARATOR);
        stringBuffer.delete(lastIndex, lastIndex + LIST_SEPARATOR.length() + 1);

        return stringBuffer.toString();
    }

    public static List<String> convertStringToStrList(String str) {
        if (str == null) {
            str = "";
        }
        return Arrays.asList(str.split(LIST_SEPARATOR));
    }
}
