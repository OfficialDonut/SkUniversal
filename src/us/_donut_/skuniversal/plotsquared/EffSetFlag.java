package us._donut_.skuniversal.plotsquared;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.intellectualcrafters.plot.flag.Flags;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("PlotSquared - Set Flag")
@Description("Sets flag in a plot.")
@Examples({"set flag \"player-interact\" to true in the plot with id (id of plot at player)"})
public class EffSetFlag extends Effect {

    private Expression<String> id;
    private Expression<String> flag;
    private Expression<Boolean> value;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
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
        Plot plot = PlotSquaredRegister.getPlot(id.getSingle(e));
        if (plot == null)
            return;
        plot.setFlag(Flags.getFlag(flag.getSingle(e)), value.getSingle(e));
    }
}
