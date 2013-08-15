package com.hjwylde.uni.swen221.lab02.list;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

/*
 * Code for Laboratory 2, SWEN 221
 * Name: Henry J. Wylde
 * Usercode: wyldehenr
 * ID: 300224283
 */

public class SumListTests extends TestCase {
    
    private static final int[] data = {
        -2147483648, 2147483647, 0, 1763060082, -814593344, -1743855880,
        1771388034, 911599810, -2006498382, -487868712, -1353262854,
        -1319354716, 982386520, -1651342802, 1826011298, -449885135,
        2039943705, -626967339, -2102565559, 2005752864, -1523943036,
        1364673097, -501953346, -2007411809, 1390571156, -1661477730,
        2000574138, -1239622555, 1307910196, 55725747, 1784280316, 1069194247,
        -415514108, 209964455, -1841589834, -230945653, 652000093, 1583029642,
        -360808409, 1548470842, 817578643, -536033961, 719115144, -2002359622,
        -1506875271, 1382157858, 678084652, -2099695269, -1767253975,
        -461618803, -1044092190, -146328400, 1042075396, 820566912,
        -1354839395, -35468958, 1062473975, -1051024616, 2115141719,
        1200768576, -1703355136, 874962655, 998361393, -2045479527, 2023171568,
        1424300094, 1097395934, -1633007667, 750752626, -1937563089, 851106370,
        2115017505, -296087733, -1811523786, 1784786005, -1045549869,
        -1094884638, 458113681, -199917235, -1148778642, 1516546307,
        1505503406, 188334588, 231211615, -1134323745, 887497436, 569616246,
        2094147271, -1790515369, -1196178875, -1867328266, -53015150,
        -1516349575, 531459612, 1586441217, 395610823, 1950489418, 824635666,
        -1821557044, -791725535, -1589793851, -2004817395, 1477248218,
        1108394207, -1402583260, -1539042277, -734218775, 1764219096,
        2051252317, -795567708, 1789128462, -1774880559, 123044591, -39131425,
        842630686, -1762415086, 1764323037, -337465465, -1850167472, 311950134,
        -1839238918, 1388846029, -1081059040, 2105299011, -353518745,
        -2104078192, -619584907, -355012614, 350667354, -1491482934,
        -128807163, -1367681425, -1879187168, 723265101, 293953016, -299928357,
        121817956, -1683581008, -2067192117, -1169697104, 491540145,
        -574439037, -557768585, 667845613, 2005870645, 254928073, -552847663,
        -576932685, -1843006777, 907332775, 907137588, 594061848, -971280086,
        469140824, -1444874916, 1337999419, -1516717933, -1319694749,
        1950930324, 1784810904, -412287128, -286098416, -2061479660,
        1966646173, 377295946, -590070761, 1126426531, 1735443581, -435895979,
        -1408405093, -1305994876, -463691197, -1183539290, 1373674582,
        -120041329, -664569047, -1302651107, -240910572, -384256099,
        -1168234710, -2005940637, 1891596645, -559700951, 1555458773,
        -339480786, -1790850931, 1440839219, 950543203, 395084483, 114582463,
        -2088496537, 2086707211, 824998588, -493590646, -765633921, 61455509,
        -1151325685, 1875526803, 41568228, -1215065811, 851106370, 2115017505,
        -296087733, -1811523786, 1784786005, -1045549869, -1094884638,
        458113681, -199917235, -1148778642, 1516546307, 1505503406, 188334588,
        231211615, -1134323745, 887497436, 569616246, 2094147271, -1790515369,
        -1196178875, -1867328266, -53015150, -1516349575, 531459612,
        1586441217, 395610823, 1950489418, 824635666, -1821557044, -791725535,
        -1589793851, -2004817395, 1477248218, 1108394207, -1402583260,
        -1539042277, -734218775, 1764219096, 2051252317, -795567708,
        1789128462, -1774880559, 123044591, -39131425, 842630686, -1762415086,
        1764323037, -337465465, -1850167472, 311950134, -1839238918,
        1388846029, -1081059040, 2105299011, -353518745, -2104078192,
        -619584907, -355012614, 350667354, -1491482934, -412287128, -286098416,
        -2061479660, 1966646173, 377295946, -590070761, 1126426531, 1735443581,
        -435895979, -1408405093, -1305994876, -463691197, -1183539290,
        1373674582, -120041329, -664569047, -1302651107, -240910572,
        -384256099, -1168234710, -2005940637, 1891596645, -559700951,
        1555458773, -339480786, -1790850931, 1440839219, 950543203, 395084483,
        114582463, -2088496537, 2086707211, 824998588, -493590646, -765633921,
        61455509, -1151325685, 1875526803, 41568228, -1215065811, 851106370,
        2115017505, -296087733, -1811523786, 1784786005
    };
    
    public @Test
    void testAdd() {
        SumList l = new SumList();
        
        int sum = 0;
        for (int d : SumListTests.data) {
            if (l.sum() != sum)
                Assert.fail("sum is " + l.sum() + ", should be " + sum);
            l.add(d);
            sum += d;
        }
    }
    
    public @Test
    void testEmpry() {
        Assert.assertTrue(new SumList().sum() == 0);
    }
    
    public @Test
    void testGetFail() {
        SumList l = new SumList();
        ArrayList<Integer> dummy = new ArrayList<>();
        
        int[] indexes = {
            -1, 1000, -101
        };
        
        // int sum = 0;
        for (int d : SumListTests.data) {
            l.add(d);
            dummy.add(d);
            // sum += d;
        }
        
        for (int idx : indexes)
            try {
                l.get(idx);
                Assert.fail("SumList.get() should have thrown exception");
            } catch (IndexOutOfBoundsException e) {
                
            }
    }
    
    public @Test
    void testRemove() {
        SumList l = new SumList();
        ArrayList<Integer> dummy = new ArrayList<>();
        
        int[] indexes = {
            100, 23, 44, 56, 33, 31, 56, 23, 9, 2, 0, 34, 55, 57, 61, 27, 3, 7,
            10
        };
        
        int sum = 0;
        for (int d : SumListTests.data) {
            l.add(d);
            dummy.add(d);
            sum += d;
        }
        
        for (int idx : indexes) {
            if (l.sum() != sum)
                Assert.fail("sum is " + l.sum() + ", should be " + sum);
            int val = l.remove(idx);
            if (val != dummy.remove(idx))
                Assert.fail("remove returned incorrect value: " + val);
            sum -= val;
        }
    }
    
    public @Test
    void testRemoveFail() {
        SumList l = new SumList();
        ArrayList<Integer> dummy = new ArrayList<>();
        
        int[] indexes = {
            -1, 1000, -101
        };
        
        // int sum = 0;
        for (int d : SumListTests.data) {
            l.add(d);
            dummy.add(d);
            // sum += d;
        }
        
        for (int idx : indexes)
            try {
                l.remove(idx);
                Assert.fail("SumList.remove() should have thrown exception");
            } catch (IndexOutOfBoundsException e) {
                
            }
    }
    
    public @Test
    void testSet() {
        SumList l = new SumList();
        ArrayList<Integer> dummy = new ArrayList<>();
        
        int[] indexes = {
            100, 23, 44, 56, 33, 31, 56, 23, 9, 2, 0, 34, 55, 57, 61, 27, 3, 7,
            10
        };
        int[] ndata = {
            907137588, 594061848, -971280086, 469140824, -1444874916,
            1337999419, -1516717933, -1319694749, 1950930324, 1784810904,
            -412287128, -286098416, -2061479660, 1966646173, 377295946,
            -590070761, 1126426531, 1735443581, -435895979, -1408405093,
            -1305994876, -463691197, -1183539290, 1373674582, -120041329,
            -664569047, -1302651107, -240910572, -384256099, -1168234710,
            -2005940637, 1891596645, -559700951, 1555458773, -339480786,
            -1790850931, 1440839219, 950543203, 395084483, 114582463,
            -2088496537, 2086707211, 824998588, -493590646, -765633921,
            61455509, -1151325685, 1875526803, 41568228, -1215065811
        };
        
        int sum = 0;
        for (int d : SumListTests.data) {
            l.add(d);
            dummy.add(d);
            sum += d;
        }
        
        for (int i = 0; i != indexes.length; ++i) {
            if (l.sum() != sum)
                Assert.fail("sum is " + l.sum() + ", should be " + sum);
            int nv = ndata[i];
            int idx = indexes[i];
            int ov = dummy.set(idx, nv);
            sum -= ov;
            sum += nv;
            l.set(idx, nv);
        }
    }
    
    public @Test
    void testSetFail() {
        SumList l = new SumList();
        ArrayList<Integer> dummy = new ArrayList<>();
        
        int[] indexes = {
            -1, 1000, -101
        };
        
        // int sum = 0;
        for (int d : SumListTests.data) {
            l.add(d);
            dummy.add(d);
            // sum += d;
        }
        
        for (int idx : indexes)
            try {
                l.set(idx, 0);
                Assert.fail("SumList.set() should have thrown exception");
            } catch (IndexOutOfBoundsException e) {
                
            }
    }
}
