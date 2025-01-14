package com.xiaolin233.Caneer;

import org.bukkit.Material;

public enum Career {
    //职业的添加
    Swordsman("Swordsman",Material.DIAMOND_SWORD, new String[]{"获得钻石帽子和一把钻石剑,还有16个普通金苹果"}),
    Fisherman("Fisherman",Material.FISHING_ROD, new String[]{"获得一个鱼竿,一把铁剑，还有16个普通金苹果"});

    private String name;
    private Material material;
    private String[] desprition;
    private Career(String name, Material material,String[] desprition){
        this.name = name;
        this.material = material;
        this.desprition = desprition;
    }

    public String getName(){
        return this.name;
    }
    public Material getMaterial(){
        return this.material;
    }
    public String[] getDesprition(){
        return this.desprition;
    }
}
