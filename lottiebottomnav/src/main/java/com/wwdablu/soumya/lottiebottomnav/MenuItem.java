package com.wwdablu.soumya.lottiebottomnav;

public class MenuItem {

    public enum Source {
        Assets,
        Raw
    }

    FontItem fontItem;

    String selectedLottieName;
    String unselectedLottieName;

    public String getSelectedLottieName() {
        return selectedLottieName;
    }

    public void setSelectedLottieName(String selectedLottieName) {
        this.selectedLottieName = selectedLottieName;
    }

    public String getUnselectedLottieName() {
        return unselectedLottieName;
    }

    public void setUnselectedLottieName(String unselectedLottieName) {
        this.unselectedLottieName = unselectedLottieName;
    }

    Source lottieSource;
    float  lottieProgress;

    boolean loop;

    Object tag;

    MenuItem() {}

    public Object getTag() {
        return tag;
    }
}
