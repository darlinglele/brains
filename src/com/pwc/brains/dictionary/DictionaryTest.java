package com.pwc.brains.dictionary;

import com.pwc.brains.Util;
import com.pwc.brains.btree.ObjectSerializationException;
import com.pwc.test.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
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
        assertEquals(expected.size(), dic.trees().keySet().size());
        for (Letter c : dic.trees().keySet()) {
            assertEquals(true, expected.contains(c));
        }
    }

    @Test
    public void loadDictionary2() throws DictionarySerializationException, ObjectSerializationException {
        String name = "Chinese";
        gc.add(name);
        createDictionary(name);
        Dictionary dic = Dictionary.load(name);
        assertEquals(2, dic.trees().size());
        assertTrue(dic.trees().containsKey(new Letter('你')));
        assertTrue(dic.trees().containsKey(new Letter('好')));
    }

    @Test
    public void findWords() throws DictionarySerializationException, ObjectSerializationException {
        String name = "Chinese";
        gc.add(name);
        createDictionary(name);
        Dictionary dic = Dictionary.load(name);
        List<String> results = dic.search("你");

        assertTrue(results.contains("你好"));
        assertTrue(results.contains("你妹"));
    }

     @Test
    public void buildDictionary() throws Exception {
        Dictionary dic = new Dictionary("chinese.dic");
        try {
            FileInputStream fstream = new FileInputStream("chinese.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                dic.put(strLine);
            }


            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        try {
            FileInputStream fstream = new FileInputStream("english.txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                dic.put(strLine);
            }


            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        dic.save();
    }

    public void loadDictionary() throws Exception {
        buildDictionary();
        Dictionary dic = Dictionary.load("chinese.dic");
        List<String> result = dic.search("一");
        System.out.println(result.size());
    }

    public void createDictionary(String name) {
        Dictionary dic = new Dictionary(name);
        dic.put("你好");
        dic.put("好吗");
        dic.put("你妹");
        try {
            dic.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(Util.isFileExists(name));
    }


    @Test
    public void testIfStringIsPrefixOrBound() throws Exception {
//        loadDictionary();
        Dictionary dic = Dictionary.load("chinese.dic");

        String word1 = "中华";
        assertTrue(dic.isBound(word1));
        assertTrue(dic.isPrefix(word1));
        String word2 = "中华门";
        assertTrue(dic.isBound(word2));
        assertFalse(dic.isPrefix(word2));
        String word3 = "中华门卫";
        assertFalse(dic.isBound(word3));
        assertFalse(dic.isPrefix(word3));
        String word4 = "公安部";
        assertTrue(dic.isBound(word4));
        assertTrue(dic.isPrefix(word4));
        String word5 = ",";
        assertFalse(dic.isBound(word5));
        assertFalse(dic.isPrefix(word5));

    }
}
