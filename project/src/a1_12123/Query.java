package engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static engine.Word.createWord;


public class Query {
    String searchPhrase;
    List<Word> searchWords = new ArrayList<Word>();
    public Query(String searchPhrase) {
        this.searchPhrase = searchPhrase;
        String[] listOfSearchWord = searchPhrase.split("\\s+");
        for (String word : listOfSearchWord) {
            Word searchWord = createWord(word);
            if (searchWord.isKeyword()) {
                searchWords.add(searchWord);
            }
        }
    }

    public List<Word> getKeywords() {
        return searchWords;
    }

    public List<Match> matchAgainst(Doc d) {
        List<Match> listOfMatch = new ArrayList<Match>();
        List<Word> listWordInDoc = new ArrayList<Word>();
        listWordInDoc.addAll(d.getTitle());
        listWordInDoc.addAll(d.getBody());
        for (Word searchWord : searchWords) {
            int firstIndex = -1;
            int freq = 0;

            for (int i = 0; i <listWordInDoc.size(); i++) {
                if(searchWord.equals(listWordInDoc.get(i)) ) {
                    freq++;
                    if (firstIndex == -1 ) {
                        firstIndex = i;

                    }
                }
            }
            if (freq > 0) {
            Match match = new Match(searchWord,d,freq,firstIndex);
            listOfMatch.add(match);
            }
        }
        Collections.sort(listOfMatch);
        return listOfMatch;
    }


}
