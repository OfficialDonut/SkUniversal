package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ExprAllPlotIDs extends SimpleExpression<String> {

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "ids of all plots";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        PlotAPI plot = new PlotAPI();
        List<String> ids = new ArrayList<>();
        for (Plot aPlot : plot.getAllPlots()) {
            ids.add(aPlot.getId().toString());
        }
        return ids.toArray(new String[ids.size()]);
    }

}