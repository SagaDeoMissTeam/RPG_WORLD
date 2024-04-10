package net.sdm.sdm_rpg_world.modules.rpg.item.genItems;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.openzen.zencode.java.ZenCodeType;


@ZenRegister
@ZenCodeType.Name("mods.rpgworld.rpg.item.GenItemStack")
public class GenItemStack {
    public ItemStack item;
    public double chance;
    public CompoundTag nbt;

    @ZenCodeType.Constructor
    public GenItemStack(ItemStack item, double chance, CompoundTag nbt){
        this.item = item;
        this.chance = chance;
        this.nbt = nbt;
    }

    @ZenCodeType.Method
    public static GenItemStack of(ItemStack item, double chance){
        return of(item, chance, new CompoundTag());
    }
    @ZenCodeType.Method
    public static GenItemStack of(ItemStack item, double chance, CompoundTag nbt){
        return new GenItemStack(item, chance, nbt);
    }
}
