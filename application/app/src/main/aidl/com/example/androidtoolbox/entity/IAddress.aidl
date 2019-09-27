// IAddress.aidl
package com.example.androidtoolbox.entity;
// Declare any non-default types here with import statements
import com.example.androidtoolbox.entity.Address;
interface IAddress {
      void queryAddress(out Address add);
      Address queryById(int id);
}
