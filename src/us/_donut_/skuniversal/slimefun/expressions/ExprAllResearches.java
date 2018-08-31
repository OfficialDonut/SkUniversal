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

@Name("Slimefun - All Researches")
@Description("Returns the ids of all Slimefun researches")
@Examples({"send \"%all slimefun researches%\""})
public class ExprAllResearches extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprAllResearches.class, Number.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] [IDs of all [the]] [Slimefun] researches");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the IDs of all Slimefun researches";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return Research.list().stream().map(Research::getID).toArray(Number[]::new);
    }
}
