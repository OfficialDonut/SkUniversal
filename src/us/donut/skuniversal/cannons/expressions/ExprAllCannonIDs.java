package us.donut.skuniversal.cannons.expressions;

import at.pavlov.cannons.cannon.CannonManager;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("Cannons - All Cannon IDs")
@Description("Returns the IDs of all cannons.")
@Examples({"send \"%the IDs of all cannons%\""})
public class ExprAllCannonIDs extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprAllCannonIDs.class, String.class, ExpressionType.SIMPLE, "[(all [[of] the]|the)]] [IDs of all] cannons");
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
    public String toString(@Nullable Event e, boolean b) {
        return "the IDs of all cannons";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return CannonManager.getCannonList().values().stream().map(cannon -> cannon.getUID().toString()).toArray(String[]::new);
    }

}
