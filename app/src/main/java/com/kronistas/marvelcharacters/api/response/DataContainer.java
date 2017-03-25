package com.kronistas.marvelcharacters.api.response;

import com.kronistas.marvelcharacters.api.vo.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trey Robinson on 2/13/14.
 */
public class DataContainer <E> {

    public int offset;
    public int limit;
    public int total;
    public int count;
    public ArrayList<E> results;

}
