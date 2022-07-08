package BananaFructa.thah.network;


import BananaFructa.thah.Thah;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Thah.MODID);

    public static void registerPackets() {
        INSTANCE.registerMessage(SPacketPrompChooseClimateHandler.class,SPacketPrompChooseClimate.class,0, Side.CLIENT);
        INSTANCE.registerMessage(CPacketClimateTypeHandler.class,CPacketClimateType.class,1,Side.SERVER);
    }

}
