package us._donut_.skuniversal.parties;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.utils.api.PartiesAPI;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class EffRemoveParty extends Effect {

    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        name = (Expression<String>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "delete party";
    }
    @Override
    protected void execute(Event e) {
        PartiesAPI parties = new PartiesAPI();
        parties.deleteParty(name.getSingle(e));
    }
}
