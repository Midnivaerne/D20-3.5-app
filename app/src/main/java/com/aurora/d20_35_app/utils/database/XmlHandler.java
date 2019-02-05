package com.aurora.d20_35_app.utils.database;

import com.aurora.d20_35_app.enums.DBColumnNames;
import com.aurora.d20_35_app.enums.ItemType;
import com.aurora.d20_35_app.models.helpers.Item;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class XmlHandler extends DefaultHandler {

    private final DatabaseHolder databaseHolder;
    private List itemList = null;
    private Map itemMap = null;
    private Item item = null;
    private StringBuilder data = null;

    public XmlHandler(DatabaseHolder databaseHolder) {
        this.databaseHolder = databaseHolder;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (ItemType.contains(qName)) {
            itemList = ItemType.fromString(qName).getDatabaseList(databaseHolder);
            itemMap = ItemType.fromString(qName).getDatabaseMap(databaseHolder);
        } else if (qName.contains("_Item") && ItemType.contains(qName.split("_")[0])) {
            item = ItemType.fromString(qName.split("_")[0]).getNewObject();
        } else if (DBColumnNames.contains(qName)) {
            Objects.requireNonNull(DBColumnNames.fromString(qName)).setColumnIsUsed(true);
        }
        data = new StringBuilder();
    }

    @Override
    public void characters(char ch[], int start, int length) { //todo probably to change
        data.append(new String(ch, start, length));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void endElement(String uri, String localName, String qName) {
        for (int i = 0; i < DBColumnNames.values().length; i++) {
            if (DBColumnNames.values()[i].getColumnIsUsed()) {
                DBColumnNames.values()[i].setParameter(item, data.toString().trim());
                Objects.requireNonNull(DBColumnNames.fromString(qName)).setColumnIsUsed(false);
                break;
            }
        }
        if (qName.contains("_Item") && ItemType.contains(qName.split("_")[0])) {
            itemList.add(item); //warning suppressed
            itemMap.put(itemMap.size() + 1, item); //warning suppressed
        }
    }


}
