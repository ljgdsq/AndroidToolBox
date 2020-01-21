package com.example.androidtoolbox.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

public class BitmapCache extends LruCache<String, Bitmap> {


    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public BitmapCache(int maxSize) {
        super(maxSize);
    }

    public BitmapCache(){
        super((int) (Runtime.getRuntime().maxMemory()/(1024*8)));
    }
    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getByteCount()/1024;
    }
}
