package com.example.androidtoolbox.entity;

import java.util.Objects;

public  class Anim {
    private String name;
    private String desc;
    private int iconId;

    public Anim(String name, String desc, int iconId) {
        this.name = name;
        this.desc = desc;
        this.iconId = iconId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    @Override
    public String toString() {
        return "Anim{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", iconId=" + iconId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anim anim = (Anim) o;
        return iconId == anim.iconId &&
                Objects.equals(name, anim.name) &&
                Objects.equals(desc, anim.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, iconId);
    }
}
