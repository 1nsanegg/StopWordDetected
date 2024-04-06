package engine;

import java.util.List;

public class Result implements Comparable<Result> {

    private Doc doc;
    private List<Match> matches;
    List<Match> matchFound;

    public Result (Doc d, List<Match> matches) {
        this.doc = d;
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;

    }
    public Doc getDoc() {
        return doc;
    }
    public int getTotalFrequency() {
        int totalFreq = 0;
        for (int i = 0; i < matches.size() ; i++) {
            totalFreq += matches.get(i).getFreq();
        }
        return totalFreq;
    }
    public double getAverageFirstIndex() {
        double firstIndexes = 0;
        for (Match match : matches) {
            firstIndexes += match.getFirstIndex();
        }
        return firstIndexes/matches.size();
    }

    public String htmlHighlight() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h3>");
        for (int i = 0; i <doc.getTitle().size() ; i++) {
            Word titleWord = doc.getTitle().get(i);
            boolean matchingWord = false;

            for (Match match : matches) {
                if (titleWord.equals(match.getWord())) {
                    matchingWord = true;
                    break;
                }
            }

            if (matchingWord) {
                sb.append(titleWord.getPrefix());
                sb.append("<u>");
                sb.append(titleWord.getText());
                sb.append("</u>");
                sb.append(titleWord.getSuffix());
            } else {
                sb.append(titleWord.toString());
            }
            sb.append(" ");

        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("</h3>");

        sb.append("<p>");
        for (int i = 0; i <doc.getBody().size() ; i++) {
            Word wordOfBody = doc.getBody().get(i);
            boolean isSearchWord = false;

            for (Match match : matches) {
                if (wordOfBody.equals(match.getWord())) {
                    isSearchWord = true;
                    break;
                }
            }

            if (isSearchWord) {
                sb.append(wordOfBody.getPrefix());
                sb.append("<b>");
                sb.append(wordOfBody.getText());
                sb.append("</b>");
                sb.append(wordOfBody.getSuffix());
            } else {
                sb.append(wordOfBody.toString());
            }
            sb.append(" ");

        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("</p>");
        return sb.toString();
    }

    public int compareTo(Result o) {
        if (this.matches.size() != o.matches.size()) {
            return -Integer.compare(this.matches.size(), o.matches.size());

        } else if (this.getTotalFrequency() != o.getTotalFrequency()) {
            return -Integer.compare(this.getTotalFrequency(), o.getTotalFrequency());
        } else {
            return Double.compare(this.getAverageFirstIndex(),  o.getAverageFirstIndex());
        }
    }
}
