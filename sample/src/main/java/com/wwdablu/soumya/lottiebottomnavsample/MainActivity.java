package com.wwdablu.soumya.lottiebottomnavsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.TypefaceCompat;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;

import com.wwdablu.soumya.lottiebottomnav.FontBuilder;
import com.wwdablu.soumya.lottiebottomnav.FontItem;
import com.wwdablu.soumya.lottiebottomnav.ILottieBottomNavCallback;
import com.wwdablu.soumya.lottiebottomnav.LottieBottomNav;
import com.wwdablu.soumya.lottiebottomnav.MenuItem;
import com.wwdablu.soumya.lottiebottomnav.MenuItemBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ILottieBottomNavCallback, MailFragment.ClickHandler {

    private LottieBottomNav bottomNav;
    private ArrayList<MenuItem> list;

    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_nav);

        mFragments = new ArrayList<>(5);

        mFragments.add(new HomeFragment());
        mFragments.add(new GiftFragment());

        MailFragment mailFragment = new MailFragment();
        mailFragment.setClickHandler(this);
        mFragments.add(mailFragment);

        mFragments.add(new SettingsFragment());
        mFragments.add(new SettingsFragment());

        FontItem fontItem = FontBuilder.create("·")
                .build();

        MenuItem item1 = MenuItemBuilder.create("tab_map.json", MenuItem.Source.Assets, fontItem, "dash")
                .pausedProgress(0)
                .loop(false)
                .build();

        MenuItem item2 = MenuItemBuilder.createFrom(item1, fontItem)
                .selectedLottieName("tab_find.json")
                .unSelectedLottieName("tab_find.json")
                .pausedProgress(0)
                .build();

        MenuItem item3 = MenuItemBuilder.createFrom(item1, fontItem)
                .selectedLottieName("tab_car.json")
                .unSelectedLottieName("tab_car.json")
                .pausedProgress(0)
                .build();

        MenuItem item4 = MenuItemBuilder.createFrom(item1, fontItem)
                .selectedLottieName("tab_store.json")
                .unSelectedLottieName("tab_store.json")
                .pausedProgress(0)
                .build();

        MenuItem item5 = MenuItemBuilder.createFrom(item1, fontItem)
                .selectedLottieName("tab_me.json")
                .unSelectedLottieName("tab_me.json")
                .pausedProgress(0)
                .build();


        list = new ArrayList<>(4);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);

        bottomNav.setCallback(this);
        bottomNav.setMenuItemList(list);
        bottomNav.setSelectedIndex(1);
    }

    private void updateResource(boolean isDark) {
        //TODO 修改 第一个tab 样式
        bottomNav.getMenuItemFor(0).setSelectedLottieName(isDark ? "message_cupid.json" : "settings.json");
        bottomNav.getMenuItemFor(0).setUnselectedLottieName(isDark ? "message_cupid.json" : "settings.json");

        bottomNav.getMenuItemFor(1).setSelectedLottieName(isDark ? "tab_find_dark.json" : "tab_find.json");
        bottomNav.getMenuItemFor(1).setUnselectedLottieName(isDark ? "tab_find_dark.json" : "tab_find.json");
        bottomNav.getMenuItemFor(2).setSelectedLottieName(isDark ? "tab_car_dark.json" : "tab_car.json");
        bottomNav.getMenuItemFor(2).setUnselectedLottieName(isDark ? "tab_car_dark.json" : "tab_car.json");
        bottomNav.getMenuItemFor(3).setSelectedLottieName(isDark ? "tab_store_dark.json" : "tab_store.json");
        bottomNav.getMenuItemFor(3).setUnselectedLottieName(isDark ? "tab_store_dark.json" : "tab_store.json");
        bottomNav.getMenuItemFor(4).setSelectedLottieName(isDark ? "tab_me_dark.json" : "tab_me.json");
        bottomNav.getMenuItemFor(4).setUnselectedLottieName(isDark ? "tab_me_dark.json" : "tab_me.json");
    }

    @Override
    public void onMenuSelectedStart(int newIndex) {
        if (newIndex == 2) {
            bottomNav.setBackgroundColor(Color.parseColor("#18191C"));
            updateResource(true);
        } else {
            updateResource(false);
            bottomNav.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public void onMenuSelected(int oldIndex, int newIndex, MenuItem menuItem) {


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_frag_container, mFragments.get(newIndex), mFragments.get(newIndex).getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    @Override
    public void onAnimationStart(int index, MenuItem menuItem) {
        //
    }

    @Override
    public void onAnimationEnd(int index, MenuItem menuItem) {

        if (index == 2 && menuItem.getTag() != null && "cupid".equalsIgnoreCase(menuItem.getTag().toString())) {
            restoreMenuForMessage(menuItem);
        }
    }

    @Override
    public void onAnimationCancel(int index, MenuItem menuItem) {
        if (index == 2 && menuItem.getTag() != null && "cupid".equalsIgnoreCase(menuItem.getTag().toString())) {
            restoreMenuForMessage(menuItem);
        }
    }

    private void restoreMenuForMessage(MenuItem menuItem) {
        MenuItem item = MenuItemBuilder.createFrom(menuItem)
                .selectedLottieName("message.json")
                .build();

        bottomNav.updateMenuItemFor(2, item);
    }

    @Override
    public void onChangeMenuIcon() {


//        if (bottomNav.getSelectedIndex() == 2) {
//
//            MenuItem cupidMessage = MenuItemBuilder.createFrom(bottomNav.getMenuItemFor(2))
//                    .selectedLottieName("message_cupid.json")
//                    .tag("cupid")
//                    .build();
//
//            bottomNav.updateMenuItemFor(2, cupidMessage);
//        }
    }
}
