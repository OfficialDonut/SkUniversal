package us.donut.skuniversal.plotsquared.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.plotsquared.core.plot.Plot;
import com.plotsquared.core.plot.flag.GlobalFlagContainer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.plotsquared.PlotSquaredHook.getPlot;

@Name("PlotSquared - Set Flag")
@Description("Sets flag in a plot.")
@Examples({"set flag \"player-interact\" to true in the plot with id (id of plot at player)"})
public class EffSetFlag extends Effect {

    static {
        Skript.registerEffect(EffSetFlag.class, "set [the] flag %string% to %boolean% (in|for) [the] [PlotSquared] plot [with ID] %string%");
    }

    private Expression<String> id;
    private Expression<String> flag;
    private Expression<Boolean> value;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        id = (Expression<String>) e[2];
        flag = (Expression<String>) e[0];
        value = (Expression<Boolean>) e[1];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "set flag " + flag.toString(e, b) + " to " + value.toString(e, b) + " in plot with id " + id.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        Plot plot;
        if (id.getSingle(e) == null || value.getSingle(e) == null || flag.getSingle(e) == null || (plot = getPlot(id.getSingle(e))) == null) return;
        plot.setFlag(GlobalFlagContainer.getInstance().getFlagClassFromString(flag.getSingle(e)), value.getSingle(e).toString());
    }
}
