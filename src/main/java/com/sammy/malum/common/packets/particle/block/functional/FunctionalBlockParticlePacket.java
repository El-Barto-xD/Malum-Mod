package com.sammy.malum.common.packets.particle.block.functional;

import team.lodestar.lodestone.systems.network.LodestoneClientPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class FunctionalBlockParticlePacket extends LodestoneClientPacket {
    protected final List<String> spirits;
    protected final double posX;
    protected final double posY;
    protected final double posZ;

    public FunctionalBlockParticlePacket(List<String> spirits, Vec3 vec3) {
        this(spirits, vec3.x, vec3.y, vec3.z);
    }

    public FunctionalBlockParticlePacket(List<String> spirits, double posX, double posY, double posZ) {
        this.spirits = spirits;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    public static <T extends FunctionalBlockParticlePacket> T decode(PacketProvider<T> provider, FriendlyByteBuf buf) {
        int strings = buf.readInt();
        List<String> spirits = new ArrayList<>();
        for (int i = 0; i < strings; i++) {
            spirits.add(buf.readUtf());
        }
        double posX = buf.readDouble();
        double posY = buf.readDouble();
        double posZ = buf.readDouble();
        return provider.getPacket(spirits, new Vec3(posX, posY, posZ));
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(spirits.size());
        for (String string : spirits) {
            buf.writeUtf(string);
        }
        buf.writeDouble(posX);
        buf.writeDouble(posY);
        buf.writeDouble(posZ);
    }

    public interface PacketProvider<T extends FunctionalBlockParticlePacket> {
        T getPacket(List<String> spirits, Vec3 vec3);
    }
}
