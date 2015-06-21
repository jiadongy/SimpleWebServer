package util;

/**
 * Created by Feiyu on 2015/6/15 0015.
 */
public class Pair<K,V> {
    private K _1;
    private V _2;

    public Pair(K _1,V _2){
        this._1=_1;
        this._2=_2;
    }

    public K _1(){return this._1;}

    public V _2(){return this._2;}

    public void set_1(K _1){this._1=_1;}

    public void set_2(V _2){this._2=_2;}
}
