package us.donut.skuniversal.cannons.conditions;

import at.pavlov.cannons.cannon.Cannon;
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
import java.util.UUID;

import static us.donut.skuniversal.cannons.CannonsHook.cannons;

@Name("Cannons - Is Cannon Clean")
@Description("Checks if a cannon is clean.")
@Examples({"if the cannon with id (id of cannon at player) is clean:"})
public class CondClean extends Condition {

    static {
        Skript.registerCondition(CondClean.class,
                "[the] cannon [with ID] %string% is clean[ed]",
                "[the] cannon [with ID] %string% is(n't| not) clean[ed]");
    }

    private Expression<String> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "cannon with ID " + id.toString(e, b) + " is clean";
    }

    @Override
    public boolean check(Event e) {
        Cannon cannon;
        if (id.getSingle(e) == null || (cannon = cannons.getCannon(UUID.fromString(id.getSingle(e)))) == null) return isNegated();
        return cannon.isClean() != isNegated();
    }

}
