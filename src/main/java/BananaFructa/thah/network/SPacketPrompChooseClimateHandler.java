package BananaFructa.thah.network;

import BananaFructa.thah.gui.ChooseClimateGui;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class SPacketPrompChooseClimateHandler implements IMessageHandler<SPacketPrompChooseClimate, IMessage>{

    @Override
    public IMessage onMessage(SPacketPrompChooseClimate message, MessageContext ctx) {

        Minecraft.getMinecraft().addScheduledTask(new Runnable() {
            @Override
            public void run() {
                Minecraft.getMinecraft().displayGuiScreen(new ChooseClimateGui());
            }
        });

        return null;
    }
}
