package com.aurora.d20_35_app.enums;

import com.aurora.d20_35_app.models.helpers.Item;

import org.junit.Assert;
import org.junit.Test;

import static com.aurora.d20_35_app.enums.DBSettingColumnNames.*;
import static com.aurora.d20_35_app.enums.DBSettingColumnNames.fromString;
import static com.aurora.d20_35_app.models.helpers.Item.*;

public class DBSettingColumnNamesTest {

    @Test
    public void toStringTest() {
        Assert.assertEquals(ITEM_ID_COLUMN_NAME, COL_ITEM_ID.toString());
        Assert.assertNotEquals(ITEM_NAME_COLUMN_NAME, COL_ITEM_ID.toString());
        Assert.assertNotEquals(ITEM_ID_COLUMN_NAME, COL_ITEM_NAME.toString());
    }

    @Test
    public void fromStringTest() {
        Assert.assertEquals(COL_ITEM_ID, fromString(ITEM_ID_COLUMN_NAME));
        Assert.assertNotEquals(COL_ITEM_NAME, fromString(ITEM_ID_COLUMN_NAME));
        Assert.assertNotEquals(COL_ITEM_ID, fromString(ITEM_NAME_COLUMN_NAME));
    }

    @Test
    public void containsTest() {
        Assert.assertTrue(contains(ITEM_ID_COLUMN_NAME));
        Assert.assertFalse(contains("RandomText"));
    }

    @Test
    public void setParameterTest() {
        Item item = new Item();
        String data = "TestName";
        COL_ITEM_NAME.setParameter(item, data);
        Assert.assertEquals("TestName", item.getName());
    }

    @Test
    public void columnIsUsedTest() {
        COL_ITEM_ID.setColumnIsUsed(true);
        Assert.assertTrue(COL_ITEM_ID.getColumnIsUsed());
        COL_ITEM_ID.setColumnIsUsed(false);
        Assert.assertFalse(COL_ITEM_ID.getColumnIsUsed());
    }

}