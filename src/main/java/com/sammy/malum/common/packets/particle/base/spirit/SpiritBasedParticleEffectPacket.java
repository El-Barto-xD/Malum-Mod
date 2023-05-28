package com.sammy.malum.common.packets.particle.base.spirit;

import com.sammy.malum.common.packets.particle.base.*;
import com.sammy.malum.core.helper.*;
import com.sammy.malum.core.systems.spirit.*;
import net.minecraft.network.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.network.*;

import java.util.*;
import java.util.function.*;

public abstract class SpiritBasedParticleEffectPacket extends PositionBasedParticleEffectPacket {
    protected final List<String> spirits;

    public SpiritBasedParticleEffectPacket(List<String> spirits, double posX, double posY, double posZ) {
        super(posX, posY, posZ);
        this.spirits = spirits;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(spirits.size());
        for (String string : spirits) {
            buf.writeUtf(string);
        }
        super.encode(buf);
    }

    @OnlyIn(Dist.CLIENT)
    public void execute(Supplier<NetworkEvent.Context> context) {
        for (String string : spirits) {
            execute(context, SpiritHelper.getSpiritType(string));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public abstract void execute(Supplier<NetworkEvent.Context> context, MalumSpiritType spiritType);

    public static <T extends SpiritBasedParticleEffectPacket> T decode(PacketProvider<T> provider, FriendlyByteBuf buf) {
        int strings = buf.readInt();
        List<String> spirits = new ArrayList<>();
        for (int i = 0; i < strings; i++) {
            spirits.add(buf.readUtf());
        }
        double posX = buf.readDouble();
        double posY = buf.readDouble();
        double posZ = buf.readDouble();
        return provider.getPacket(spirits, posX, posY, posZ);
    }

    public interface PacketProvider<T extends SpiritBasedParticleEffectPacket> {
        T getPacket(List<String> spirits, double posX, double posY, double posZ);
    }
}