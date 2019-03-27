package com.aurora.core.models.typehelpers;

import static com.aurora.core.database.DbColumnNamesMethods.contains;
import static com.aurora.core.database.DbColumnNamesMethods.fromString;
import static com.aurora.core.database.DbSettingColumns.COL_ITEM_ID;
import static com.aurora.core.database.DbSettingColumns.COL_ITEM_NAME;

import com.aurora.core.database.DbColumnNames;
import com.aurora.core.database.DbSettingColumns;
import com.aurora.core.models.helpers.Item;
import org.junit.Assert;
import org.junit.Test;

public class DBSettingColumnsTest {

    @Test
    public void toStringTest() {
      Assert.assertEquals(DbColumnNames.ITEM_ID_COLUMN_NAME, COL_ITEM_ID.toString());
      Assert.assertNotEquals(DbColumnNames.ITEM_NAME_COLUMN_NAME, COL_ITEM_ID.toString());
      Assert.assertNotEquals(DbColumnNames.ITEM_ID_COLUMN_NAME, COL_ITEM_NAME.toString());
    }

    @Test
    public void fromStringTest() {
      Assert.assertEquals(COL_ITEM_ID, fromString(DbColumnNames.ITEM_ID_COLUMN_NAME, DbSettingColumns.class));
      Assert.assertNotEquals(COL_ITEM_NAME, fromString(DbColumnNames.ITEM_ID_COLUMN_NAME, DbSettingColumns.class));
      Assert.assertNotEquals(COL_ITEM_ID, fromString(DbColumnNames.ITEM_NAME_COLUMN_NAME, DbSettingColumns.class));
    }

    @Test
    public void containsTest() {
      Assert.assertTrue(contains(DbColumnNames.ITEM_ID_COLUMN_NAME, DbSettingColumns.class));
      Assert.assertFalse(contains("RandomText", DbSettingColumns.class));
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