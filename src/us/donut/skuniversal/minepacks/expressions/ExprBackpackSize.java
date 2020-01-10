package us.donut.skuniversal.minepacks.expressions;

import at.pcgamingfreaks.Minepacks.Bukkit.API.Backpack;
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

import static us.donut.skuniversal.minepacks.MinePacksHook.*;

@Name("MinePacks - Backpack Size")
@Description("Returns the size of a backpack.")
@Examples({"send \"%the size of the backpack of player%\""})
public class ExprBackpackSize extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprBackpackSize.class, Number.class, ExpressionType.COMBINED,
                "[the] size of [the] (back|mine)pack of %player%",
                "[the] size of %player%'s (back|mine)pack");
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
        return "size of backpack of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        Backpack backpack = minePacks.getBackpackCachedOnly(player.getSingle(e));
        return backpack != null ? new Number[]{backpack.getSize()} : null;
    }
}