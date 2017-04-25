package net.onepagebook.memorypower.home;

import android.os.CountDownTimer;

import net.onepagebook.memorypower.common.Log;

/**
 * Created by ovso on 2017. 4. 26..
 */

public class SimpleCountDownTimer extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and
     *                          {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public SimpleCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        Log.d("millisUntilFinished = " + millisUntilFinished);
    }

    @Override
    public void onFinish() {
    }


}
