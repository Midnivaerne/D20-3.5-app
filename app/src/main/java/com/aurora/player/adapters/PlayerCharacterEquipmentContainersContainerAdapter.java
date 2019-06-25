package com.aurora.player.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import com.aurora.core.models.userdata.HeroEquipment;
import com.aurora.core.models.userdata.HeroPlayer;

public class PlayerCharacterEquipmentContainersContainerAdapter extends BaseExpandableListAdapter implements OnChildClickListener,
    OnGroupClickListener {

  private final Context context;
  private final HeroPlayer playerHero;
  private final LinkedHashMap<HeroEquipment, List<HeroEquipment>> itemCollections;
  private final ArrayList<HeroEquipment> containerList;

  public PlayerCharacterEquipmentContainersContainerAdapter(Context context, HeroPlayer playerHero) {
    this.context = context;
    this.playerHero = playerHero;
    itemCollections = playerHero.getHeroContainerEquipmentMap();
    containerList = new ArrayList<>(new LinkedHashSet<>(itemCollections.keySet()));
  }

  @Override
  public int getGroupCount() {
    return containerList.size();
  }

  @Override
  public int getChildrenCount(int containerPosition) {
    return itemCollections.get(containerList.get(containerPosition)).size();
  }

  @Override
  public HeroEquipment getGroup(int containerPosition) {
    return containerList.get(containerPosition);
  }

  @Override
  public HeroEquipment getChild(int containerPosition, int equipmentItemPosition) {
    return itemCollections.get(containerPosition).get(equipmentItemPosition);
  }

  @Override
  public long getGroupId(int containerPosition) {
    return containerPosition;
  }

  @Override
  public long getChildId(int containerPosition, int equipmentItemPosition) {
    return equipmentItemPosition;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public View getGroupView(int containerPosition, boolean b, View view, ViewGroup viewGroup) {
    return null;
  }

  @Override
  public View getChildView(int containerPosition, int equipmentItemPosition, boolean b, View view, ViewGroup viewGroup) {
    return null;
  }

  @Override
  public boolean isChildSelectable(int containerPosition, int equipmentItemPosition) {
    return false;
  }

  @Override
  public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
    return false;
  }

  @Override
  public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
    return false;
  }
}
