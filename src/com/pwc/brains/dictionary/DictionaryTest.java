package com.pwc.brains.dictionary;

import com.pwc.brains.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class DictionaryTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInitDictionary() {
        Dictionary dic = new Dictionary("Chinese");
        assertEquals("Chinese", dic.name());
    }

    @Test
    public void addWord() {
        String word = "你好";
        Dictionary dic = new Dictionary("Chinese");
        dic.put(word);
        Collection<Letter> expected_lettes = new ArrayList<Letter>();
        expected_lettes.add(new Letter('你'));
        expected_lettes.add(new Letter('好'));

        assertEquals(expected_lettes.size(), dic.letters().size());
        for (Letter c : dic.letters()) {
            assertEquals(true, expected_lettes.contains(c));
        }
    }

    @Test
    public void saveDictionary() throws Exception {
        Dictionary dic = new Dictionary("Chinese");
        dic.put("你好");
        dic.save();
        assertTrue(Util.isFileExists("Chinese"));
    }

    @Test
    public void loadDictionary2() throws DictionarySerializationException {
        Dictionary dic = Dictionary.load("Chinese_Test");
        assertEquals(2, dic.letters().size());
        assertTrue(dic.letters().contains(new Letter('你')));
        assertTrue(dic.letters().contains(new Letter('好')));
    }
}
