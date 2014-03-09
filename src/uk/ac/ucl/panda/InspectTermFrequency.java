package uk.ac.ucl.panda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kensk8er on 09/03/2014.
 *
 */
public class InspectTermFrequency {
    private static List<String> queries = new ArrayList<String>(3) {{
        add("foreign"); add("minor"); add("germani");
    }};

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int fileNum = args.length;
        if (fileNum == 0) {
            System.out.println("Input file names!");
            System.exit(1);
        }

        for (int i = 0; i < fileNum; i++) {
            String fileName = args[i];
            System.out.println("file name: " + fileName + " (rank = " + (i + 1) + ")");

            GetDocTermStats getDocTermStats = new GetDocTermStats();
            HashMap termStats = getDocTermStats.GetDocLevelStats(fileName);
            Iterator iterator = termStats.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                if (queries.contains(key)) {
                    int value = (Integer) termStats.get(key);
                    System.out.println(key + " " + value);
                }
            }
            System.out.print("\n");
        }
    }
}
