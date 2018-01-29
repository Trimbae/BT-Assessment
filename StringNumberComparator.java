//This idea/code came from this post on stack overflow about ordering lists of strings by the number they start with,
//we create a new comparator to perfrom comparison in the way we want.
//https://stackoverflow.com/questions/34571281/how-do-you-numerically-sort-an-arraylist-with-intstring-entries
import java.util.*;

public class StringNumberComparator implements Comparator<String> {
    // overides the compare method in Comparator<String>
    @Override
    public int compare (String s1, String s2) {
        //cuts the second number out of each string and converts it to a long
        long i1 = Long.parseLong(s1.split(" ")[1]);
        long i2 = Long.parseLong(s2.split(" ")[1]);
        //returns int based on which is bigger 
        int cmp = Long.compare(i1, i2);
        if (cmp != 0) {
            return cmp;
        }
        return s1.compareTo(s2);
    }
}