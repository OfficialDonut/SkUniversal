package us.donut.skuniversal.slimefun.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Slimefun - All Researches")
@Description("Returns the IDs of all Slimefun researches")
@Examples({"send \"%all slimefun researches%\""})
public class ExprAllResearches extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprAllResearches.class, String.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] [IDs of all [the]] [Slimefun] researches");
    }

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
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the IDs of all Slimefun researches";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return SlimefunPlugin.getRegistry().getResearches().stream().map(r -> r.getKey().getKey()).toArray(String[]::new);
    }
}
