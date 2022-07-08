package com.sammy.malum.common.effect;

import com.sammy.malum.common.capability.MalumPlayerDataCapability;
import com.sammy.malum.core.setup.content.AttributeRegistry;
import com.sammy.malum.core.setup.content.SoundRegistry;
import com.sammy.malum.core.setup.content.potion.MalumMobEffectRegistry;
import com.sammy.ortus.helpers.ColorHelper;
import com.sammy.ortus.helpers.EntityHelper;
import com.sammy.ortus.setup.OrtusAttributeRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;

import static com.sammy.malum.core.setup.content.item.ItemTagRegistry.GROSS_FOODS;

public class GluttonyEffect extends MobEffect {
    public GluttonyEffect() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(88, 86, 60));
        addAttributeModifier(OrtusAttributeRegistry.MAGIC_PROFICIENCY.get(), "4d82fd0a-24b6-45f5-8d7a-983f99fd6783", 4f, AttributeModifier.Operation.ADDITION);
    }

    public static void canApplyPotion(PotionEvent.PotionApplicableEvent event) {
        MobEffectInstance potionEffect = event.getPotionEffect();
        LivingEntity entityLiving = event.getEntityLiving();
        if (potionEffect.getEffect().equals(MobEffects.HUNGER) && entityLiving.hasEffect(MalumMobEffectRegistry.GLUTTONY.get())) {
            event.setResult(Event.Result.DENY);
        }
    }

    public static void finishEating(LivingEntityUseItemEvent.Finish event) {
        ItemStack stack = event.getResultStack();
        if (stack.is(GROSS_FOODS)) {
            LivingEntity entity = event.getEntityLiving();
            MobEffectInstance effect = entity.getEffect(MalumMobEffectRegistry.GLUTTONY.get());
            if (effect != null) {
                EntityHelper.extendEffect(effect, entity, 200, 1000);
                Level level = entity.level;
                level.playSound(null, entity.blockPosition(), SoundRegistry.HUNGRY_BELT_FEEDS.get(), SoundSource.PLAYERS, 1.7f, 1.2f + level.random.nextFloat() * 0.5f);
            }
        }
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn instanceof Player player) {
            player.causeFoodExhaustion(0.004f * (amplifier + 1));
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}