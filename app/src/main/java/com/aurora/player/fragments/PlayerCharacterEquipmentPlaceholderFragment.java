package com.aurora.player.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import lombok.NonNull;

import com.aurora.core.R;
import com.aurora.core.database.models.userdata.HeroArmour;
import com.aurora.player.adapters.PlayerCharacterEquipmentContainersContainerAdapter;
import com.aurora.player.adapters.PlayerCharacterEquipmentShieldsShieldAdapter;
import com.aurora.player.adapters.PlayerCharacterEquipmentWeaponsWeaponAdapter;
import com.aurora.player.playercharacterutils.PlayerCharacterArmourEnum;
import com.aurora.player.playercharacterutils.PlayerCharacterArmourSpecificEnum;
import com.aurora.player.playercharacterutils.PlayerCharacterWornEquipmentPlacesEnum;
import com.aurora.player.playercharacterutils.PlayerCharacterWornEquipmentPlacesSpecificEnum;
import com.aurora.player.viewmodels.PlayerCharacterVM;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterEquipmentPlaceholderFragment extends Fragment {

  /**
   * The fragment argument representing the section number for this fragment.
   */
  private static final String ARG_SECTION_NUMBER = "section_number";

  private PlayerCharacterVM playerCharacterVM;

  public PlayerCharacterEquipmentPlaceholderFragment() {
  }

  /**
   * Returns a new instance of this fragment for the given section number.
   */
  public static PlayerCharacterEquipmentPlaceholderFragment newInstance(int sectionNumber, PlayerCharacterVM playerCharacterVM) {
    PlayerCharacterEquipmentPlaceholderFragment fragment = new PlayerCharacterEquipmentPlaceholderFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    fragment.playerCharacterVM = playerCharacterVM;
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_player_character_equipment, container, false);
    loadHeroDataFromVMtoView(rootView);
    return rootView;
  }

  private void loadHeroDataFromVMtoView(View rootView) {
    loadWeapons(rootView.findViewById(R.id.fragment_player_character_equipment_weapons));
    loadArmour(rootView.findViewById(R.id.fragment_player_character_equipment_armour));
    loadShield(rootView.findViewById(R.id.fragment_player_character_equipment_shields));
    loadWorn(rootView.findViewById(R.id.fragment_player_character_equipment_worn));
    loadContainers(rootView.findViewById(R.id.fragment_player_character_equipment_containers));
  }

  private void loadWeapons(@NonNull View view) {
    ExpandableListView weaponsRecyclerView = (ExpandableListView) view;
    PlayerCharacterEquipmentWeaponsWeaponAdapter playerCharacterEquipmentWeaponsWeaponAdapter = new PlayerCharacterEquipmentWeaponsWeaponAdapter(
        this.getContext(), weaponsRecyclerView, playerCharacterVM.getHero());
    weaponsRecyclerView.setAdapter(playerCharacterEquipmentWeaponsWeaponAdapter);
    playerCharacterEquipmentWeaponsWeaponAdapter.setListHeight();
  }

  private void loadArmour(@NonNull View armoursRecyclerView) {
    if (playerCharacterVM.getHero().getWornItemId() != null) {
      armoursRecyclerView.setVisibility(View.VISIBLE);
      Integer wornArmourId = playerCharacterVM.getHero().getWornItemId();
      HeroArmour wornArmour = playerCharacterVM.getHero().getHeroArmourFromIdMap().get(wornArmourId);
      for (PlayerCharacterArmourEnum armourEnum : PlayerCharacterArmourEnum.values()) {
        if (wornArmour.getArmourValues().get(armourEnum) != null) {
          for (PlayerCharacterArmourSpecificEnum armourSpecific : PlayerCharacterArmourSpecificEnum.values()) {
            ((TextView) armoursRecyclerView.findViewById(armourEnum.getFieldId())
                .findViewById(armourSpecific.getSpecificFieldId(armourEnum)))
                .setText(armourSpecific.getSpecificValue(armourEnum, wornArmour));
          }
        }
      }
    } else {
      armoursRecyclerView.setVisibility(View.GONE);
    }
  }

  private void loadShield(@NonNull View view) {
    RecyclerView shieldsListView = (RecyclerView) view.findViewById(R.id.fragment_player_character_equipment_shields_list);
    PlayerCharacterEquipmentShieldsShieldAdapter playerCharacterEquipmentShieldsShieldAdapter = new PlayerCharacterEquipmentShieldsShieldAdapter(
        this.getContext(), shieldsListView, playerCharacterVM.getHero());
    shieldsListView.setAdapter(playerCharacterEquipmentShieldsShieldAdapter);
  }

  private void loadWorn(@NonNull View wornRecyclerView) {
    if (!playerCharacterVM.getHero().getHeroPlaceEquipmentMap().isEmpty()) {
      for (PlayerCharacterWornEquipmentPlacesEnum wornWhere : PlayerCharacterWornEquipmentPlacesEnum.values()) {
        if (playerCharacterVM.getHero().getHeroPlaceEquipmentMap().get(wornWhere) != null) {
          for (PlayerCharacterWornEquipmentPlacesSpecificEnum wornWhereSpecific : PlayerCharacterWornEquipmentPlacesSpecificEnum.values()) {
            ((TextView) wornRecyclerView.findViewById(wornWhere.getFieldId()).findViewById(wornWhereSpecific.getSpecificFieldId(wornWhere)))
                .setText(
                    wornWhereSpecific.getSpecificValue(wornWhere, playerCharacterVM.getHero().getHeroPlaceEquipmentMap().get(wornWhere)));
          }
        }
      }
    }
  }

  private void loadContainers(@NonNull View containersRecyclerView) {
    if (!playerCharacterVM.getHero().getHeroContainerEquipmentMap().isEmpty()) {
      ((ExpandableListView) containersRecyclerView)
          .setAdapter(new PlayerCharacterEquipmentContainersContainerAdapter(this.getContext(), playerCharacterVM.getHero()));
    }
  }
}
