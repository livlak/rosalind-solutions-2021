import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> readFromFile(String fileName) throws Exception {
        var path = FileUtils.class.getResource(fileName).getPath().substring(1);
        var fullPath = Paths.get(path);
        return Files.readAllLines(fullPath);
    }

    public static List<FastaEntry> readFromFastaFile(String fileName) throws Exception {
        var lines = readFromFile(fileName);
        var list = new ArrayList<FastaEntry>();

        int i = 0;
        String id = "";
        while (i < lines.size()) {
            if (lines.get(i).charAt(0) == '>') {
                id = lines.get(i).substring(1);
                i++;
            } else {
                var sb = new StringBuilder();
                while (i < lines.size() && lines.get(i).charAt(0) != '>') {
                    sb.append(lines.get(i));
                    i++;
                }

                var fastaEntry = new FastaEntry(id, sb.toString());
                list.add(fastaEntry);
            }
        }

        return list;
    }
}
