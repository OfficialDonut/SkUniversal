package us.donut.skuniversal.cannons.expressions;

import at.pavlov.cannons.cannon.Cannon;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.UUID;

import static us.donut.skuniversal.cannons.CannonsHook.cannons;

@Name("Cannons - Cannon Soot")
@Description("Returns the amount of soot in a cannon.")
@Examples({"send \"%the amount of soot in the cannon with id (id of cannon at player)%\""})
public class ExprCannonSoot extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprCannonSoot.class, Number.class, ExpressionType.COMBINED, "[the] [amount of] soot in [the] cannon [with ID] %string%");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the amount of soot in the cannon with ID " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        Cannon cannon;
        if (id.getSingle(e) == null || (cannon = cannons.getCannon(UUID.fromString(id.getSingle(e)))) == null) return null;
        return new Number[]{cannon.getSoot()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        double newSoot = ((Number) delta[0]).doubleValue();
        Cannon cannon;
        if (id.getSingle(e) == null || (cannon = cannons.getCannon(UUID.fromString(id.getSingle(e)))) == null) return;
        if (mode == Changer.ChangeMode.SET) {
            cannon.setSoot(newSoot);
        } else if (mode == Changer.ChangeMode.ADD) {
           cannon.setSoot(cannon.getSoot() + newSoot);
        } else if (mode == Changer.ChangeMode.REMOVE) {
           cannon.setSoot(cannon.getSoot() - newSoot);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) ? CollectionUtils.array(Number.class) : null;
    }

}
