package BananaFructa.thah;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ThahConfig {

    public static Configuration config;
    public static boolean enableDescription;

    public static void load(File configDirectory) {
        config = new Configuration(new File(configDirectory,"thah.cfg"));

        enableDescription = config.getBoolean("description","general",false,"If set to true descriptions will be shown for each climate when the player hovers over the button");

        for (Climates climate : Climates.values()) {
            climate.enabled =  config.getBoolean( climate.name + "_cold","climate",climate.enabled,"Shows the " + climate.name + " climate as a spawn option");
            climate.dif = config.getInt( climate.name + "_difficulty","climate",climate.dif,0,6,"The difficulty of the " + climate.name + " climate");
            climate.desc = config.getString(climate.name + "_desc","climate",climate.desc,"The description of the " + climate.name + " climate");
        }

        if (config.hasChanged()) config.save();
    }

}
