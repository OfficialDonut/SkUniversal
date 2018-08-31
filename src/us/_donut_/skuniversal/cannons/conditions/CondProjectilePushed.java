package us._donut_.skuniversal.cannons.conditions;

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

import static us._donut_.skuniversal.cannons.CannonsHook.*;

@Name("Cannons - Is Projectile Pushed")
@Description("Checks if the projectile in a cannon has been pushed.")
@Examples({"if projectile in the cannon with id (id of cannon at player) is pushed:"})
public class CondProjectilePushed extends Condition {

    static {
        Skript.registerCondition(CondProjectilePushed.class,
                "[the] projectile in [the] cannon [with ID] %string% (is|has been) pushed",
                "[the] projectile in [the] cannon [with ID] %string% (is(n't| not)|has(n't| not) been) pushed");
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
        return "the projectile has been pushed in the cannon with id " + id.toString(e, b);
    }

    @Override
    public boolean check(Event e) {
        Cannon cannon;
        if (id.getSingle(e) == null || (cannon = cannons.getCannon(UUID.fromString(id.getSingle(e)))) == null) return isNegated();
        return cannon.isProjectilePushed() != isNegated();
    }

}
