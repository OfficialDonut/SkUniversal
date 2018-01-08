package us._donut_.skuniversal.parties;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.alessiodp.parties.utils.api.PartiesAPI;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Parties - Remove Party")
@Description("Removes a party.")
@Examples({"remove the party named \"cool\""})
public class EffRemoveParty extends Effect {

    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        name = (Expression<String>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "delete party named " + name.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (name.getSingle(e) != null) {
            PartiesAPI parties = new PartiesAPI();
            parties.deleteParty(name.getSingle(e));
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
        }
    }
}
