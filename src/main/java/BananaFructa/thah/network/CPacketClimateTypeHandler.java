package BananaFructa.thah.network;

import BananaFructa.thah.Climates;
import BananaFructa.thah.Thah;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CPacketClimateTypeHandler implements IMessageHandler<CPacketClimateType, IMessage> {

    @Override
    public IMessage onMessage(CPacketClimateType message, MessageContext ctx) {

        FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(new Runnable() {
            @Override
            public void run() {
                try {
                    if (Thah.proxy.thahWorldStorage.oldPlayers.contains(message.uuid)) return;
                    MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                    EntityPlayer player = server.getEntityWorld().getPlayerEntityByUUID(message.uuid);
                    if (player == null) return;
                    int z = Climates.values()[message.type].z;
                    if (z == 0) {
                        Thah.proxy.thahWorldStorage.addPlayer(player);
                        return; // no need to teleport the player as it already there
                    }

                    WorldServer worldIn = server.getWorld(0); // the player will always first telemport in the overworld
                    BlockPos finalPosition = null;
                    boolean found = false;
                    int tried = 0;
                    int interval = 5000;
                    while (!found && tried < 50) {

                        boolean odd = !(tried % 2 == 0);
                        int factor = tried;
                        if (odd) factor++;
                        factor /= 2;
                        int begin = (int) (interval * Math.pow(-1, tried) * factor);

                        System.out.println("Checking " + begin + " - " + (begin + interval));
                        for (int i = begin; i < begin + interval; i += interval / 10) {
                            BlockPos pos = worldIn.getTopSolidOrLiquidBlock(new BlockPos(i, 0, z));
                            Chunk chunk = worldIn.getChunkFromBlockCoords(pos.add(0, -1, 0));
                            if (chunk.getBlockState(pos.add(0, -1, 0)).isSideSolid(worldIn, pos.add(0, -1, 0), EnumFacing.UP) &&
                                    chunk.getBlockState(pos.add(0, 1, 0)).getBlock() == Blocks.AIR) {
                                found = true;
                                finalPosition = pos;
                            }
                        }

                        tried++;

                    }

                    Thah.proxy.thahWorldStorage.addPlayer(player);

                    player.setSpawnPoint(finalPosition, true);
                    player.attemptTeleport(finalPosition.getX(), finalPosition.getY(), finalPosition.getZ());
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
        });

        return null;
    }
}
