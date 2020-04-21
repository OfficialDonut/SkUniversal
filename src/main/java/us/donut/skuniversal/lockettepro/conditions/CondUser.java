package us.donut.skuniversal.lockettepro.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.crafter.mc.lockettepro.LocketteProAPI;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("LockettePro - Is User")
@Description("Checks if a player can use a locked block.")
@Examples({"if player is user of clicked block:"})
public class CondUser extends Condition {

    static {
        Skript.registerCondition(CondUser.class,
                "%player% is [a] [LockettePro] user of %block%",
                "%player% is(n't| not) [a] [LockettePro] user of %block%");
    }

    private Expression<Player> player;
    private Expression<Block> block;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        block = (Expression<Block>) e[1];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " is user of block " + block.toString(e, b);
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null || block.getSingle(e) == null) return isNegated();
        return LocketteProAPI.isUser(block.getSingle(e), player.getSingle(e)) != isNegated();
    }
}
