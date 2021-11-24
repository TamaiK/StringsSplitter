package myclass;

import java.util.List;

public interface IStringsSplitterExtendFixedLength extends IStringsSplitter {

    // abstract関数

    abstract List<String> split(String splitString, int fixedLength);

    abstract int getSplitIndex(String splitString, int beginIndex, int fixedLength);

    // default関数

    default int getFasterIndex(int index1, int index2) {

        if (index1 < index2) {
            return index1;
        }

        return index2;
    }
}
