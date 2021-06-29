package us.donut.skuniversal.parties.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.parties.PartiesHook.partiesAPI;

@Name("Parties - Party Exists")
@Description("Checks if a party exists.")
@Examples({"if party named \"cool\" exists:"})
public class CondPartyExists extends Condition {

    static {
        Skript.registerCondition(CondPartyExists.class,
                "[a] party [(named|with name)] %string% exists",
                "[a] party [(named|with name)] %string% does(n't| not) exists");
    }

    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        name = (Expression<String>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "party " + name.toString(e, b) + " exists";
    }

    @Override
    public boolean check(Event e) {
        if (name.getSingle(e) == null) return isNegated();
        return (partiesAPI.getParty(name.getSingle(e)) == null) == isNegated();
    }
}
