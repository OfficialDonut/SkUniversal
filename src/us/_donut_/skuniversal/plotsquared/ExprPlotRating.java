package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprPlotRating extends SimpleExpression<Number> {

    private Expression<Location> loc;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        loc = (Expression<Location>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "rating of plot";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        PlotAPI plot = new PlotAPI();
        return new Number[]{plot.getPlot(loc.getSingle(e)).getAverageRating()};
    }
}
