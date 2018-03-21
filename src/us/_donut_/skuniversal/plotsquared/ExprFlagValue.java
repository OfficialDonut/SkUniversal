package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.flag.Flags;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotId;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("PlotSquared - Plot Flag Value")
@Description("Returns the value of a flag in a plot.")
@Examples({"send \"%the value of flag {flag} in the plot with id (id of plot at player)%\""})
public class ExprFlagValue extends SimpleExpression<Object> {

    private Expression<String> id;
    private Expression<String> flag;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Object> getReturnType() {
        return Object.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[1];
        flag = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the value of flag " + flag.getSingle(e) + " in plot with id " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected Object[] get(Event e) {
        if (id.getSingle(e) != null && flag.getSingle(e) != null) {
            PlotAPI plot = new PlotAPI();
            PlotId plotId = PlotId.fromString(id.getSingle(e));
            if (plotId == null) {
                Skript.error("Invalid plot ID, please refer to the syntax");
                return null;
            } else {
                for (Plot aPlot : plot.getAllPlots()) {
                    if (aPlot.getId().equals(plotId)) {
                        return new Object[]{aPlot.getFlags().get(Flags.getFlag(flag.getSingle(e)))};
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