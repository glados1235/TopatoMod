package net.tombvali.topatomod.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class YeetPacket implements ModPacket {

    private final float x;
    private final float y;
    private final float z;

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeFloat(x);
        buf.writeFloat(y);
        buf.writeFloat(z);
    }

    public YeetPacket(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public YeetPacket(FriendlyByteBuf buffer) {
        this.x = buffer.readFloat();
        this.y = buffer.readFloat();
        this.z = buffer.readFloat();
    }

    @Override
    public void handle(@Nullable Level level, @Nullable Player player) {
        player.stopRiding();
        player.push(x, y, z);
    }
}
