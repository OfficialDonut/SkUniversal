package us._donut_.skuniversal.bitcoin.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.bitcoin.BitcoinHook.*;

@Name("Bitcoin - Value Fluctuate")
@Description("Makes the bitcoin value fluctuate.")
@Examples({"make the bitcoin value fluctuate"})
public class EffFluctuate extends Effect {

    static {
        Skript.registerEffect(EffFluctuate.class, "make [the] bitcoin value (fluctuate|change) [randomly]");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "make the bitcoin value fluctuate";
    }

    @Override
    protected void execute(Event e) {
        bitcoinAPI.makeValueFluctuate();
    }
}
