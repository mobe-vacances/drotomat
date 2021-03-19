package m2dl.mobe.vacances.challenge.game;

import m2dl.mobe.vacances.challenge.R;

public class Constants {

    public static final int[] USED_BITMAPs_IDS = {
            R.drawable.player_jump,
            R.drawable.player_jump_revert,
            R.drawable.player_1,
            R.drawable.player_2,
            R.drawable.player_3,
            R.drawable.player_1_revert,
            R.drawable.player_2_revert,
            R.drawable.player_3_revert,
            R.drawable.exit,
    };

    public static final int[] USED_SOUNDS_IDS = {
            R.raw.menu
    };


    public static final float VOLUME_MENU_MUSIC = 0.9f;

    public static final int ACCELERATION_THRESHOLD = 15;
    public static final long TIME_BETWEEN_SHAKE_RESETS = 500;
    public static final long TIME_BETWEEN_LIGHT_EVENTS = 1000;
    public static final long LIGHT_THRESHOLD_PERCENT = 20;

    public static final int PLAYER_WIDTH = 50;
    public static final int PLAYER_HEIGHT = 100;
    public static final float PLAYER_MAX_X_SPEED = 1f;
    public static final float PLAYER_MAX_Y_SPEED = 1f;
    public static final float PLAYER_JUMP_ACCELERATION = -0.02f;
    public static final float PLAYER_GRAVITY = 0.00065f;
    public static final float PLAYER_Y_INERTIA = 0.0003f;
    public static final float PLAYER_X_INERTIA = 0.0003f;
    public static final float PLAYER_X_ACCELERATION = 0.00005f;

    public static final int Z_INDEX_PLAYER = 100;

    public static final int BACKGROUND_Z_INDEX = 0;
    public static final int LEVEL_Z_INDEX = 152;
}
