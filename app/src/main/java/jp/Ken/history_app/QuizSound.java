package jp.Ken.history_app;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class QuizSound {
    private static SoundPool soundPool;
    private static int ct_ans;
    private static int it_ans;
    private static int next_bgm;
    private static int home_bgm;

    public QuizSound(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder ( )
                    .setUsage ( AudioAttributes.USAGE_GAME )
                    .setContentType ( AudioAttributes.CONTENT_TYPE_MUSIC )
                    .build ( );

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes( audioAttributes )
                    .setMaxStreams(2)
                    .build();

        } else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }

        ct_ans = soundPool.load ( context,R.raw.quiz_bgm1,1 );
        it_ans = soundPool.load ( context,R.raw.quiz_bgm2,1 );
        next_bgm = soundPool.load ( context,R.raw.tugi,1 );
        home_bgm = soundPool.load ( context,R.raw.home,1 );


    }

    public void play_ct_ans() {
        soundPool.play(ct_ans, 1.0f, 1.0f, 1, 0, 0.9f);
    }

    public void play_it_ans() {
        soundPool.play( it_ans , 1.0f, 1.0f, 1, 0, 0.9f);
    }

    public void play_next_bgm() {
        soundPool.play( next_bgm , 1.0f, 1.0f, 1, 0, 0.9f);
    }

    public void play_home_bgm() {
        soundPool.play( home_bgm , 1.0f, 1.0f, 1, 0, 0.9f);
    }

}


