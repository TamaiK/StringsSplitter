package myclass;

import java.util.List;

public class StringsFixedLengthSplitter extends StringsMoreSplitter {

    public static List<String> splitFixedLengthWithLineBreakCodeAndPeriod(String inpuString, int inputFixedLength) {

        StringsFixedLengthSplitter stringsSplitter = new StringsFixedLengthSplitter();
        List<String> splittedLines = stringsSplitter.splitWithLine(inpuString, inputFixedLength);
        
        return splittedLines;
    }

    // オーバーライド関数

    @Override
    protected int getFixedLengthSplitIndex(String inpuString, int beginIndex, int fixedLength) {
        return beginIndex + fixedLength;
    }
}
