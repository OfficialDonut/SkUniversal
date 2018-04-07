package us._donut_.skuniversal.slimefun;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.mrCookieSlime.Slimefun.Objects.Research;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Name("Slimefun - All Researches")
@Description("Returns the ids of all Slimefun researches")
@Examples({"send \"%all slimefun researches%\""})
public class ExprAllResearches extends SimpleExpression<Number> {

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the IDs of all Slimefun researches";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        List<Number> ids = new ArrayList<>();
        for (Research research : Research.list) {
            ids.add(research.getID());
        }
        return ids.toArray(new Number[ids.size()]);
    }
}
