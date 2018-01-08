package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("PlotSquared - Is Player in Plot")
@Description("Checks if a player is in a plot.")
@Examples({"if player is in a plot:"})
public class CondInPlot extends Condition {

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "player is in plot";
    }

    @Override
    public boolean check(Event e) {
        PlotAPI plot = new PlotAPI();
        return plot.isInPlot(player.getSingle(e));
    }
}
