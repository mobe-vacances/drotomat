package m2dl.mobe.vacances.challenge.game.mobengine.utils;


import m2dl.mobe.vacances.challenge.game.mobengine.resource_stores.SoundStore;

public class ActiveActivitiesTracker {
    private static int nActivities = 0;

    public static void activityStarted()
    {
        if( nActivities == 0 ) {
            SoundStore.startAllPaused();
        }
        nActivities++;
    }

    public static void activityStopped()
    {
        nActivities--;
        if( nActivities == 0 ) {
            SoundStore.pauseAll();
        }
    }
}
