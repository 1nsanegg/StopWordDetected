package engine;

import java.util.ArrayList;
import java.util.List;

import static engine.Word.createWord;


public class Doc {
    String content;
    List<Word> wordsOfTitle = new ArrayList<Word>();
    List<Word> wordsOfBody = new ArrayList<Word>();

    public Doc(String content) {
        this.content = content;
        String[] context = content.split("\\n");
        String titleOfDoc = context[0];
        String bodyOfDoc = context[1];

        String[] wordsInTitle = titleOfDoc.split("\\s+");
        for (String word : wordsInTitle) {
            wordsOfTitle.add(createWord(word));
        }
        String[] wordsInBody = bodyOfDoc.split("\\s+");
        for (String word : wordsInBody) {
            wordsOfBody.add(createWord(word));
        }
    }

    public List<Word> getTitle() {
        return wordsOfTitle;
    }

    public List<Word> getBody() {
        return wordsOfBody;
    }

    public boolean equals(Object o) {
        Doc d = (Doc) o;
        if (!(this.getTitle().size() == d.getTitle().size() && this.getBody().size() == d.getBody().size())) {
            return false;
        }
        for (int i = 0; i < this.getTitle().size() ; i++) {
            if (!(this.getTitle().get(i).equals(d.getTitle().get(i)) && this.getBody().get(i).equals(d.getBody().get(i)))) {
                return false;
            }
        }
        return true;
    }
}
