package myclass;

import java.util.List;

public interface IStringsSplitterExtendFixedLength extends IStringsSplitter {

    public static final char CHAR_COMMA = '、';

    List<String> split(String splitString, int fixedLength);

    int getSplitIndex(String splitString, int beginIndex, int fixedLength);

    int getSplitCharIndex(String splitString, int beginIndex);

    int getFixedLengthSplitIndex(String splitString, int beginIndex, int fixedLength);

    int getFasterIndex(int index1, int index2);

    boolean isFasterBreakCode(int nextBreakCode, int nextPeriod);

    boolean isPeriodBreak(int endIndex, String splitString);
}
