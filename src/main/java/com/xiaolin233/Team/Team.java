package com.xiaolin233.Team;

import org.bukkit.Material;

public enum Team {
    RED("RED",Material.REDSTONE),
    BLUE("BULE",Material.DIAMOND_BLOCK),
    GREEN("GREEN",Material.GREEN_RECORD);
    private String name;
    private Material material;
    private Team(String name, Material material){
        this.name = name;
        this.material = material;
    }

    public String getName() {
        return name;
    }
    public Material getMaterial() {
        return material;
    }
}
