package net.sdm.sdm_rpg_world.client.lore.modernui;

import icyllis.modernui.animation.LayoutTransition;
import icyllis.modernui.fragment.Fragment;
import icyllis.modernui.fragment.FragmentContainerView;
import icyllis.modernui.graphics.drawable.ColorDrawable;
import icyllis.modernui.util.DataSet;
import icyllis.modernui.view.Gravity;
import icyllis.modernui.view.LayoutInflater;
import icyllis.modernui.view.View;
import icyllis.modernui.view.ViewGroup;
import icyllis.modernui.widget.FrameLayout;
import icyllis.modernui.widget.LinearLayout;
import icyllis.modernui.widget.ScrollView;
import icyllis.modernui.widget.TextView;
import net.sdm.engine_core.SDMEngineCore;

import static icyllis.modernui.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static icyllis.modernui.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class LoreScreenMUI extends Fragment {
    public static final int id_tab_container = 0x0002;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, DataSet savedInstanceState) {
        var content = new LinearLayout(requireContext());
        content.setOrientation(LinearLayout.VERTICAL);
//        ScrollView base = new ScrollView(this.getContext());
//        base.setId(660);

        TextView textView = new TextView(getContext());
        textView.setText("Hello From Mee");
        content.addView(textView);
        var buttonGroup = new LinearLayout(requireContext());
        buttonGroup.setOrientation(LinearLayout.HORIZONTAL);
        buttonGroup.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        buttonGroup.setLayoutTransition(new LayoutTransition());

        content.addView(buttonGroup, new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        var tabContainer = new FragmentContainerView(requireContext());
        tabContainer.setBackground(new ColorDrawable(255));
        tabContainer.setId(id_tab_container);

        int tabSize = content.dp(340);
        textView = new TextView(getContext());
        textView.setText("Hello From Mee");
        tabContainer.addView(textView);
        content.addView(tabContainer, new LinearLayout.LayoutParams(tabSize, tabSize));

        content.setLayoutParams(new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, Gravity.CENTER));

        return content;
    }
}
