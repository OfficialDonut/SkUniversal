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

@Name("Cannons - Is Projectile Loaded")
@Description("Checks if a projectile is loaded in a cannon.")
@Examples({"if a projectile is loaded in the cannon with id (id of cannon at player):"})
public class CondProjectileLoaded extends Condition {

    static {
        Skript.registerCondition(CondProjectileLoaded.class,
                "[a] projectile is loaded in [the] cannon [with ID] %string%",
                "[the] cannon [with ID] %string% is loaded",
                "[a] projectile is(n't| not) loaded in [the] cannon [with ID] %string%",
                "[the] cannon [with ID] %string% is(n't| not) loaded");
    }

    private Expression<String> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        setNegated(matchedPattern == 2 || matchedPattern == 3);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "a projectile is loaded in the cannon with id " + id.toString(e, b);
    }

    @Override
    public boolean check(Event e) {
        Cannon cannon;
        if (id.getSingle(e) == null || (cannon = cannons.getCannon(UUID.fromString(id.getSingle(e)))) == null) return isNegated();
       return cannon.isProjectileLoaded() != isNegated();
    }

}
