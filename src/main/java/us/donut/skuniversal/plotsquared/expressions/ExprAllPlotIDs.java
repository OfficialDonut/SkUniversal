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
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.plotsquared.PlotSquaredHook.*;

@Name("PlotSquared - All Plot IDs")
@Description("Returns all plot IDs.")
@Examples({"send \"%IDs of all plot%\""})
public class ExprAllPlotIDs extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprAllPlotIDs.class, String.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] [IDs of all [the]] [PlotSquared] plots");
    }

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
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "IDs of all plots";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return plotAPI.getAllPlots().stream().map(plot -> plot.getId().toString()).toArray(String[]::new);
    }

}