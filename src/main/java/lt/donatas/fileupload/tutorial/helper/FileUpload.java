package lt.donatas.fileupload.tutorial.helper;

import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUpload {

    private Map<String, Integer> uniqueWordsAndCount = new HashMap<>();
    private Map<String, Integer> agPart = new HashMap<>();
    private Map<String, Integer> hnPart = new HashMap<>();
    private Map<String, Integer> ouPart = new HashMap<>();
    private Map<String, Integer> vzPart = new HashMap<>();

    public Map<String, Integer> getUniqueWordsAndCount() {
        return uniqueWordsAndCount;
    }

    public void setUniqueWordsAndCount(Map<String, Integer> uniqueWordsAndCount) {
        this.uniqueWordsAndCount = uniqueWordsAndCount;
    }

    public Map<String, Integer> getAgPart() {
        return agPart;
    }

    public void setAgPart(Map<String, Integer> agPart) {
        this.agPart = agPart;
    }

    public Map<String, Integer> getHnPart() {
        return hnPart;
    }

    public void setHnPart(Map<String, Integer> hnPart) {
        this.hnPart = hnPart;
    }

    public Map<String, Integer> getOuPart() {
        return ouPart;
    }

    public void setOuPart(Map<String, Integer> ouPart) {
        this.ouPart = ouPart;
    }

    public Map<String, Integer> getVzPart() {
        return vzPart;
    }

    public void setVzPart(Map<String, Integer> vzPart) {
        this.vzPart = vzPart;
    }

    public void splitWordsFromFilesAndWriteToNewFiles(MultipartFile[] files) throws IOException {
        splitWords(files);
        fillParts();
        writeToFiles();
    }

    private void splitWords(MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            String content = new String(file.getBytes());
            String[] words = content.split("\\s+");

            for (String word : words) {
                if (uniqueWordsAndCount.containsKey(word)) { //If word is in our map already, increase count
                    uniqueWordsAndCount.put(word, uniqueWordsAndCount.get(word) + 1);
                } else {  //If not in our map, add it and set count to 1
                    uniqueWordsAndCount.put(word, 1);
                }
            }
        }
    }

    private void fillParts() {
        for (Map.Entry<String, Integer> entry : uniqueWordsAndCount.entrySet()) {
            String key = entry.getKey();
            //      1.3.1. žodžiai prasidedantys raide A-G;
            if (key.toLowerCase().matches("^[a-g].*$")) {
                agPart.put(key, entry.getValue());
            }
            //      1.3.2. žodžiai prasidedantys raide H-N;
            else if (key.toLowerCase().matches("^[h-n].*$")) {
                hnPart.put(key, entry.getValue());
            }
            //      1.3.3. žodžiai prasidedantys raide O-U;
            else if (key.toLowerCase().matches("^[o-u].*$")) {
                ouPart.put(key, entry.getValue());
            }
            //      1.3.4. žodžiai prasidedantys raide V-Z.
            else if (key.toLowerCase().matches("^[v-z].*$")) {
                vzPart.put(key, entry.getValue());
            }
        }
    }

    private void writeToFiles() {
        writeToFile(agPart, "ag.txt");
        writeToFile(hnPart, "hn.txt");
        writeToFile(ouPart, "ou.txt");
        writeToFile(vzPart, "vz.txt");
    }

    private void writeToFile(Map<String, Integer> map, String fileName) {
        if (!CollectionUtils.isEmpty(map)) {
            List<String> lines = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String result = "word:" + entry.getKey() + " count:" + entry.getValue();
                lines.add(result);
            }

            Path file = Paths.get("C:\\test\\naujifailai\\" + fileName);
            try {
                Files.write(file, lines, Charset.forName("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
