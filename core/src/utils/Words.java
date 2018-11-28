package utils;

import java.util.ArrayList;
import java.util.List;

public class Words {
    public static List<String> fullJustify(String[] words, int L) {
        List<String> list = new ArrayList<String>();
        int size = words.length;
        int currentLength = 0;
        String tmp = "";

        //list = "This is an ", "example of text ", "justification. "
        for (int i = 0; i < size; i++) {
            currentLength += words[i].length() + 1;
            if (currentLength > L + 1) {
                list.add(tmp);
                tmp = "";
                currentLength = 0;
                --i;
            }
            else {
                tmp += words[i] + " ";
            }
        }
        //the last word. "justificaion. "
        if (!tmp.equals(""))
            list.add(tmp);


        for (int i = 0; i < list.size() - 1; i++) {
            //i = 1, tmp = "example of text "
            tmp = list.get(i);
            String [] tmpStrArray = tmp.split(" ");
            int totoalLength = 0;
            for (int j = 0; j < tmpStrArray.length; ++j) {
                totoalLength += tmpStrArray[j].length();
            }

            //totoalLength = 13, L = 16, numOfString = 3, freeSpace = 16 - 13 = 3.
            //spaceCount = {2, 1, 0}
            int[] spaceCount = getSpaceCount(L - totoalLength, tmpStrArray.length);
            tmp = "";
            for (int j = 0; j < tmpStrArray.length; j++) {
                tmp += tmpStrArray[j];
                for (int k = 0; k < spaceCount[j]; ++k) {
                    tmp += " ";
                }
            }

            //tmp = "example  of text"
            list.set(i, tmp);
        }

        //last word. no extra space between words.
        tmp = list.get(list.size() - 1);
        if (tmp.length() < L) {
            while (tmp.length() < L) {
                tmp += " ";
            }
        }
        else if (tmp.length() > L) {
            tmp = tmp.substring(0, L);
        }
        list.set(list.size() - 1, tmp);
        return list;
    }

    public static int[] getSpaceCount(int freeSpace, int numOfString) {
        int size = numOfString - 1;
        int[] ret = new int[size + 1];
        if (size == 0) {
            ret [0] = freeSpace;
        }
        else {
            for (int i = 0; i < ret.length - 1; i++) {
                ret[i] = freeSpace % size == 0 ? freeSpace / size : freeSpace / size + 1;
                freeSpace -= ret[i];
                --size;
            }
        }
        return ret;
    }
}
