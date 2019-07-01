package us.donut.skuniversal.parties.effects;

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

import static us.donut.skuniversal.parties.PartiesHook.*;

@Name("Parties - Remove Party")
@Description("Removes a party.")
@Examples({"remove the party named \"cool\""})
public class EffRemoveParty extends Effect {

    static {
        Skript.registerEffect(EffRemoveParty.class, "(delete|remove) [the] party [(named|with name)] %string%");
    }

    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        name = (Expression<String>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "delete party named " + name.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (name.getSingle(e) == null) return;
        partiesAPI.deleteParty(name.getSingle(e));
    }
}
