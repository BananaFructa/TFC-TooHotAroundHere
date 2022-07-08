package BananaFructa.thah;

import BananaFructa.thah.network.PacketHandler;
import BananaFructa.thah.network.SPacketPrompChooseClimate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.UUID;

public class CommonProxy {

    public ThahWorldStorage thahWorldStorage;

    public void init() {
        PacketHandler.registerPackets();
    }

    public void serverStarting(FMLServerStartingEvent event) {
        thahWorldStorage = ThahWorldStorage.get(event.getServer().getWorld(0));
    }

    @SubscribeEvent
    public void entityJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.player.world.isRemote) {
            if (firstJoined(event.player)) {
                promptPlayerToChoose(event.player);
            }
        }
    }

    public boolean firstJoined(EntityPlayer player) {
        return !thahWorldStorage.oldPlayers.contains(player.getUniqueID());
    }

    public void promptPlayerToChoose(EntityPlayer player) {
        PacketHandler.INSTANCE.sendTo(new SPacketPrompChooseClimate(),(EntityPlayerMP)player);
    }

}
