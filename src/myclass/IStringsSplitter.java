package myclass;

import java.util.List;

public interface IStringsSplitter {

    public static final int READ_ENDED = -1;
    public static final int MISSING_INDEX = -1;
    public static final int NEXT_INDEX = 1;

    public static final String STRING_BLANK = "";

    // static関数

    public static boolean isReadEnd(int splitIndex) {
        return splitIndex <= READ_ENDED;
    }

    // abstract関数

    abstract List<String> split(String splitString);

    abstract int getSplitIndex(String splitString, int beginIndex);

    abstract int getNextStartIndex(int endIndex, String splitString);

    // default関数

    default String getSplitLine(String splitString, int beginIndex, int endIndex) {

        if (endIndex == READ_ENDED) {
            return STRING_BLANK;
        }

        return splitString.substring(beginIndex, endIndex);
    }

    default boolean isOverIndex(int index, String splitString) {
        return index >= splitString.length();
    }

    default char getChar(String splitString, int endIndex) {
        return splitString.charAt(endIndex);
    }

    default int getCharIndex(String splitString, char splitChar, int index) {

        int splitIndex = splitString.indexOf(splitChar, index);
        if (splitIndex == MISSING_INDEX) {
            splitIndex = splitString.length();
        }

        return splitIndex;
    }
}
