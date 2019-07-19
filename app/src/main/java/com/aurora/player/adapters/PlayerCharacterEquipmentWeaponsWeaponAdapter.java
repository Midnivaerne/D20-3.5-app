package com.aurora.player.adapters;

import static com.aurora.core.database.TranslationsHolder.translate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aurora.core.R;
import com.aurora.core.models.userdata.HeroPlayer;
import com.aurora.core.models.userdata.HeroWeapons;

public class PlayerCharacterEquipmentWeaponsWeaponAdapter extends BaseExpandableListAdapter {

  private final Context context;
  private final HeroPlayer playerHero;
  private final LinkedHashMap<Integer, List<Integer>> heroWeaponsAmmoIdMap;
  private Map<Integer, HeroWeapons> weaponsList;

  private ExpandableListView weaponsRecyclerView;
  private Map<Integer, Integer> weaponIdsToPositions = new HashMap<Integer, Integer>();
  private Map<Integer, Integer> weaponPositionsToIds = new HashMap<Integer, Integer>();
  private Map<Integer, Integer> ammoIdsToPositions = new HashMap<Integer, Integer>();
  private Map<Integer, Integer> ammoPositionsToIds = new HashMap<Integer, Integer>();
  private int lastExpandedGroupPosition = -1;

  public PlayerCharacterEquipmentWeaponsWeaponAdapter(Context context, ExpandableListView weaponsRecyclerView,
      HeroPlayer playerHero) {
    this.context = context;
    this.weaponsRecyclerView = weaponsRecyclerView;
    this.playerHero = playerHero;
    heroWeaponsAmmoIdMap = playerHero.getHeroWeaponsWithAmmoMap();
    weaponsList = playerHero.getHeroWeaponsFromIdMap();
    populateGroupListIds();
  }

  private void populateGroupListIds() {
    int i = 0;
    for (HeroWeapons hw : weaponsList.values()) {
      if (heroWeaponsAmmoIdMap.keySet().contains(hw.getItemID())) {
        weaponIdsToPositions.put(hw.getItemID(), i);
        weaponPositionsToIds.put(i, hw.getItemID());
        i++;
      }
    }
  }

  @Override
  public int getGroupCount() {
    return heroWeaponsAmmoIdMap != null ? heroWeaponsAmmoIdMap.size() : 0;
  }

  @Override
  public int getChildrenCount(int weaponPosition) {
    return heroWeaponsAmmoIdMap != null ? (heroWeaponsAmmoIdMap.get(weaponPositionsToIds.get(weaponPosition)) != null ? heroWeaponsAmmoIdMap
        .get(weaponPositionsToIds.get(weaponPosition)).size() : 0) : 0;
  }

  @Override
  public Object getGroup(int weaponPosition) {
    return weaponsList.get(weaponPositionsToIds.get(weaponPosition));
  }

  @Override
  public Object getChild(int weaponPosition, int ammoPosition) {
    return weaponsList.get(ammoPositionsToIds.get(ammoPosition));
  }

  @Override
  public long getGroupId(int weaponPosition) {
    return weaponPosition;
  }

  @Override
  public long getChildId(int weaponPosition, int ammoPosition) {
    return ammoPosition;
  }

  @Override
  public boolean hasStableIds() {
    return false;
  }

  @Override
  public View getGroupView(int weaponPosition, boolean isExpanded, View view, ViewGroup parent) {
    HeroWeapons heroWeapon = (HeroWeapons) getGroup(weaponPosition);
    WeaponViewHolder weaponViewHolder;
    if (view == null) {
      //LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //view = layoutInflater.inflate(R.layout.fragment_player_character_equipment_weapons_weapon, null);
      view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.fragment_player_character_equipment_weapons_weapon, parent, false);
      weaponViewHolder = new WeaponViewHolder(view, heroWeapon);
      view.setTag(weaponViewHolder);
    }
    weaponViewHolder = (WeaponViewHolder) view.getTag();
    if (getChildrenCount(weaponPosition) < 1) {
      weaponViewHolder.weaponRangedExpander.setClickable(false);
      weaponViewHolder.weaponRangedExpander.setEnabled(false);
      weaponViewHolder.weaponRangedExpander.setVisibility(View.INVISIBLE);
    }
    return view;
  }

  @Override
  public View getChildView(int weaponPosition, int ammoPosition, boolean isLastChild, View view, ViewGroup parent) {
    HeroWeapons weaponAmmo = (HeroWeapons) getChild(weaponPosition, ammoPosition);
    AmmoViewHolder ammoViewHolder;
    if (view == null) {
      //LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //view = layoutInflater.inflate(R.layout.fragment_player_character_equipment_weapons_weapon, null);
      view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.fragment_player_character_equipment_weapons_weapon_ranged, parent, false);
      ammoViewHolder = new AmmoViewHolder(view, weaponAmmo);
      view.setTag(ammoViewHolder);
    }
    ammoViewHolder = (AmmoViewHolder) view.getTag();
    HeroWeapons expandedHeroWeapon = weaponsList.get(weaponPositionsToIds.get(weaponPosition));
    if (expandedHeroWeapon != null && expandedHeroWeapon.getSelectedAmmoId() != null) {
      int selectedAmmoId = expandedHeroWeapon.getSelectedAmmoId();
      if (selectedAmmoId > -1 && selectedAmmoId == weaponAmmo.getItemID()) {
        ammoViewHolder.ammoEquippedIndicator.setChecked(true);
      }
    }
    return view;
  }

  @Override
  public boolean isChildSelectable(int weaponPosition, int ammoPosition) {
    return false;
  }

  @Override
  public void onGroupExpanded(int weaponPosition) {
    if (weaponPosition != lastExpandedGroupPosition) {
      weaponsRecyclerView.collapseGroup(lastExpandedGroupPosition);
    }
    super.onGroupExpanded(weaponPosition);
    lastExpandedGroupPosition = weaponPosition;
  }

  public void setListHeight() {
    int totalHeight = 0;
    int desiredWidth = View.MeasureSpec.makeMeasureSpec(weaponsRecyclerView.getWidth(), View.MeasureSpec.UNSPECIFIED);
    for (int i = 0; i < this.getGroupCount(); i++) {
      View groupItem = this.getGroupView(i, false, null, weaponsRecyclerView);
      groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
      totalHeight += groupItem.getMeasuredHeight();
      if (weaponsRecyclerView.isGroupExpanded(i)) {
        for (int j = 0; j < this.getChildrenCount(i); j++) {
          View listItem = this.getChildView(i, j, false, null, weaponsRecyclerView);
          listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
          totalHeight += listItem.getMeasuredHeight();
        }
      }
    }
    ViewGroup.LayoutParams params = weaponsRecyclerView.getLayoutParams();
    int height = totalHeight + (weaponsRecyclerView.getDividerHeight() * (this.getGroupCount() - 1));
    if (height < 10) {
      height = 200;
    }
    params.height = height;
    weaponsRecyclerView.setLayoutParams(params);
    weaponsRecyclerView.requestLayout();
  }

  class WeaponViewHolder extends RecyclerView.ViewHolder {

    final ImageButton weaponEquippedIndicator;
    final TextView weaponName;
    final TextView weaponProperties;
    final TextView weaponAttackDescription;
    final TextView weaponAttackValue;
    final TextView weaponDamageDescription;
    final TextView weaponDamageValue;
    final TextView weaponQuantity;
    final ImageButton weaponRangedExpander;

    WeaponViewHolder(View view, HeroWeapons heroWeapon) {
      super(view);
      weaponEquippedIndicator = (ImageButton) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_equipped);
      weaponName = (TextView) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_texts_name);
      weaponProperties = (TextView) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_texts_properties);
      weaponAttackDescription = (TextView) (view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_attack)
          .findViewById(R.id.fragment_number_value_with_description_rollable_box_description));
      weaponAttackValue = (TextView) (view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_attack)
          .findViewById(R.id.fragment_number_value_with_description_rollable_box_value));
      weaponDamageDescription = (TextView) (view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_damage)
          .findViewById(R.id.fragment_number_value_with_description_rollable_box_description));
      weaponDamageValue = (TextView) (view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_damage)
          .findViewById(R.id.fragment_number_value_with_description_rollable_box_value));
      weaponQuantity = (TextView) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_texts_quantity);
      weaponRangedExpander = (ImageButton) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_ranged_show);
      bind(view, heroWeapon);
    }

    public void bind(View view, HeroWeapons heroWeapon) {
      weaponEquippedIndicator.setTag(heroWeapon);
      //weaponEquippedIndicator.setOnClickListener(onClickListener);

      weaponName.setText(translate(heroWeapon.getName()));
      weaponName.setTag(heroWeapon);
      //containerName.setOnClickListener(onClickListener);

      weaponProperties.setTag(heroWeapon);
      //weaponProperties.setOnClickListener(onClickListener);

      weaponAttackDescription.setTag(heroWeapon);
      //weaponAttackDescription.setOnClickListener(onClickListener);

      weaponAttackValue.setTag(heroWeapon);
      //weaponAttackValue.setOnClickListener(onClickListener);

      weaponDamageDescription.setTag(heroWeapon);
      //weaponDamageDescription.setOnClickListener(onClickListener);

      weaponDamageValue.setTag(heroWeapon);
      //weaponDamageValue.setOnClickListener(onClickListener);

      weaponQuantity.setTag(heroWeapon);
      //weaponQuantity.setOnClickListener(onClickListener);

      weaponRangedExpander.setTag(heroWeapon);
      weaponRangedExpander.setOnClickListener(onExpandClickListener);
    }
  }

  class AmmoViewHolder extends RecyclerView.ViewHolder {

    final RadioButton ammoEquippedIndicator;
    final TextView ammoName;
    final TextView ammoProperties;
    final TextView ammoQuantity;
    final ImageButton ammoDetails;

    AmmoViewHolder(View view, HeroWeapons heroWeapon) {
      super(view);
      ammoEquippedIndicator = (RadioButton) view
          .findViewById(R.id.fragment_player_character_equipment_weapons_weapon_ranged_name_equipped_button);
      ammoName = (TextView) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_ranged_text_name);
      ammoProperties = (TextView) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_ranged_text_properties);
      ammoQuantity = (TextView) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_texts_quantity);
      ammoDetails = (ImageButton) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_equipped_details);
      bind(view, heroWeapon);
    }

    public void bind(View view, HeroWeapons heroWeapon) {
      ammoEquippedIndicator.setTag(heroWeapon);
      ammoEquippedIndicator.setOnClickListener(onAmmoToggleClickListener);

      ammoName.setText(translate(heroWeapon.getName()));
      ammoName.setTag(heroWeapon);
      //ammoName.setOnClickListener(onClickListener);

      ammoProperties.setText(translate("TODO"));//todo ammoProperties
      ammoProperties.setTag(heroWeapon);
      //ammoProperties.setOnClickListener(onClickListener);

      ammoQuantity.setTag(heroWeapon);
      //weaponQuantity.setOnClickListener(onClickListener);

      ammoDetails.setTag(heroWeapon);
      //ammoDetails.setOnClickListener(onClickListener);
    }
  }

  private View.OnClickListener onExpandClickListener = view -> {
    int thisWeaponPosition = weaponIdsToPositions.get(((HeroWeapons) view.getTag()).getItemID());
    if (getChildrenCount(thisWeaponPosition) > 0) {
      weaponsRecyclerView.smoothScrollToPosition(thisWeaponPosition);
      expandAndPopulateChildListIds(thisWeaponPosition, weaponsRecyclerView.isGroupExpanded(thisWeaponPosition));
      setListHeight();
    }
  };

  private void expandAndPopulateChildListIds(int thisWeaponPosition, Boolean expanded) {
    if (!expanded) {
      int i = 0;
      for (HeroWeapons ha : weaponsList.values()) {
        if (heroWeaponsAmmoIdMap.get(weaponPositionsToIds.get(thisWeaponPosition)).contains(ha.getItemID())) {
          ammoIdsToPositions.put(ha.getItemID(), i);
          ammoPositionsToIds.put(i, ha.getItemID());
          i++;
        }
      }
      weaponsRecyclerView.expandGroup(thisWeaponPosition, true);
    } else {
      weaponsRecyclerView.collapseGroup(thisWeaponPosition);
      ammoIdsToPositions.clear();
      ammoPositionsToIds.clear();
    }
  }

  private View.OnClickListener onAmmoToggleClickListener = view -> {
    int thisAmmoPosition = ammoIdsToPositions.get(((HeroWeapons) view.getTag()).getItemID());
    for (int i = 0; i < getChildrenCount(lastExpandedGroupPosition); i++) {
      if (thisAmmoPosition != i) {
        ((AmmoViewHolder) weaponsRecyclerView.getChildAt(lastExpandedGroupPosition + 1 + i).getTag()).ammoEquippedIndicator
            .setChecked(false);
      }
    }
    HeroWeapons heroWeapon = (HeroWeapons) getGroup(lastExpandedGroupPosition);
    HeroWeapons selectedAmmo = (HeroWeapons) ((AmmoViewHolder) weaponsRecyclerView
        .getChildAt(lastExpandedGroupPosition + 1 + thisAmmoPosition).getTag()).ammoEquippedIndicator.getTag();
    heroWeapon.setSelectedAmmo(selectedAmmo);
    heroWeapon.setSelectedAmmoId(selectedAmmo.getItemID());
  };

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
