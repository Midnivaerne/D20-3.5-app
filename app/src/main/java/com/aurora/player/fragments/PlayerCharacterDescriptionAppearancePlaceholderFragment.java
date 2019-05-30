package com.aurora.player.fragments;

import static com.aurora.core.database.TranslationsHolder.translate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.aurora.core.R;
import com.aurora.player.playerCharacterUtils.PlayerCharacterDescriptionsEnum;
import com.aurora.player.viewmodels.PlayerCharacterVM;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterDescriptionAppearancePlaceholderFragment extends Fragment {

  private static final String ARG_SECTION_NUMBER = "section_number";

  private PlayerCharacterVM playerCharacterVM;

  public PlayerCharacterDescriptionAppearancePlaceholderFragment() {
  }

  public static PlayerCharacterDescriptionAppearancePlaceholderFragment newInstance(int sectionNumber,
      PlayerCharacterVM playerCharacterVM) {
    PlayerCharacterDescriptionAppearancePlaceholderFragment fragment = new PlayerCharacterDescriptionAppearancePlaceholderFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    fragment.playerCharacterVM = playerCharacterVM;
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_player_character_description_appearance, container, false);
    loadHeroDataFromVMtoView(rootView);
    return rootView;
  }

  private void loadHeroDataFromVMtoView(View rootView) {
    RelativeLayout entryOuter;
    for (PlayerCharacterDescriptionsEnum description : PlayerCharacterDescriptionsEnum.values()) {
      entryOuter = rootView.findViewById(description.getFieldId());
      ((TextView) entryOuter.findViewById(R.id.fragment_text_value_with_description_box_description))
          .setText(translate(description.toString()));
      ((TextView) entryOuter.findViewById(R.id.fragment_text_value_with_description_box_text))
          .setText(translate(playerCharacterVM.getHero().getTextDescriptions().get(description)));
    }
    //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
  }

}
