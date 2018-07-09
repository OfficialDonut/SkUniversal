package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("PlotSquared - Plot Biome")
@Description("Returns the biome of a plot.")
@Examples({"send \"%the biome of the plot with id (id of plot at player)%\""})
public class ExprPlotBiome extends SimpleExpression<String> {

    private Expression<String> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "biome of plot with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        Plot plot = PlotSquaredRegister.getPlot(id.getSingle(e));
        return plot == null ? null : new String[]{plot.getBiome()};
    }
}
