package us.donut.skuniversal.lwc.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.lwc.LWCHook.lwc;

@Name("LWC - Prtection Number")
@Description("Returns the number of protections a player has.")
@Examples({"send \"%the number of protections of player%\""})
public class ExprProtectionNumber extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprProtectionNumber.class, Number.class, ExpressionType.COMBINED,
                "[the] [(number|amount) of] [LWC] protections of %player%",
                "%player%'s [(number|amount) of] [LWC] protections");
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
        return "number of protections of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{lwc.getPhysicalDatabase().getProtectionCount(player.getSingle(e).getName())};
    }
}
