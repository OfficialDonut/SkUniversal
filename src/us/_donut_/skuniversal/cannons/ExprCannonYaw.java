package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.Cannons;
import at.pavlov.cannons.cannon.Cannon;;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.UUID;

@Name("Cannons - Cannon Yaw")
@Description("Returns the yaw of a cannon.")
@Examples({"send \"%the yaw of the cannon with id (id of cannon at player)%\""})
public class ExprCannonYaw extends SimpleExpression<Number> {

    private Expression<String> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the yaw of the cannon with ID " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        Cannon cannon = Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e)));
        return cannon == null ? null : new Number[]{cannon.getAimingYaw()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Number newYaw = (Number) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).setAimingYaw(newYaw.doubleValue());
        } else if (mode == Changer.ChangeMode.ADD) {
            Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).setAimingYaw(Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).getTemperature() + newYaw.doubleValue());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).setAimingYaw(Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).getTemperature() - newYaw.doubleValue());
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }

}
