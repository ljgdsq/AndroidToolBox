// IPerson.aidl
package com.example.androidtoolbox.aidl;

// Declare any non-default types here with import statements

interface IPerson {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

   java.lang.String queryName(int id);
}
