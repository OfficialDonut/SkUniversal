package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.Cannons;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.UUID;

@Name("Cannons - Cannon Design")
@Description("Returns the design of a cannon.")
@Examples({"send \"%the design of the cannon with id (id of cannon at player)%\""})
public class CannonDesign extends SimpleExpression<String> {

    private Expression<String> id;

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
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the design of cannon with ID " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (id.getSingle(e) != null) {
            if (Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))) != null) {
                return new String[]{ Cannons.getPlugin().getCannon(UUID.fromString(id.getSingle(e))).getCannonDesign().getDesignName() };
            } else {
                return null;
            }
        } else {
            Skript.error("Must provide a string, please refer to the syntax");
            return null;
        }
    }

}