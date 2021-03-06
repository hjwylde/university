package com.hjwylde.uni.comp261.assignment03.robotSimulator.parser;

import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * Code for Assignment 3, COMP 261 Name: Henry J. Wylde Usercode: wyldehenr ID: 300224283
 */

/**
 * A helper class that iterates through a <code>CharSequence</code> with methods such as
 * <code>expect(CharSequence expect);</code> and <code>hasNext();</code> .
 * 
 * @author Henry J. Wylde
 */
public class ParserScanner {

    private final CharSequence cs;

    private int index = 0;

    /**
     * Initialize with the given <code>CharSequence</code>.
     * 
     * @param cs the CharSequence.
     */
    public ParserScanner(CharSequence cs) {
        this.cs = cs;
    }

    /**
     * Initialize with the given <code>Scanner</code>. It will then form a <code>CharSequence</code>
     * from the combination of all the <code>Scanner</code>'s tokens.
     * 
     * @param s the Scanner.
     */
    public ParserScanner(Scanner s) {
        String str = new String();
        while (s.hasNext())
            str += s.next();

        cs = str;
    }

    /**
     * Clears all whitespace up to the next character in the <code>CharSequence</code>.
     */
    public void clearWhitespace() {
        while (hasNext() && Character.isWhitespace(peek()))
            index++;
    }

    /**
     * Expect the given <code>CharSequence</code>, clearing whitespace before and after checking,
     * advancing the index if it is found, throwing a <code>ParseException</code> if the given
     * <code>CharSequence</code> was not found.
     * 
     * @param expect the CharSequence to expect.
     * @throws ParseException if the CharSequence was not found.
     */
    public void expect(CharSequence expect) throws ParseException {
        expect(expect, true, true);
    }

    /**
     * Expect the given <code>CharSequences</code> clearing whitespace before and after checking
     * each expect. Advances the index after each expect, throws a <code>ParseException</code> if an
     * expect was not found.
     * 
     * @param expects an array of all CharSequences to expect.
     * @throws ParseException if any CharSequence was not found.
     */
    public void expect(CharSequence... expects) throws ParseException {
        for (CharSequence expect : expects)
            expect(expect, true, true);
    }

    /**
     * Expect the given <code>CharSequence</code>, clearing whitespace before and after checking the
     * expect if <code>clear</code>. Throws a <code>ParseException</code> if the expect was not
     * found.
     * 
     * @param expect the CharSequence to expect.
     * @param clear whether to clear whitespace before and after checking the expect.
     * @throws ParseException if the CharSequence was not found.
     */
    public void expect(CharSequence expect, boolean clear) throws ParseException {
        expect(expect, clear, clear);
    }

    /**
     * Expect the given <code>CharSequence</code>, clearing whitespace before and after checking the
     * expect if <code>clearBefore</code> and <code>clearAfter</code> respectively. Throws a
     * <code>ParseException</code> if the expect was not found.
     * 
     * @param expect the CharSequence to expect.
     * @param clearBefore whether to clear whitespace before checking the expect.
     * @param clearAfter whether to clear whitespace after checking the expect.
     * @throws ParseException if the CharSequence was not found.
     */
    public void expect(CharSequence expect, boolean clearBefore, boolean clearAfter)
            throws ParseException {
        if (clearBefore)
            clearWhitespace();

        // Check that there is enough characters for the expect.
        if ((index + expect.length()) > cs.length())
            throw new ParseException("Expected \"" + expect + "\". Received \""
                    + cs.subSequence(index, cs.length()) + "\".");

        // Check each character to see if it matches the expect.
        for (int i = index; i < (index + expect.length()); i++)
            if (cs.charAt(i) != expect.charAt(i - index))
                throw new ParseException("Expected \"" + expect + "\". Received \""
                        + cs.subSequence(index, index + expect.length()) + "\".");

        // Advance the iterator.
        index += expect.length();

        if (clearAfter)
            clearWhitespace();
    }

    /**
     * Expect at least one more token from this <code>ParserScanner</code>. This method does not
     * advance the iterator, it only throws a <code>ParseException</code> if there are no more
     * tokens.
     * 
     * @throws ParseException if there are no more tokens.
     */
    public void expectToken() throws ParseException {
        if (!hasNext())
            throw new ParseException("Unexpected end of iterator.");
    }

    /**
     * Check whether this <code>ParserScanner</code> has any more tokens to iterate through.
     * 
     * @return <code>true</code> if there are more tokens.
     */
    public boolean hasNext() {
        return index < cs.length();
    }

    /**
     * Check whether the next token is an <code>int</code>. Returns <code>false</code> if there are
     * no more tokens or the next token is not an <code>int</code>.
     * 
     * @return <code>true</code> if the next token is an int.
     */
    public boolean hasNextInt() {
        if (!hasNext())
            return false;

        // (peek().isDigit) || (if (peek() == '-') && (not end of CharSequence) &&
        // (peek(2).isDigit))
        return Character.isDigit(peek())
                || ((peek() == '-') && ((index + 1) < cs.length()) && Character.isDigit(cs
                        .charAt(index + 1)));
    }

    /**
     * Gets the next character and advances the index. Throws a <code>NoSuchElementException</code>
     * if there are no more characters to get.
     * 
     * @return the next character.
     */
    public char next() {
        if (!hasNext())
            throw new NoSuchElementException();

        return cs.charAt(index++);
    }

    /**
     * Gets the next <code>int</code>. Throws a <code>NoSuchElementException</code> if there are no
     * more characters to get or the next token is not an <code>int</code>.
     * 
     * @return the next int.
     */
    public int nextInt() {
        if (!hasNextInt())
            throw new NoSuchElementException();

        String str = "";
        if (peek() == '-')
            str += next();

        while (hasNext() && Character.isDigit(peek()))
            str += next();

        return Integer.valueOf(str);
    }

    /**
     * Gets the next character without advancing the index. Throws a
     * <code>NoSuchElementException</code> if there are no more characters.
     * 
     * @return the next character.
     */
    public char peek() {
        if (!hasNext())
            throw new NoSuchElementException();

        return cs.charAt(index);
    }

    /**
     * Resets the index to 0.
     */
    public void reset() {
        index = 0;
    }
}
