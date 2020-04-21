package us.donut.skuniversal.plotsquared.conditions;

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

import static us.donut.skuniversal.plotsquared.PlotSquaredHook.*;

@Name("PlotSquared - Is Player in Plot")
@Description("Checks if a player is in a plot.")
@Examples({"if player is in a plot:"})
public class CondInPlot extends Condition {

    static {
        Skript.registerCondition(CondInPlot.class,
                "%player% is [currently] in [a] [PlotSquared] plot",
                "%player% is(n't| not) [currently] in [a] [PlotSquared] plot");
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
        return "player " + player.toString(e, b) + " is in a plot";
    }

    @Override
    public boolean check(Event e) {
        if (player.getSingle(e) == null) return isNegated();
        return (plotAPI.wrapPlayer(player.getSingle(e).getUniqueId()).getCurrentPlot() != null) == isNegated();
    }
}
