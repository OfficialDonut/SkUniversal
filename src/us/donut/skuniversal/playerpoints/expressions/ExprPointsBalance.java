package us.donut.skuniversal.playerpoints.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.playerpoints.PlayerPointsHook.playerPointsAPI;

@Name("PlayerPoints - Balance")
@Description("Returns the PlayerPoints balance of a player.")
@Examples({"send \"%the player points balance of player%\""})
public class ExprPointsBalance extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprPointsBalance.class, Number.class, ExpressionType.COMBINED,
                "[the] [Player Points] (bal[ance]|points) of %offlineplayer%",
                "%offlineplayer%'s [Player Points] (bal[ance]|points)");
    }

    private Expression<OfflinePlayer> player;

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
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the player points balance of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{playerPointsAPI.look(player.getSingle(e).getUniqueId())};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (player.getSingle(e) == null) return;
        int amount = ((Number) delta[0]).intValue();
        if (mode == Changer.ChangeMode.SET) {
            playerPointsAPI.set(player.getSingle(e).getUniqueId(), amount);
        } else if (mode == Changer.ChangeMode.ADD) {
            playerPointsAPI.give(player.getSingle(e).getUniqueId(), amount);
        } else if (mode == Changer.ChangeMode.REMOVE) {
            playerPointsAPI.take(player.getSingle(e).getUniqueId(), amount);
        } else if (mode == Changer.ChangeMode.RESET) {
            playerPointsAPI.reset(player.getSingle(e).getUniqueId());
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.RESET) ? CollectionUtils.array(Number.class) : null;
    }
}