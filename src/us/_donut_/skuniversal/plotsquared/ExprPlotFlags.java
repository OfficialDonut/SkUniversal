package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.flag.Flag;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("PlotSquared - Plot Flags")
@Description("Returns the set flags in a plot.")
@Examples({"send \"%the flags of the plot with id (id of plot at player)%\""})
public class ExprPlotFlags extends SimpleExpression<String> {

    private Expression<String> id;

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
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "flags of plot of with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        Plot plot = PlotSquaredRegister.getPlot(id.getSingle(e));
        return plot == null ? null : plot.getFlags().keySet().stream().map(Flag::getName).toArray(String[]::new);
    }
}
