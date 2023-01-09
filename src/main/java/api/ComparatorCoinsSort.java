package api;

import api.pojoModels.AllTikersData;

import java.util.Comparator;

public class ComparatorCoinsSort implements Comparator<AllTikersData> {
    @Override
    public int compare(AllTikersData o1, AllTikersData o2) {
        int result = Float.compare(Float.parseFloat(o2.getChangeRate()), Float.parseFloat(o1.getChangeRate()));
        return result;
    }
}
