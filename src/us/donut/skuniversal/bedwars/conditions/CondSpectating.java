package us.donut.skuniversal.bedwars.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import me.MineHome.Bedwars.Spectator.Spectator;
import javax.annotation.Nullable;

@Name("Bedwars - Is Spectating")
@Description("Checks if a player is spectating a Bedwars game.")
@Examples({"if loop-player is spectating Bedwars:"})
public class CondSpectating extends Condition {

    static {
        Skript.registerCondition(CondSpectating.class,
                "%player% is spectating [Bedwars]",
                "%player% is [a] [Bedwars] spectator",
                "%player% is(n't| not) spectating [Bedwars]",
                "%player% is(n't| not) [a] [Bedwars] spectator");
    }

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        setNegated(matchedPattern == 2 || matchedPattern == 3);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player " + player.toString(e, b) + " is spectating Bedwars";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return Spectator.is(player.getSingle(e)) != isNegated();
    }
}