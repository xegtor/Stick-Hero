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
    public void testPlatformPosition(){
        MyPlatform myPlatform = new MyPlatform(10, 10);
        Assert.assertEquals(10, myPlatform.getStartPosition());
    }
    @Test
    public void testPlatformWidth(){
        MyPlatform myPlatform = new MyPlatform(10, 10);
        Assert.assertEquals(10, myPlatform.getWidth());
    }
    @Test
    public void testPlatformSetPosition(){
        MyPlatform myPlatform = new MyPlatform(10, 10);
        myPlatform.setStartPosition(20);
        Assert.assertEquals(20, myPlatform.getStartPosition());
    }
    @Test
    public void testPlayerFlyweight(){
        Player player = Player.getPlayerScore("Default");
        Player player2 = Player.getPlayerScore("Default");
        Assert.assertSame(player, player2);
    }
}
