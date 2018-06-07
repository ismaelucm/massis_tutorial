package com.massisframework.massis3.examples.simulator.World;

public class SimulationVertice {

    public static int MIN_TRANSPORT_RATE = 1;
    public static int MAX_TRANSPORT_RATE = 30;

    private int _transportRate;

    public boolean isTransporRateBlocked() {
        return _transporRateBlocked;
    }

    public void setTransporRateBlocked(boolean _transporRateBlocked) {
        this._transporRateBlocked = _transporRateBlocked;
    }

    private boolean _transporRateBlocked;

    public SimulationVertice() {
        _transportRate = 0;
    }


    public SimulationVertice(int rate) {
        _transportRate = rate;
    }

    public int get_transportRate() {
        return _transportRate;
    }

    public void set_transportRate(int _transportRate) {
        this._transportRate = _transportRate;
    }
}
