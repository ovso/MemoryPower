package net.onepagebook.memorypower.main;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by ovso on 2017. 5. 1..
 */

public abstract class OnSimpleProgressChangeListener implements DiscreteSeekBar.OnProgressChangeListener {


    @Override
    public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

    }

    @Override
    public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {

    }

    @Override
    public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            onStopTrackingTouch(seekBar.getProgress());
    }

    abstract void onStopTrackingTouch(int progress);
}
