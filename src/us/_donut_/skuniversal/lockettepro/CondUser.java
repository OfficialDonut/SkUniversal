package us._donut_.skuniversal.lockettepro;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.crafter.mc.lockettepro.LocketteProAPI;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class CondUser extends Condition {

    private Expression<Player> player;
    private Expression<Block> block;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        block = (Expression<Block>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.getSingle(e) + " is user of block " + block.getSingle(e);
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) != null) {
            if (block.getSingle(e) != null) {
                return LocketteProAPI.isUser(block.getSingle(e), player.getSingle(e));
            } else {
                Skript.error("Must provide a block, please refer to the syntax");
                return false;
            }
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return false;
        }
    }
}
