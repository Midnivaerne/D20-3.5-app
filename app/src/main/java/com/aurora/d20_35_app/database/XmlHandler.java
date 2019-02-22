package com.aurora.d20_35_app.database;

import com.aurora.d20_35_app.models.typeHelpers.CoreTypeHelper;
import com.aurora.d20_35_app.models.typeHelpers.ItemType;
import com.aurora.d20_35_app.models.typeHelpers.RulesType;
import com.aurora.d20_35_app.models.helpers.CoreHelper;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (CoreTypeHelper.contains(qName, RulesType.class) || CoreTypeHelper.contains(qName, ItemType.class)) {
            setupListAndMap(qName);
        } else if (qName.contains("_Item") && (CoreTypeHelper.contains(qName.split("_")[0], RulesType.class) || CoreTypeHelper.contains(qName.split("_")[0], ItemType.class))) {
            setupItem(qName);
        } else if (DBColumnNamesMethods.contains(qName, DBRulesColumnNames.class) || DBColumnNamesMethods.contains(qName, DBSettingColumnNames.class)) {
            setupObject(qName);
        }
        data = new StringBuilder();
    }

    private void setupListAndMap(String qName) {
        if (CoreTypeHelper.contains(qName, RulesType.class)) {
            itemList = CoreTypeHelper.fromString(qName, RulesType.class).getDatabaseList(databaseHolder);
            itemMap = CoreTypeHelper.fromString(qName, RulesType.class).getDatabaseMap(databaseHolder);
            dbPart = DBRulesColumnNames.class;
        } else if (CoreTypeHelper.contains(qName, ItemType.class)) {
            itemList = CoreTypeHelper.fromString(qName, ItemType.class).getDatabaseList(databaseHolder);
            itemMap = CoreTypeHelper.fromString(qName, ItemType.class).getDatabaseMap(databaseHolder);
            dbPart = DBSettingColumnNames.class;
        }
    }

    private void setupItem(String qName) {
        if (CoreTypeHelper.contains(qName.split("_")[0], RulesType.class)) {
            item = CoreTypeHelper.fromString(qName.split("_")[0], RulesType.class).getNewObject();
        }
        if (CoreTypeHelper.contains(qName.split("_")[0], ItemType.class)) {
            item = CoreTypeHelper.fromString(qName.split("_")[0], ItemType.class).getNewObject();
        }
    }

    private void setupObject(String qName) {
        if (DBColumnNamesMethods.contains(qName, DBRulesColumnNames.class)) {
            Objects.requireNonNull(DBColumnNamesMethods.fromString(qName, DBRulesColumnNames.class)).setColumnIsUsed(true);
        }
        if (DBColumnNamesMethods.contains(qName, DBSettingColumnNames.class)) {
            Objects.requireNonNull(DBColumnNamesMethods.fromString(qName, DBSettingColumnNames.class)).setColumnIsUsed(true);
        }
    }


    @Override
    public void characters(char ch[], int start, int length) { //todo probably to change
        data.append(new String(ch, start, length));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endElement(String uri, String localName, String qName) {
        for (int i = 0; i < dbPart.getEnumConstants().length; i++) {
            if (((DBColumnNamesMethods) dbPart.getEnumConstants()[i]).getColumnIsUsed()) {
                ((DBColumnNamesMethods) dbPart.getEnumConstants()[i]).setParameter((item), data.toString().trim());
                ((DBColumnNamesMethods) Objects.requireNonNull(DBColumnNamesMethods.fromString(qName, dbPart))).setColumnIsUsed(false);
                break;
            }
        }
        if (qName.contains("_Item") && (CoreTypeHelper.contains(qName.split("_")[0], RulesType.class) || CoreTypeHelper.contains(qName.split("_")[0], ItemType.class))) {
            itemList.add(item); //warning suppressed
            itemMap.put(itemMap.size() + 1, item); //warning suppressed
        }
    }


}
