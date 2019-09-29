// IAddress.aidl
package com.example.androidtoolbox;
// Declare any non-default types here with import statements
import com.example.androidtoolbox.Address;
interface IAddress {
      void queryAddress(int i,out Address add);
      Address queryById(int id);
}
