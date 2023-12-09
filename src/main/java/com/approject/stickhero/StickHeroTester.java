package com.approject.stickhero;

import org.junit.Assert;
import org.junit.Test;

public class StickHeroTester {
    @Test
    public void testStick(){
        Stick stick = new Stick(10);
        Assert.assertEquals(10, stick.getLength());
    }
    @Test
    public void testPlatform(){
        MyPlatform myPlatform = new MyPlatform(10, 10);
        Assert.assertEquals(10, myPlatform.getStartPosition());
        Assert.assertEquals(10, myPlatform.getWidth());
        myPlatform.setStartPosition(20);
        Assert.assertEquals(20, myPlatform.getStartPosition());
    }
}
