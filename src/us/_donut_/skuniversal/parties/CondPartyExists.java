package us._donut_.skuniversal.parties;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.Parties;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class CondPartyExists extends Condition {

    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "party exists";
    }

    @Override
    public boolean check(Event e) {
        return Parties.getInstance().getPartyHandler().existParty(name.getSingle(e));
    }
}
