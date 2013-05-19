package com.pwc.test;

import org.junit.After;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestBase {
    protected List<String> gc = new ArrayList<String>();

    @After
    public void clear() {
        for (String name : gc) {
            delete(name);
        }
        gc.clear();
    }

    private void delete(String name) {
        File file = new File(name);

        if (file.exists()) {
            System.out.println("deleting file " + name);
            if (file.delete()) {
                System.out.println("deleted " + name + " successfully!");
            } else {
                System.out.println("failed to delete the file " + name);
            }
        }
    }
}
