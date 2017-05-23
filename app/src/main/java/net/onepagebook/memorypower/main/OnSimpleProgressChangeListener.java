package net.onepagebook.memorypower.main;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

abstract class OnSimpleProgressChangeListener implements DiscreteSeekBar.OnProgressChangeListener {


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

    public abstract void onStopTrackingTouch(int progress);
}
