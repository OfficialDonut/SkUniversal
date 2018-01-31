package us._donut_.skuniversal.playerpoints;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("PlayerPoints - Balance")
@Description("Returns the PlayerPoints balance of a player.")
@Examples({"send \"%the player points balance of player%\""})
public class ExprPointsBalance extends SimpleExpression<Number> {

    private PlayerPointsAPI playerPointsAPI = ((PlayerPoints)Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints")).getAPI();
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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the player points balance of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) != null) {
            return new Number[]{ playerPointsAPI.look(player.getSingle(e).getUniqueId()) };
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Number amount = (Number) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            playerPointsAPI.set(player.getSingle(e).getUniqueId(), amount.intValue());
        } else if (mode == Changer.ChangeMode.ADD) {
            playerPointsAPI.give(player.getSingle(e).getUniqueId(), amount.intValue());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            playerPointsAPI.take(player.getSingle(e).getUniqueId(), amount.intValue());
        } else if (mode == Changer.ChangeMode.RESET) {
            playerPointsAPI.reset(player.getSingle(e).getUniqueId());
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.RESET) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }
}