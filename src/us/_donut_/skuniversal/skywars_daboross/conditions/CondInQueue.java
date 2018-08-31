package us._donut_.skuniversal.skywars_daboross.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.skywars_daboross.SkyWarsDaborossHook.*;

@Name("SkyWars (Daboross) - Is Player in Queue")
@Description("Checks if a player is in the game queue.")
@Examples({"if player is in the skywars game queue:"})
public class CondInQueue extends Condition {

    static {
        Skript.registerCondition(CondInQueue.class,
                "%player% is in [the] [SkyWars] [game] queue",
                "%player% is(n't| not) in [the] [SkyWars] [game] queue");
    }

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b)+ " is in queue";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return skyWars.getGameQueue().inQueue(player.getSingle(e).getUniqueId()) != isNegated();
    }
}
