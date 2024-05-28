package ch.hslu.ad.sw13ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Simple KMP search algorithm implementation.
 * This implementation is by no means ideal or optimally performant.
 * I tried to implement KMP search without looking up the theory or code samples and only with what I
 * still had in mind from the lecture.
 */
public final class KMP {

    private static final Logger LOG = LoggerFactory.getLogger(KMP.class);

    private int[] next;
    private int patternIndex;
    private String pattern;
    private String data;

    public KMP() {
    }

    /**
     * Search for pattern in data
     *
     * @param pattern search pattern
     * @param data    data to search through
     * @return index of first occurrence if pattern was found. Returns -1 if pattern was not found.
     */
    public static int search(final String pattern, final String data) {
        var kmp = new KMP();
        return kmp.doSearch(pattern, data);
    }

    public static String getBorder(final String s) {
        String border = "";
        for (int j = 1; j < s.length(); j++) {
            String front = s.substring(0, j);
            String back = s.substring(s.length() - j);
            if (front.equals(back)) {
                border = front;
            }
        }
        return border;
    }

    private int doSearch(final String pattern, final String data) {
        this.pattern = pattern;
        this.data = data;

        // analyze pattern and build lookup table
        this.next = new int[this.pattern.length() + 1];
        for (int i = 1; i < this.pattern.length(); i++) {
            String subPattern = this.pattern.substring(0, i + 1);
            String border = getBorder(subPattern);

            LOG.debug("sub pattern: {} has border: {}", subPattern, border);
            this.next[i + 1] = border.length();
        }
        LOG.debug("lookup table: {}", Arrays.toString(this.next));

        // iterate through data
        for (int i = 0; i < this.data.length(); i++) {
            if (tryMatch(i)) {
                int foundAt = i - this.pattern.length() + 1;
                LOG.debug("found the pattern at index: {}", foundAt);
                return foundAt;
            }
        }

        // found nothing, return a sad -1
        return -1;
    }

    /**
     * Try to match current pattern index at specified index in data.
     * If no match is found, but the new pattern index is not 0, retry match.
     *
     * @param index current data index
     * @return true if the full pattern was found.
     */
    private boolean tryMatch(final int index) {
        if (this.data.charAt(index) == this.pattern.charAt(this.patternIndex)) {
            LOG.debug("match by comparing: {} with: {} -> new pattern index: {}",
                    this.data.charAt(index), this.pattern.charAt(this.patternIndex), this.patternIndex + 1);
            this.patternIndex++;

            // if pattern index reaches the size of the pattern - we found what we are looking for.
            return this.patternIndex >= this.pattern.length();
        } else {
            LOG.debug("no match by comparing: {} with: {} -> new pattern index: {}",
                    this.data.charAt(index), this.pattern.charAt(this.patternIndex), next[this.patternIndex]);

            if (this.patternIndex != 0) {
                this.patternIndex = this.next[this.patternIndex];
                tryMatch(index);
            }
        }
        return false;
    }
}
