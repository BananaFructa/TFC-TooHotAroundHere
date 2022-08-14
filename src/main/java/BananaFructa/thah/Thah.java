package BananaFructa.thah;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Thah.MODID, name = Thah.NAME, version = Thah.VERSION)
public class Thah {
    public static final String MODID = "thah";
    public static final String NAME = "TooHotAroundHere";
    public static final String VERSION = "1.1.1";

    public static Thah INSTANCE;

    @SidedProxy(modId = Thah.MODID,clientSide = "BananaFructa.thah.ClientProxy",serverSide = "BananaFructa.thah.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        INSTANCE = this;
        ThahConfig.load(event.getModConfigurationDirectory());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(proxy);
        proxy.init();
    }

    @Mod.EventHandler
    public void serverStaring(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }


}
