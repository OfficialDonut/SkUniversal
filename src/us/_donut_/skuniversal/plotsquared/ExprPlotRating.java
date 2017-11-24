package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotId;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprPlotRating extends SimpleExpression<Number> {

    private Expression<String> id;

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
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "rating of plot with id " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (id.getSingle(e) != null) {
            PlotAPI plot = new PlotAPI();
            PlotId plotId = PlotId.fromString(id.getSingle(e));
            if (plotId == null) {
                Skript.error("Invalid plot ID, please refer to the syntax");
                return null;
            } else {
                for (Plot aPlot : plot.getAllPlots()) {
                    if (aPlot.getId().equals(plotId)) {
                        if (Double.isNaN(aPlot.getAverageRating())) {
                            return null;
                        }
                        return new Number[]{aPlot.getAverageRating()};
                    }
                }
                Skript.error("Invalid plot ID, please refer to the syntax");
                return null;
            }
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }
}
