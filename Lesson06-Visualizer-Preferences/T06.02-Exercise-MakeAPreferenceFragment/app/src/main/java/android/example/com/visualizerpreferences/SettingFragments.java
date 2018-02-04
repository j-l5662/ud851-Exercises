package android.example.com.visualizerpreferences;

/**
 * Created on 1/28/2018.
 */

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class SettingFragments extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_visualizer);
    }
}
