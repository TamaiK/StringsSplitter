package myclass;

import java.util.ArrayList;
import java.util.List;

public class StringsSplitter implements IStringsSplitter {

    @Override
    public List<String> split(String splitString) {

        List<String> splittedLines = new ArrayList<>();

        int beginIndex = 0;
        int endIndex = 0;
        while (!IStringsSplitter.isReadEnd(beginIndex)) {

            endIndex = getSplitIndex(splitString, beginIndex);

            String splitLine = getSplitLine(splitString, beginIndex, endIndex);
            splittedLines.add(splitLine);

            beginIndex = getNextStartIndex(endIndex, splitString);
        }

        return splittedLines;
    }

    @Override
    public int getSplitIndex(String splitString, int beginIndex) {

        if (isOverIndex(beginIndex, splitString)) {
            return READ_ENDED;
        }

        return getCharIndex(splitString, CHAR_BREAKCODE, beginIndex);
    }

    @Override
    public int getNextStartIndex(int endIndex, String splitString) {

        if (IStringsSplitter.isReadEnd(endIndex)) {
            return READ_ENDED;
        }

        if (isOverIndex(endIndex, splitString)) {
            return READ_ENDED;
        }

        int nextStart = endIndex + NEXT_INDEX;
        return nextStart;
    }

    @Override
    public String getSplitLine(String splitString, int beginIndex, int endIndex) {

        if (endIndex == READ_ENDED) {
            return STRING_BLANK;
        }

        return splitString.substring(beginIndex, endIndex);
    }

    @Override
    public boolean isOverIndex(int index, String splitString) {
        return index >= splitString.length();
    }

    @Override
    public char getChar(String splitString, int endIndex) {
        return splitString.charAt(endIndex);
    }

    @Override
    public int getCharIndex(String splitString, char splitChar, int index) {

        int splitIndex = splitString.indexOf(splitChar, index);
        if (splitIndex == MISSING_INDEX) {
            splitIndex = splitString.length();
        }

        return splitIndex;
    }
}
