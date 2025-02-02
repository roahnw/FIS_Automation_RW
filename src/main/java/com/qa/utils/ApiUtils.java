package com.qa.utils;

import com.qa.pages.BasePage;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

public class ApiUtils {

    public static void isMapKeysContainsInList(List<String> list, Set<String> keySet) {

        boolean flag = true;
        for(String key:keySet) {
            if (!list.contains(key)) {
                flag = false;
                break;
            }
        }

        if(flag) {
            BasePage.test.get().pass("Actual: ["+list+"] matched with expected: ["+keySet+"]");
        }
        else {
            BasePage.test.get().fail("Actual: [" + list + "] not matched with expected: [" + keySet + "]");
            Assert.fail("Actual: [" + list + "] not matched with expected: [" + keySet + "]");
        }
    }
}
