package us._donut_.skuniversal.slimefun.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
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

    static {
        Skript.registerExpression(ExprResearchName.class, String.class, ExpressionType.COMBINED, "[the] name of [the] [Slimefun] research [with ID] %integer%");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
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
        if (id.getSingle(e) == null) return null;
        return new String[]{Research.getByID(id.getSingle(e)).getName()};
    }
}
