package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.cannon.Cannon;
import at.pavlov.cannons.cannon.CannonManager;
import ch.njol.skript.Skript;
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
import java.util.ArrayList;
import java.util.List;

@Name("Cannons - Cannon IDs in Sphere")
@Description("Returns the IDs of all cannons in a sphere.")
@Examples({"send \"%the IDs of the cannons within the sphere centered at player with radius 5%\""})
public class CannonIDsInSphere extends SimpleExpression<String> {

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        center = (Expression<Location>) e[0];
        radius = (Expression<Number>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the IDs of the cannons within the sphere centered at " + center.getSingle(e) + " with radius " + radius.getSingle(e);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (center.getSingle(e) != null) {
            if (radius.getSingle(e) != null) {
                List<String> ids = new ArrayList<>();
                for (Cannon cannon : CannonManager.getCannonsInSphere(center.getSingle(e), radius.getSingle(e).doubleValue())) {
                    ids.add(cannon.getUID().toString());
                }
                return ids.toArray(new String[ids.size()]);
            } else {
                Skript.error("Must provide a number, please refer to the syntax");
                return null;
            }
        } else {
            Skript.error("Must provide a location, please refer to the syntax");
            return null;
        }
    }

}