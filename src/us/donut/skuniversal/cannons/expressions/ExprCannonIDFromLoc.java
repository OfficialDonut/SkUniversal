package us.donut.skuniversal.cannons.expressions;

import at.pavlov.cannons.cannon.Cannon;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.cannons.CannonsHook.*;

@Name("Cannons - Cannon from Location")
@Description("Returns the ID of a cannon at a location.")
@Examples({"send \"%the id of the cannon at player%\""})
public class ExprCannonIDFromLoc extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprCannonIDFromLoc.class, String.class, ExpressionType.COMBINED, "[the] [ID of [the]] cannon at %location%");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
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
        Cannon cannon;
        if (location.getSingle(e) == null || (cannon = cannons.getCannonsAPI().getCannon(location.getSingle(e), null)) == null) return null;
        return new String[]{cannon.getUID().toString()};
    }

}