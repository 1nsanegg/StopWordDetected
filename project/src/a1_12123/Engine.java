package engine;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



public class Engine {
    Doc[] docs;
    public int loadDocs(String dirname) {
        File folder = new File(dirname);
        File[] filesInFolder = folder.listFiles();
        assert filesInFolder != null;
        docs = new Doc[filesInFolder.length];
        for (int i = 0; i < filesInFolder.length; i++) {
            File file = filesInFolder[i];
            try {
                Scanner scan = new Scanner(file);
                String title = scan.nextLine();
                String body = scan.nextLine();
                Doc d = new Doc(title + "\n" + body);
                docs[i] = d;
            } catch (FileNotFoundException e) {}
        }
        return filesInFolder.length;

    }
    public Doc[] getDocs() {
        return docs;
    }
    public List<Result> search(Query q) {
        List<Result> output = new ArrayList<Result>();
        for (Doc doc : docs) {
            List<Match> matchList = q.matchAgainst(doc);
            Result result = new Result(doc, matchList);
            if (!matchList.isEmpty()) {
                output.add(result);
            }
        }
        Collections.sort(output);
        return output;
    }
    public String htmlResult(List<Result> results) {
        StringBuilder sb = new StringBuilder();
        for (Result result : results) {
            sb.append(result.htmlHighlight());
        }
        return sb.toString();
    }
}


