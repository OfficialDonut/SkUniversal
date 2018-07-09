package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.Cannons;
import at.pavlov.cannons.cannon.Cannon;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;


@Name("Cannons - Cannon from Location")
@Description("Returns the ID of a cannon at a location.")
@Examples({"send \"%the id of the cannon at player%\""})
public class ExprCannonIDFromLoc extends SimpleExpression<String> {

    private Expression<Location> location;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        location = (Expression<Location>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the ID of cannon at " + location.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        Cannon cannon = Cannons.getPlugin().getCannonsAPI().getCannon(location.getSingle(e), null);
        return cannon == null ? null : new String[]{Cannons.getPlugin().getCannonsAPI().getCannon(location.getSingle(e), null).getUID().toString()};
    }

}