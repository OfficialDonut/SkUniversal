package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprPlayerHomeLoc extends SimpleExpression<Location> {

    private Expression<Player> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "home location of plot of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        if (player.getSingle(e) != null) {
            PlotAPI plot = new PlotAPI();
            return new Location[]{plot.getHomeLocation(plot.getPlot(player.getSingle(e)))};
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }
}
