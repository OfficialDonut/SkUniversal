package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprHomeLoc extends SimpleExpression<Location> {

    private Expression<Location> loc;

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
        loc = (Expression<Location>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "home location of plot";
    }

    @Override
    @Nullable
    protected Location[] get(Event e) {
        PlotAPI plot = new PlotAPI();
        return new Location[]{plot.getHomeLocation(plot.getPlot(loc.getSingle(e)))};
    }
}