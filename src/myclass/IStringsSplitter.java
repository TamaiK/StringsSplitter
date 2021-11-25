package myclass;

import java.util.List;

public interface IStringsSplitter {

    public static final int READ_ENDED = -1;
    public static final int MISSING_INDEX = -1;
    public static final int NEXT_INDEX = 1;

    public static final String STRING_BLANK = "";

    public static final char CHAR_BREAKCODE = '\n';
    public static final char CHAR_PERIOD = '。';

    // static関数

    public static boolean isReadEnd(int splitIndex) {
        return splitIndex <= READ_ENDED;
    }

    // 関数

    List<String> split(String splitString);

    int getSplitIndex(String splitString, int beginIndex);

    int getNextStartIndex(int endIndex, String splitString);

    String getSplitLine(String splitString, int beginIndex, int endIndex);

    boolean isOverIndex(int index, String splitString);

    char getChar(String splitString, int endIndex);

    int getCharIndex(String splitString, char splitChar, int index);
}
