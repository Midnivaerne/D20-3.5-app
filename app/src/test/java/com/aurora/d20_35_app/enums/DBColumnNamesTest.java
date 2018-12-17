package com.aurora.d20_35_app.enums;

import com.aurora.d20_35_app.helper.Item;

import org.junit.Assert;
import org.junit.Test;

import static com.aurora.d20_35_app.enums.DBColumnNames.*;
import static com.aurora.d20_35_app.enums.DBColumnNames.fromString;
import static com.aurora.d20_35_app.helper.Item.*;

public class DBColumnNamesTest {

    @Test
    public void toStringTest() {
        Assert.assertEquals(itemIdColumnName, ColItemID.toString());
        Assert.assertNotEquals(nameColumnName, ColItemID.toString());
        Assert.assertNotEquals(itemIdColumnName, ColName.toString());
    }

    @Test
    public void fromStringTest() {
        Assert.assertEquals(ColItemID, fromString(itemIdColumnName));
        Assert.assertNotEquals(ColName, fromString(itemIdColumnName));
        Assert.assertNotEquals(ColItemID, fromString(nameColumnName));
    }

    @Test
    public void containsTest() {
        Assert.assertTrue(contains(itemIdColumnName));
        Assert.assertFalse(contains("RandomText"));
    }

    @Test
    public void setParameterTest() {
        Item item = new Item();
        String data = "TestName";
        ColName.setParameter(item, data);
        Assert.assertEquals("TestName", item.getName());
    }

    @Test
    public void columnIsUsedTest() {
        ColItemID.setColumnIsUsed(true);
        Assert.assertTrue(ColItemID.getColumnIsUsed());
        ColItemID.setColumnIsUsed(false);
        Assert.assertFalse(ColItemID.getColumnIsUsed());
    }

}