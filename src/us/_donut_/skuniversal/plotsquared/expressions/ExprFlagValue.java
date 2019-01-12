package us._donut_.skuniversal.plotsquared.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.github.intellectualsites.plotsquared.plot.flag.Flags;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.plotsquared.PlotSquaredHook.*;

@Name("PlotSquared - Plot Flag Value")
@Description("Returns the value of a flag in a plot.")
@Examples({"send \"%the value of flag {flag} in the plot with id (id of plot at player)%\""})
public class ExprFlagValue extends SimpleExpression<Object> {

    static {
        Skript.registerExpression(ExprFlagValue.class, Object.class, ExpressionType.COMBINED, "[[the] value of] [the] flag %string% (in|for) [the] [PlotSquared] plot [with ID] %string%");
    }

    private Expression<String> id;
    private Expression<String> flag;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<?> getReturnType() {
        return Object.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[1];
        flag = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the value of flag " + flag.toString(e, b) + " in plot with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected Object[] get(Event e) {
        Plot plot;
        if (id.getSingle(e) == null || flag.getSingle(e) == null || (plot = getPlot(id.getSingle(e))) == null) return null;
        return new Object[]{plot.getFlags().get(Flags.getFlag(flag.getSingle(e)))};
    }
}