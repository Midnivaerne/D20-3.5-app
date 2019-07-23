package com.aurora.player.adapters;

import static com.aurora.core.database.TranslationsHolder.translate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

import com.aurora.core.R;
import com.aurora.core.database.models.userdata.HeroEquipment;
import com.aurora.core.database.models.userdata.HeroPlayer;
import com.aurora.core.database.models.userdata.HeroWeapons;

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
  public View getGroupView(int containerPosition, boolean b, View view, ViewGroup parent) {
    HeroEquipment heroEquipment = (HeroEquipment) getGroup(containerPosition);
    EquipmentViewHolder equipmentViewHolder;
    if (view == null) {
      //LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //view = layoutInflater.inflate(R.layout.fragment_player_character_equipment_containers_container, null);
      view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.fragment_player_character_equipment_containers_container, parent, false);
      equipmentViewHolder = new EquipmentViewHolder(view, heroEquipment);
      view.setTag(equipmentViewHolder);
    }
    equipmentViewHolder = (EquipmentViewHolder) view.getTag();
    return view;
  }

  @Override
  public View getChildView(int containerPosition, int equipmentItemPosition, boolean b, View view, ViewGroup parent) {
    HeroEquipment heroEquipment = (HeroEquipment) getChild(containerPosition, equipmentItemPosition);
    EquipmentViewHolder equipmentViewHolder;
    if (view == null) {
      //LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //view = layoutInflater.inflate(R.layout.fragment_player_character_equipment_containers_container_item, null);
      view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.fragment_player_character_equipment_containers_container_item, parent, false);
      equipmentViewHolder = new EquipmentViewHolder(view, heroEquipment);
      view.setTag(equipmentViewHolder);
    }
    equipmentViewHolder = (EquipmentViewHolder) view.getTag();
    return view;
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

  class EquipmentViewHolder extends RecyclerView.ViewHolder {

    final TextView containerName;

    EquipmentViewHolder(View view, HeroEquipment heroEquipment) {
      super(view);
      containerName = (TextView) view.findViewById(R.id.fragment_player_character_equipment_containers_container_name);
      bind(heroEquipment);
    }

    public void bind(HeroEquipment heroEquipment) {
      containerName.setText(translate(heroEquipment.getName()));
      containerName.setTag(heroEquipment);
      //containerName.setOnClickListener(onClickListener);
    }
  }

  private class HeroWeaponsComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
      return ((HeroWeapons) o1).getName().compareTo(((HeroWeapons) o2).getName());
    }

    @Override
    public boolean equals(Object obj) {
      return false;
    }

    @Override
    public int hashCode() {
      return 0;
    }
  }
}
