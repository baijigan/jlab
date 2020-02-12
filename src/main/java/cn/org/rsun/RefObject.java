package cn.org.rsun;

import java.util.ArrayList;
import java.util.List;

public class RefObject {
    public List<Object> list;
    public jlab lab;
    RefObject( jlab lab ){
        this.lab= lab;

        list= new ArrayList<Object>();
        list.add( lab );
        System.out.println("new RefObject ( lab )");
    }

    public void free( ){
        this.lab= null;
        this.list= null;
        System.out.println("new RefObject ( free )");
    }
}
