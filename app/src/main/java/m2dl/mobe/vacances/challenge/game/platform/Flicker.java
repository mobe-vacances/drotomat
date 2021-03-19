package m2dl.mobe.vacances.challenge.game.platform;

import java.util.ArrayList;
import java.util.List;

import m2dl.mobe.vacances.challenge.game.mobengine.core.Updatable;

public class Flicker implements Updatable {

    private static List<PlateformeFlick> plateformeFlickList = new ArrayList<>();

    public static void addPlateforme(PlateformeFlick p){
        plateformeFlickList.add(p);
    }

    public static void switchAll(){
        for(int i = 0 ; i < plateformeFlickList.size() ; i++){
            plateformeFlickList.get(i).switchPlateform();
        }
    }

    @Override
    public void update(int delta) {

    }
}