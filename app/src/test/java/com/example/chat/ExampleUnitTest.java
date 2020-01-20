package com.example.chat;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        int vlaue=0 ;

        for(int i =0;i<5;i++){

            vlaue=i+i;
            i++;
        }
        assertEquals(7,vlaue);
    }
}