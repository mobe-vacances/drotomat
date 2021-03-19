package m2dl.mobe.vacances.challenge.game.mobengine.resource_stores;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoundStore {

    private static final Map<Integer, MediaPlayer> mediaPlayerMap = new HashMap<>();

    private static final List<MediaPlayer> pausedMediaPlayers = new ArrayList<>();

    private static float masterVolume = 1.0f;

    public static void createMediaPlayers(int[] soundIds, Context context) {
        for(Integer soundId : soundIds) {
            mediaPlayerMap.put(soundId, MediaPlayer.create(context, soundId));
        }
    }

    public static void releaseMediaPlayers() {
        for(MediaPlayer mp : mediaPlayerMap.values()) {
            mp.release();
        }
        mediaPlayerMap.clear();
    }

    public static void playSound(int soundId, float volume) {
        mediaPlayerMap.get(soundId).setVolume(masterVolume*volume, masterVolume*volume);
        mediaPlayerMap.get(soundId).seekTo(0);
        mediaPlayerMap.get(soundId).start();
    }

    public static void loopSound(int soundId, float volume) {
        mediaPlayerMap.get(soundId).setLooping(true);
        mediaPlayerMap.get(soundId).setVolume(masterVolume*volume, masterVolume*volume);
        mediaPlayerMap.get(soundId).seekTo(0);
        mediaPlayerMap.get(soundId).start();

    }

    public static void stopLoopedSound(int soundId) {
        mediaPlayerMap.get(soundId).setLooping(false);
        mediaPlayerMap.get(soundId).pause();
    }

    public static void pauseAll() {
        for(MediaPlayer mp : mediaPlayerMap.values()) {
            if(mp.isPlaying()) {
                mp.pause();
                pausedMediaPlayers.add(mp);
            }
        }
    }

    public static void startAllPaused() {
        for (MediaPlayer mp : pausedMediaPlayers) {
            mp.start();
        }
        pausedMediaPlayers.clear();
    }

    public static float getMasterVolume() {
        return masterVolume;
    }

    public static void setMasterVolume(float masterVolume) {
        SoundStore.masterVolume = masterVolume;
    }

    public static Map<Integer, MediaPlayer> getMediaPlayerMap() {
        return mediaPlayerMap;
    }
}
