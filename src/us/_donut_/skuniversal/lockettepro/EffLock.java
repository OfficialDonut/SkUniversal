package us._donut_.skuniversal.lockettepro;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.crafter.mc.lockettepro.Config;
import me.crafter.mc.lockettepro.Utils;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class EffLock extends Effect {

    private Expression<String> stringFace;
    private Expression<Block> block;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        stringFace = (Expression<String>) e[0];
        block = (Expression<Block>) e[1];
        player = (Expression<Player>) e[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "place LockettePro sign with player " + player.getSingle(e) + " as the owner";
    }

    @Override
    protected void execute(Event e) {
        if (player.getSingle(e) != null) {
            if (block.getSingle(e) != null) {
                if (stringFace.getSingle(e) != null) {
                    BlockFace face;
                    switch (stringFace.getSingle(e)) {
                        case "north":
                            face = BlockFace.NORTH;
                            break;
                        case "south":
                            face = BlockFace.SOUTH;
                            break;
                        case "east":
                            face = BlockFace.EAST;
                            break;
                        case "west":
                            face = BlockFace.WEST;
                            break;
                        default:
                            face = BlockFace.NORTH;
                            break;
                    }
                    Utils.putSignOn(block.getSingle(e), face, Config.getDefaultPrivateString(), player.getSingle(e).getName());
                } else {
                    Skript.error("Must provide a string (block face), please refer to the syntax");
                }
            } else {
                Skript.error("Must provide a block, please refer to the syntax");
            }
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
        }
    }
}
