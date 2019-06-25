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
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import com.aurora.core.R;
import com.aurora.core.models.userdata.HeroPlayer;
import com.aurora.core.models.userdata.HeroWeapons;

public class EquipmentWeaponsRecyclerViewAdapter extends BaseExpandableListAdapter implements OnChildClickListener,
    OnGroupClickListener {

  private final Context context;
  private final HeroPlayer playerHero;
  private final LinkedHashMap<HeroWeapons, List<HeroWeapons>> heroWeaponsAmmoMap;
  private final ArrayList<HeroWeapons> weaponsList;

  public EquipmentWeaponsRecyclerViewAdapter(Context context, HeroPlayer playerHero) {
    this.context = context;
    this.playerHero = playerHero;
    heroWeaponsAmmoMap = playerHero.getHeroWeaponsMap();
    weaponsList = playerHero.getHeroWeapons();
  }

  @Override
  public int getGroupCount() {
    return weaponsList != null ? weaponsList.size() : 0;
  }

  @Override
  public int getChildrenCount(int weaponPosition) {
    return heroWeaponsAmmoMap!=null?heroWeaponsAmmoMap.get(weaponsList.get(weaponPosition)).size():0;
  }

  @Override
  public Object getGroup(int weaponPosition) {
    return weaponsList.get(weaponPosition);
  }

  @Override
  public Object getChild(int weaponPosition, int ammoPosition) {
    return heroWeaponsAmmoMap.get(weaponPosition).get(ammoPosition);
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
    return view;
  }

  @Override
  public View getChildView(int weaponPosition, int ammoPosition, boolean isLastChild, View view, ViewGroup parent) {
    HeroWeapons weaponAmmo = (HeroWeapons) getChild(weaponPosition, ammoPosition);
    WeaponViewHolder ammoViewHolder;//todo change to ammo?
    if (view == null) {
      //LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //view = layoutInflater.inflate(R.layout.fragment_player_character_equipment_weapons_weapon, null);
      view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.fragment_player_character_equipment_weapons_weapon, parent, false); //todo change to ammo?
      ammoViewHolder = new WeaponViewHolder(view, weaponAmmo);
      view.setTag(ammoViewHolder);
    }
    ammoViewHolder = (WeaponViewHolder) view.getTag();
    return view;
  }

  @Override
  public boolean isChildSelectable(int weaponPosition, int ammoPosition) {
    return false;
  }

  @Override
  public boolean onChildClick(ExpandableListView parent, View v, int weaponPosition, int ammoPosition, long id) {
    return false;
  }

  @Override
  public boolean onGroupClick(ExpandableListView parent, View v, int weaponPosition, long id) {
    return false;
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
      bind(heroWeapon);
    }

    public void bind(HeroWeapons heroWeapon) {
      weaponEquippedIndicator.setTag(heroWeapon);
      //weaponEquippedIndicator.setOnClickListener(onClickListener);

      weaponName.setText(translate(heroWeapon.getName()));
      weaponName.setTag(heroWeapon);
      //weaponName.setOnClickListener(onClickListener);

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
      //weaponRangedExpander.setOnClickListener(onClickExpanderListener);
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
