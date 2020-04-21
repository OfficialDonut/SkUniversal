package us.donut.skuniversal.plotsquared.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.flag.PlotFlag;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.plotsquared.PlotSquaredHook.*;

@Name("PlotSquared - Plot Flags")
@Description("Returns the set flags in a plot.")
@Examples({"send \"%the flags of the plot with id (id of plot at player)%\""})
public class ExprPlotFlags extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPlotFlags.class, String.class, ExpressionType.COMBINED, "[the] [set] flags of [the] [PlotSquared] plot [with ID] %string%");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
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
        Plot plot;
        if (id.getSingle(e) == null || (plot = getPlot(id.getSingle(e))) == null) return null;
        return plot.getFlags().stream().map(PlotFlag::getName).toArray(String[]::new);
    }
}
