package us._donut_.skuniversal.skywars_cookloco;

import ak.CookLoco.SkyWars.api.SkyWarsAPI;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprDeaths extends SimpleExpression<Number> {

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "SkyWars deaths of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) != null) {
            return new Number[]{SkyWarsAPI.getDeaths(player.getSingle(e))};
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Number deathsChange = (Number) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).setDeaths(deathsChange.intValue());
        } else if (mode == Changer.ChangeMode.ADD) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).addDeaths(deathsChange.intValue());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).setDeaths(SkyWarsAPI.getDeaths(player.getSingle(e))-deathsChange.intValue());
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) {
            return CollectionUtils.array(Number.class);
        }
        return null;
    }
}
