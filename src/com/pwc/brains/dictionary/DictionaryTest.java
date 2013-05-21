package com.pwc.brains.dictionary;

import com.pwc.brains.Util;
import com.pwc.brains.btree.ObjectSerializationException;
import com.pwc.test.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class DictionaryTest extends TestBase {
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
        Collection<Letter> expected = new ArrayList<Letter>();
        expected.add(new Letter('你'));
        expected.add(new Letter('好'));

        assertEquals(expected.size(), dic.letters().size());
        for (Letter c : dic.letters()) {
            assertEquals(true, expected.contains(c));
        }
    }

    @Test
    public void loadDictionary2() throws DictionarySerializationException, ObjectSerializationException {
        String name = "Chinese";
        gc.add(name);
        createDictionary(name);
        Dictionary dic = Dictionary.load(name);
        assertEquals(2, dic.letters().size());
        assertTrue(dic.letters().contains(new Letter('你')));
        assertTrue(dic.letters().contains(new Letter('好')));
    }


    public void createDictionary(String name) {
        Dictionary dic = new Dictionary(name);
        dic.put("你好");
        try {
            dic.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(Util.isFileExists(name));
    }
}
