package BananaFructa.thah.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.Charset;
import java.util.UUID;

public class CPacketClimateType implements IMessage {
    // TODO: fix security vulnerability
    public int type;
    public UUID uuid;

    public CPacketClimateType() {

    }

    public CPacketClimateType(int type, UUID uuid) {
        this.type = type;
        this.uuid = uuid;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        type = buf.readInt();
        uuid = UUID.fromString(buf.readCharSequence(36,Charset.defaultCharset()).toString());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(type);
        buf.writeCharSequence(uuid.toString(), Charset.defaultCharset());
    }
}
