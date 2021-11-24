package myclass;

import java.util.List;

public class StringsJaHyphenationSplitter extends StringsFixedLengthSplitter {
    
    protected static final char CHAR_COMMA = '、';

    public static List<String> splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(String inpuString, int inputFixedLength) {

        StringsJaHyphenationSplitter stringsSplitter = new StringsJaHyphenationSplitter();
        List<String> splittedLines = stringsSplitter.splitWithLine(inpuString, inputFixedLength);
        
        return splittedLines;
    }

    // オーバーライド関数

    @Override
    protected int getFixedLengthSplitIndex(String inpuString, int beginIndex, int fixedLength) {

        int splitIndex = beginIndex + fixedLength;

        if (isOverIndex(splitIndex, inpuString)) {
            return inpuString.length();
        }

        if (isJaHyphenation(splitIndex, inpuString)) {
            splitIndex += NEXT_INDEX;
        }

        return splitIndex;
    }

    // 追加関数

    protected boolean isJaHyphenation(int splitIndex, String inpuString) {

        char splitChar = inpuString.charAt(splitIndex);

        if (splitChar == CHAR_COMMA || splitChar == CHAR_PERIOD) {
            return true;
        }

        return false;
    }
}
