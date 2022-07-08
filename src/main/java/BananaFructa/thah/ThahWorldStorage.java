package BananaFructa.thah;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;

import java.util.*;

public class ThahWorldStorage  extends WorldSavedData {

    public static final String dataName = Thah.MODID + "_WORLD_STORAGE";

    public List<UUID> oldPlayers = new ArrayList<>();

    public ThahWorldStorage() {
        super(dataName);
    }

    public ThahWorldStorage(String s) {
        super(s);
    }

    public static ThahWorldStorage get(World world) {
        MapStorage storage = world.getMapStorage();
        ThahWorldStorage thahWorldStorage = (ThahWorldStorage)storage.getOrLoadData(ThahWorldStorage.class,dataName);
        if (thahWorldStorage == null) {
            thahWorldStorage = new ThahWorldStorage();
            storage.setData(dataName,thahWorldStorage);
        }
        return thahWorldStorage;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        if (!nbt.hasKey("oldPlayers")) return;

        ByteArrayInputStream bytearr_pl = new ByteArrayInputStream(nbt.getByteArray("oldPlayers"));

        try {
            ObjectInputStream in_pl = new ObjectInputStream(bytearr_pl);
            oldPlayers = (List<UUID>)in_pl.readObject();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        if (oldPlayers.isEmpty()) return compound;

        ByteArrayOutputStream outarr_pl = new ByteArrayOutputStream();

        try {

            ObjectOutputStream out_pl = new ObjectOutputStream(outarr_pl);
            out_pl.writeObject(oldPlayers);
            compound.setByteArray("oldPlayers",outarr_pl.toByteArray());

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return compound;
    }

    public void addPlayer(EntityPlayer player) {
        oldPlayers.add(player.getUniqueID());
        markDirty();
    }
}
