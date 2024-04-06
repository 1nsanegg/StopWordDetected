package engine;

public class Match implements Comparable<Match> {
    private Word w;
    private Doc d;
    private int freq;
    private int firstIndex;

    public Match(Word w, Doc d, int freq, int firstIndex) {
        this.w = w;
        this.d = d;
        this.freq = freq;
        this.firstIndex = firstIndex;
    }

    public int getFreq() {
        return freq;
    }

    public int getFirstIndex() {
        return firstIndex;
    }
    public Word getWord() {
        return w;
    }

    public int compareTo(Match o) {
        return Integer.compare(this.getFirstIndex(),o.getFirstIndex());
    }
}
