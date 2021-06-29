package us.donut.skuniversal.cannons.expressions;

import at.pavlov.cannons.cannon.CannonManager;
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

@Name("Cannons - Cannon IDs in Sphere")
@Description("Returns the IDs of all cannons in a sphere.")
@Examples({"send \"%the IDs of the cannons within the sphere centered at player with radius 5%\""})
public class ExprCannonIDsInSphere extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprCannonIDsInSphere.class, String.class, ExpressionType.COMBINED, "[(all [[of] the]|the)]] [IDs of [the]] cannons [with]in [the] sphere centered at %location%[,] [with] radius %number%");
    }

    private Expression<Location> center;
    private Expression<Number> radius;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        center = (Expression<Location>) e[0];
        radius = (Expression<Number>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the IDs of the cannons within the sphere centered at " + center.toString(e, b) + " with radius " + radius.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (center.getSingle(e) == null || radius.getSingle(e) == null) return null;
        return CannonManager.getCannonsInSphere(center.getSingle(e), radius.getSingle(e).doubleValue())
                .stream()
                .map(cannon -> cannon.getUID().toString())
                .toArray(String[]::new);
    }

}