package com.aurora.d20_35_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.viewModels.PlayerCharacterVM;

import androidx.fragment.app.Fragment;

import static com.aurora.d20_35_app.utils.database.TranslationsHolder.translate;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterDescriptionPlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final String HERO_NAME_DESCRIPTION = "hero_name_description";
    private static final String HERO_PLAYER_DESCRIPTION = "hero_player_description";
    private static final String HERO_CLASS_AND_LEVEL_DESCRIPTION = "hero_class_and_level_description";
    private static final String HERO_RACE_DESCRIPTION = "hero_race_description";
    private static final String HERO_ALIGNMENT_DESCRIPTION = "hero_alignment_description";
    private static final String HERO_DEITY_DESCRIPTION = "hero_deity_description";
    private static final String HERO_SIZE_DESCRIPTION = "hero_size_description";
    private static final String HERO_AGE_DESCRIPTION = "hero_age_description";
    private static final String HERO_GENDER_DESCRIPTION = "hero_gender_description";
    private static final String HERO_HEIGHT_DESCRIPTION = "hero_height_description";
    private static final String HERO_WEIGHT_DESCRIPTION = "hero_weight_description";
    private static final String HERO_EYES_DESCRIPTION = "hero_eyes_description";
    private static final String HERO_HAIR_DESCRIPTION = "hero_hair_description";
    private static final String HERO_SKIN_DESCRIPTION = "hero_skin_description";

    private static final String DESCRIPTIONS[] = {
            HERO_NAME_DESCRIPTION,
            HERO_PLAYER_DESCRIPTION,
            HERO_CLASS_AND_LEVEL_DESCRIPTION,
            HERO_RACE_DESCRIPTION,
            HERO_ALIGNMENT_DESCRIPTION,
            HERO_DEITY_DESCRIPTION,
            HERO_SIZE_DESCRIPTION,
            HERO_AGE_DESCRIPTION,
            HERO_GENDER_DESCRIPTION,
            HERO_HEIGHT_DESCRIPTION,
            HERO_WEIGHT_DESCRIPTION,
            HERO_EYES_DESCRIPTION,
            HERO_HAIR_DESCRIPTION,
            HERO_SKIN_DESCRIPTION};

    private static final int ID_VALUES[] = {
            R.id.fragment_player_character_description_entry_name,
            R.id.fragment_player_character_description_entry_player,
            R.id.fragment_player_character_description_entry_class_and_level,
            R.id.fragment_player_character_description_entry_race,
            R.id.fragment_player_character_description_entry_alignment,
            R.id.fragment_player_character_description_entry_deity,
            R.id.fragment_player_character_description_entry_size,
            R.id.fragment_player_character_description_entry_age,
            R.id.fragment_player_character_description_entry_gender,
            R.id.fragment_player_character_description_entry_height,
            R.id.fragment_player_character_description_entry_weight,
            R.id.fragment_player_character_description_entry_eyes,
            R.id.fragment_player_character_description_entry_hair,
            R.id.fragment_player_character_description_entry_skin
    };

    private PlayerCharacterVM playerCharacterVM;

    public PlayerCharacterDescriptionPlaceholderFragment() {
    }

    static PlayerCharacterDescriptionPlaceholderFragment newInstance(int sectionNumber, PlayerCharacterVM playerCharacterVM) {
        PlayerCharacterDescriptionPlaceholderFragment fragment = new PlayerCharacterDescriptionPlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        fragment.playerCharacterVM = playerCharacterVM;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_character_description, container, false);
        loadHeroDataFromVMtoView(rootView);
        return rootView;
    }

    private void loadHeroDataFromVMtoView(View rootView) {
        RelativeLayout entryOuter;
        String textValues[] = playerCharacterVM.getHeroTextValues();

        for (int i = 0; i < ID_VALUES.length; i++) {
            entryOuter = (RelativeLayout) rootView.findViewById(ID_VALUES[i]);
            setEntry(entryOuter, DESCRIPTIONS[i], textValues[i]);
        }
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
    }

    private void setEntry(RelativeLayout entryOuter, String description, String textToSet) {
        TextView entryTextView;
        entryTextView = (TextView) entryOuter.findViewById(R.id.fragment_player_character_description_entry_description);
        entryTextView.setText(translate(description));
        entryTextView = (TextView) entryOuter.findViewById(R.id.fragment_player_character_description_entry_text);
        entryTextView.setText(translate(textToSet));
    }
}
