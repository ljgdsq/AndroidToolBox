package com.example.androidtoolbox;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Address implements Parcelable {
    private int code;
    private String name;

    public Address() {
    }

    public Address(int code, String name) {
        this.code = code;
        this.name = name;
    }

    protected Address(Parcel in) {
        code = in.readInt();
        name = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return code == address.code &&
                Objects.equals(name, address.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        return "Address{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(name);
    }


    public void readFromParcel(Parcel parcel)
    {
        code = parcel.readInt();
        name = parcel.readString();
    }
}
