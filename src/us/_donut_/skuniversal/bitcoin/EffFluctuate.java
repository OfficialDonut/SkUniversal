package us._donut_.skuniversal.bitcoin;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import us._donut_.bitcoin.Bitcoin;
import javax.annotation.Nullable;

@Name("Bitcoin - Value Fluctuate")
@Description("Makes the bitcoin value fluctuate.")
@Examples({"make the bitcoin value fluctuate"})
public class EffFluctuate extends Effect {

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "make the bitcoin value fluctuate";
    }

    @Override
    protected void execute(Event e) {
        Bitcoin.getAPI().makeValueFluctuate();
    }
}
