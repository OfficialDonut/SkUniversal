package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.cannon.Cannon;
import at.pavlov.cannons.cannon.CannonManager;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Name("Cannons - All Cannon IDs")
@Description("Returns the IDs of all cannons.")
@Examples({"send \"%the IDs of all cannons%\""})
public class AllCannonIDs extends SimpleExpression<String> {

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
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the IDs of all cannons";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        List<String> ids = new ArrayList<>();
        for (Cannon cannon : CannonManager.getCannonList().values()) {
            ids.add(cannon.getUID().toString());
        }
        return ids.toArray(new String[ids.size()]);
    }

}
