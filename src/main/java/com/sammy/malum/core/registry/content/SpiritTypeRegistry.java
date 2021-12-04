package com.sammy.malum.core.registry.content;

import com.sammy.malum.MalumHelper;
import com.sammy.malum.MalumMod;
import com.sammy.malum.core.registry.items.ItemRegistry;
import com.sammy.malum.core.systems.spirit.MalumSpiritType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
@Mod.EventBusSubscriber(modid= MalumMod.MODID, value= Dist.CLIENT, bus= Mod.EventBusSubscriber.Bus.MOD)
public class SpiritTypeRegistry
{
    public static ArrayList<MalumSpiritType> SPIRITS = new ArrayList<>();

    public static final Color SACRED_SPIRIT_COLOR = new Color(234, 73, 95);
    public static MalumSpiritType SACRED_SPIRIT = create("sacred", SACRED_SPIRIT_COLOR, ItemRegistry.SACRED_SPIRIT);

    public static final Color WICKED_SPIRIT_COLOR = new Color(178, 29, 232);
    public static MalumSpiritType WICKED_SPIRIT = create("wicked", WICKED_SPIRIT_COLOR, ItemRegistry.WICKED_SPIRIT);

    public static final Color ARCANE_SPIRIT_COLOR = new Color(231, 68, 196);
    public static MalumSpiritType ARCANE_SPIRIT = create("arcane", ARCANE_SPIRIT_COLOR, ItemRegistry.ARCANE_SPIRIT);

    public static final Color ELDRITCH_SPIRIT_COLOR = new Color(142, 62, 234, 255);
    public static MalumSpiritType ELDRITCH_SPIRIT = create("eldritch", ELDRITCH_SPIRIT_COLOR, ItemRegistry.ELDRITCH_SPIRIT);

    public static final Color AERIAL_SPIRIT_COLOR = new Color(51, 229, 155);
    public static MalumSpiritType AERIAL_SPIRIT = create("aerial", AERIAL_SPIRIT_COLOR, ItemRegistry.AERIAL_SPIRIT);

    public static final Color AQUEOUS_SPIRIT = new Color(42, 114, 232);
    public static MalumSpiritType AQUEOUS_SPIRIT_COLOR = create("aqueous", AQUEOUS_SPIRIT, ItemRegistry.AQUEOUS_SPIRIT);

    public static final Color INFERNAL_SPIRIT_COLOR = new Color(210, 134, 39);
    public static MalumSpiritType INFERNAL_SPIRIT = create("infernal", INFERNAL_SPIRIT_COLOR, ItemRegistry.INFERNAL_SPIRIT);

    public static final Color EARTHEN_SPIRIT_COLOR = new Color(98, 180, 40);
    public static MalumSpiritType EARTHEN_SPIRIT = create("earthen", EARTHEN_SPIRIT_COLOR, ItemRegistry.EARTHEN_SPIRIT);

    public static MalumSpiritType create(String identifier, Color color, RegistryObject<Item> splinterItem)
    {
        MalumSpiritType spiritType = new MalumSpiritType(identifier, color, splinterItem);
        SPIRITS.add(spiritType);
        return spiritType;
    }
    @SubscribeEvent
    public static void stitchTextures(TextureStitchEvent.Pre event) {
        if (!event.getMap().getTextureLocation().equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE)) {
            return;
        }
        SPIRITS.forEach(s ->
        {
            event.addSprite(MalumHelper.prefix("spirit/" + "overlay_" + s.identifier));
            event.addSprite(MalumHelper.prefix("spirit/" + "cutout_" + s.identifier));
            event.addSprite(MalumHelper.prefix("spirit/" + "corrupted_cutout_" + s.identifier));
        });
    }
}