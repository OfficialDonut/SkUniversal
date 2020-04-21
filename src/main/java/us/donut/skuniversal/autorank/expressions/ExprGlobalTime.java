package us.donut.skuniversal.autorank.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.autorank.AutorankHook.*;

@Name("Autorank - Global Play Time")
@Description("Returns the global play time of a player in minutes.")
@Examples({"send \"Your global play time: %global play time of player% minutes\""})
public class ExprGlobalTime extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprGlobalTime.class, Number.class, ExpressionType.COMBINED,
                "[the] global [Autorank] [play[ ]]time of %player%",
                "%player%'s global [Autorank] [play[ ]]time");
    }

    private Expression<Player> player;

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
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "global time of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{autorankAPI.getGlobalPlayTime(player.getSingle(e).getUniqueId())};
    }
}
