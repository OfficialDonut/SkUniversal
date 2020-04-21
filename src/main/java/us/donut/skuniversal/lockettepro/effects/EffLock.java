package us.donut.skuniversal.lockettepro.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
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

@Name("LockettePro - Lock Block")
@Description("Locks a block by placing a sign on it.")
@Examples({"place a LockettePro sign on the north face of the clicked block with player as the owner"})
public class EffLock extends Effect {

    static {
        Skript.registerEffect(EffLock.class, "(put|place) [a] [LockettePro] sign on [the] %string% [block]face of %block% with owner %player%");
    }

    private Expression<String> stringFace;
    private Expression<Block> block;
    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        stringFace = (Expression<String>) e[0];
        block = (Expression<Block>) e[1];
        player = (Expression<Player>) e[2];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "place LockettePro sign with player " + player.toString(e, b) + " as the owner";
    }

    @Override
    protected void execute(Event e) {
        if (stringFace.getSingle(e) == null || block.getSingle(e) == null || player.getSingle(e) == null) return;
        BlockFace face;
        switch (stringFace.getSingle(e).toLowerCase()) {
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
    }
}
