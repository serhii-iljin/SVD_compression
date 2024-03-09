package com.compression.compression_svd;



/**
 * Defines the behavior for classes with objects that can be scaled.
 */

public interface Scalable
{
    /**
     * Scales object to the current window size.
     */
    void addResizeListener();
}
