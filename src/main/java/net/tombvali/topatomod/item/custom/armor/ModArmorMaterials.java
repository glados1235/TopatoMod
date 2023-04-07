package net.tombvali.topatomod.item.custom.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.item.ModItems;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {

    STARDUST("stardust", 22, new int[]{2, 4, 4, 2}, 20, SoundEvents.ARMOR_EQUIP_GOLD,
            1.5F, 0F, () -> {
        return Ingredient.of(ModItems.STARDUST_INGOT.get());
    });


    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModArmorMaterials(String p_40474_, int i, int[] ints, int i1, SoundEvent soundEvent, float v, float v1, Supplier<Ingredient> supplier) {
        this.name = p_40474_;
        this.durabilityMultiplier = i;
        this.slotProtections = ints;
        this.enchantmentValue = i1;
        this.sound = soundEvent;
        this.toughness = v;
        this.knockbackResistance = v1;
        this.repairIngredient = new LazyLoadedValue<>(supplier);
    }

    public int getDurabilityForSlot(EquipmentSlot p_40484_) {
        return HEALTH_PER_SLOT[p_40484_.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot p_40487_) {
        return this.slotProtections[p_40487_.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return TopatoMod.MODID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
