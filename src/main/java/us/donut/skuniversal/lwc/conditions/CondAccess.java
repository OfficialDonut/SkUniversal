package us.donut.skuniversal.lwc.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.lwc.LWCHook.*;

@Name("LWC - Player Access")
@Description("Checks if a player has access to a block.")
@Examples({"if player has access to the clicked block:"})
public class CondAccess extends Condition {

    static {
        Skript.registerCondition(CondAccess.class,
                "%player% (has access to|can access) [LWC] [(locked|protected)] %block%",
                "%player% (does(n't| not) have access to|can('t|not) access) [LWC] [(locked|protected)] %block%");
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
        return "player " + player.toString(e, b) + " has access to block " + block.toString(e, b);
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null || block.getSingle(e) == null) return isNegated();
        return lwc.canAccessProtection(player.getSingle(e), block.getSingle(e)) != isNegated();
    }
}
