package com.aurora.d20_35_app.models.typeHelpers;

import static com.aurora.d20_35_app.database.DBSettingColumnNames.COL_ITEM_ID;
import static com.aurora.d20_35_app.database.DBSettingColumnNames.COL_ITEM_NAME;
import static com.aurora.d20_35_app.database.DBSettingColumnNames.contains;
import static com.aurora.d20_35_app.database.DBSettingColumnNames.fromString;

import com.aurora.d20_35_app.database.DBColumnNames;
import com.aurora.d20_35_app.database.DBSettingColumnNames;
import com.aurora.d20_35_app.models.helpers.Item;
import org.junit.Assert;
import org.junit.Test;

public class DBSettingColumnNamesTest {

    @Test
    public void toStringTest() {
        Assert.assertEquals(DBColumnNames.ITEM_ID_COLUMN_NAME, COL_ITEM_ID.toString());
        Assert.assertNotEquals(DBColumnNames.ITEM_NAME_COLUMN_NAME, COL_ITEM_ID.toString());
        Assert.assertNotEquals(DBColumnNames.ITEM_ID_COLUMN_NAME, COL_ITEM_NAME.toString());
    }

    @Test
    public void fromStringTest() {
        Assert.assertEquals(COL_ITEM_ID, fromString(DBColumnNames.ITEM_ID_COLUMN_NAME, DBSettingColumnNames.class));
        Assert.assertNotEquals(COL_ITEM_NAME, fromString(DBColumnNames.ITEM_ID_COLUMN_NAME,DBSettingColumnNames.class));
        Assert.assertNotEquals(COL_ITEM_ID, fromString(DBColumnNames.ITEM_NAME_COLUMN_NAME,DBSettingColumnNames.class));
    }

    @Test
    public void containsTest() {
        Assert.assertTrue(contains(DBColumnNames.ITEM_ID_COLUMN_NAME,DBSettingColumnNames.class));
        Assert.assertFalse(contains("RandomText",DBSettingColumnNames.class));
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