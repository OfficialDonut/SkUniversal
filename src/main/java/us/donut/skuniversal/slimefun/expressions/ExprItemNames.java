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
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("Slimefun - Item Names")
@Description("Returns the names of all Slimefun items")
@Examples({"send \"%the names of all Slimefun items%\""})
public class ExprItemNames extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprItemNames.class, String.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)] [names of [all] [the]] Slimefun items");
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
        return "all of the slimefun items";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return SlimefunItem.list().stream().map(SlimefunItem::getID).toArray(String[]::new);
    }
}