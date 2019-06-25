package com.aurora.core.database;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.aurora.core.models.helpers.CoreHelper;
import com.aurora.core.models.typehelpers.CoreTypeHelper;
import com.aurora.core.models.typehelpers.ItemType;
import com.aurora.core.models.typehelpers.RulesType;

public class XmlHandler extends DefaultHandler {

  private final DatabaseHolder databaseHolder;
  private List itemList = null;
  private Map itemMap = null;
  private CoreHelper item = null;
  private StringBuilder data = null;
  private Class dbPart = null;

  public XmlHandler(DatabaseHolder databaseHolder) {
    this.databaseHolder = databaseHolder;
  }

  @Override
  public void startElement(String uri, String localName, String xmlObject, Attributes attributes) {
    if (CoreTypeHelper.contains(xmlObject, RulesType.class) || CoreTypeHelper.contains(xmlObject, ItemType.class)) {
      setupListAndMap(xmlObject);
    } else if (xmlObject.contains("_Item") && (CoreTypeHelper.contains(xmlObject.split("_")[0], RulesType.class) || CoreTypeHelper
        .contains(xmlObject.split("_")[0], ItemType.class))) {
      setupItem(xmlObject);
    } else if (DbColumnNamesMethods.contains(xmlObject, DbRulesColumns.class) || DbColumnNamesMethods
        .contains(xmlObject, DbSettingColumns.class)) {
      setupObject(xmlObject);
    }
    data = new StringBuilder();
  }

  private void setupListAndMap(String xmlObject) {
    if (CoreTypeHelper.contains(xmlObject, RulesType.class)) {
      itemList = CoreTypeHelper.fromString(xmlObject, RulesType.class).getDatabaseList(databaseHolder);
      itemMap = CoreTypeHelper.fromString(xmlObject, RulesType.class).getDatabaseMap(databaseHolder);
      dbPart = DbRulesColumns.class;
    } else if (CoreTypeHelper.contains(xmlObject, ItemType.class)) {
      itemList = CoreTypeHelper.fromString(xmlObject, ItemType.class).getDatabaseList(databaseHolder);
      itemMap = CoreTypeHelper.fromString(xmlObject, ItemType.class).getDatabaseMap(databaseHolder);
      dbPart = DbSettingColumns.class;
    }
  }

  private void setupItem(String xmlObject) {
    if (CoreTypeHelper.contains(xmlObject.split("_")[0], RulesType.class)) {
      item = CoreTypeHelper.fromString(xmlObject.split("_")[0], RulesType.class).getNewObject();
    }
    if (CoreTypeHelper.contains(xmlObject.split("_")[0], ItemType.class)) {
      item = CoreTypeHelper.fromString(xmlObject.split("_")[0], ItemType.class).getNewObject();
    }
  }

  private void setupObject(String xmlObject) {
    if (DbColumnNamesMethods.contains(xmlObject, DbRulesColumns.class)) {
      Objects.requireNonNull(DbColumnNamesMethods.fromString(xmlObject, DbRulesColumns.class)).setColumnIsUsed(true);
    }
    if (DbColumnNamesMethods.contains(xmlObject, DbSettingColumns.class)) {
      Objects.requireNonNull(DbColumnNamesMethods.fromString(xmlObject, DbSettingColumns.class)).setColumnIsUsed(true);
    }
  }


  @Override
  public void characters(char[] ch, int start, int length) { //todo probably to change
    data.append(new String(ch, start, length));
  }

  @Override
  @SuppressWarnings("unchecked")
  public void endElement(String uri, String localName, String xmlObject) {
    for (int i = 0; i < dbPart.getEnumConstants().length; i++) {
      if (((DbColumnNamesMethods) dbPart.getEnumConstants()[i]).getColumnIsUsed()) {
        ((DbColumnNamesMethods) dbPart.getEnumConstants()[i]).setParameter((item), data.toString().trim());
        ((DbColumnNamesMethods) Objects.requireNonNull(DbColumnNamesMethods.fromString(xmlObject, dbPart))).setColumnIsUsed(false);
        break;
      }
    }
    if (xmlObject.contains("_Item") && (CoreTypeHelper.contains(xmlObject.split("_")[0], RulesType.class) || CoreTypeHelper
        .contains(xmlObject.split("_")[0], ItemType.class))) {
      itemList.add(item); //warning suppressed
      itemMap.put(itemMap.size() + 1, item); //warning suppressed
    }
  }


}
