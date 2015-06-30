package util;

import util.Pair;

/**
 * Created by Feiyu on 2015/6/30 0030.
 **/
public class Pair3<K,V,L> extends Pair<K,V> {
    private L _3;

    public Pair3(K _1, V _2,L _3) {
        super(_1, _2);
        this._3 = _3;
    }

    public L _3() {
        return _3;
    }

    public void set_3(L _3) {
        this._3 = _3;
    }
}
