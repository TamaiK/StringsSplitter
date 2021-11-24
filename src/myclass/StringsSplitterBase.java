package myclass;

import java.util.ArrayList;
import java.util.List;

public class StringsSplitterBase {

    protected static final int READ_ENDED = -1;
    protected static final int MISSING_INDEX = -1;
    protected static final int NEXT_INDEX = 1;
    protected static final int UNLIMITED_LENGTH = Integer.MAX_VALUE;

    protected static final String STRING_BLANK = "";

    protected final List<String> splitWithLine(String inpuString) {
        return splitWithLine(inpuString, UNLIMITED_LENGTH);
    }
    
    protected final List<String> splitWithLine(String inpuString, int fixedLength) {

        List<String> splittedLines = new ArrayList<>();

        int beginIndex = 0;
        int endIndex = 0;
        while (!isReadEnd(beginIndex)) {

            endIndex = getSplitIndex(inpuString, beginIndex, fixedLength);

            String splitLine = getSplitLine(inpuString, beginIndex, endIndex);
            splittedLines.add(splitLine);

            beginIndex = getNextStartIndex(endIndex, inpuString);
        }

        return splittedLines;
    }

    protected final boolean isReadEnd(int splitIndex) {
        return splitIndex <= READ_ENDED;
    }

    protected final int getSplitIndex(String inpuString, int beginIndex, int fixedLength) {

        if (isOverIndex(beginIndex, inpuString)) {
            return READ_ENDED;
        }

        int charSplitIndex = getSplitCharIndex(inpuString, beginIndex);
        int fixedLengthSplitIndex = getFixedLengthSplitIndex(inpuString, beginIndex, fixedLength);

        int splitIndex = getFasterIndex(charSplitIndex, fixedLengthSplitIndex);

        return splitIndex;
    }

    protected final String getSplitLine(String inpuString, int beginIndex, int endIndex) {

        if (endIndex == READ_ENDED) {
            return STRING_BLANK;
        }

        return inpuString.substring(beginIndex, endIndex);
    }

    protected final boolean isOverIndex(int index, String inpuString) {
        return index >= inpuString.length();
    }

    protected final char getChar(String inpuString, int endIndex) {
        return inpuString.charAt(endIndex);
    }

    protected final int getCharIndex(String inpuString, char splitChar, int index) {
        
        int splitIndex = inpuString.indexOf(splitChar, index);
        if (splitIndex == MISSING_INDEX) {
            splitIndex = inpuString.length();
        }
        
        return splitIndex;
    }

    protected final int getFasterIndex(int index1, int index2) {

        if (index1 < index2) {
            return index1;
        }

        return index2;
    }

    //

    protected int getSplitCharIndex(String inpuString, int beginIndex){

        return inpuString.length();
    };

    protected int getFixedLengthSplitIndex(String inpuString, int beginIndex, int fixedLength){

        return inpuString.length();
    }

    protected int getNextStartIndex(int endIndex, String inpuString) {

        return READ_ENDED;
    }
}
