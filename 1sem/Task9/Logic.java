import java.util.*;

public class Logic {
    public static List<Integer> createNewList(List<Integer> list)
    {
        List<Integer> sortedList = sort(list);
        int startIndex = 0, endIndex = 0, tempLength = 1;
        for(int i = 0; i < sortedList.size() - 1; i++){
            if (sortedList.get(i) == sortedList.get(i + 1) - 1) {
                tempLength++;
            }
            else if (sortedList.get(i).equals(sortedList.get(i + 1))) continue;
            else if (tempLength > endIndex - startIndex) {
                endIndex = i + 1;
                startIndex = Math.max(0, endIndex - tempLength);
                tempLength = 1;
            }
            else tempLength = 1;
        }
        if (tempLength > endIndex - startIndex) {
            endIndex = sortedList.size();
            startIndex = endIndex - tempLength;
        }
        if (endIndex == 0) endIndex = sortedList.size() - 1;
        return sortedList.subList(startIndex, endIndex);
    }

    public static ArrayList<Integer> sort(List<Integer> passedList) {
        return new ArrayList<>(new TreeSet<>(passedList));
    }
}
