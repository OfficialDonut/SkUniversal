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

@Name("Slimefun - Research Name")
@Description("Returns the name of a Slimefun research")
@Examples({"send \"%name of the slimefun research with id 2048%\""})
public class ExprResearchName extends SimpleExpression<String> {

    private Expression<Integer> id;

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
        id = (Expression<Integer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the name of the Slimefun research with id " + id.getSingle(e);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{Research.getByID(id.getSingle(e)).getName()};
    }
}
