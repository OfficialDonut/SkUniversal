package us._donut_.skuniversal.skywars_cookloco;

import ak.CookLoco.SkyWars.api.SkyWarsAPI;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("SkyWars (CookLoco) - Player Coins")
@Description("Returns the amount of skywars coins of a player.")
@Examples({"send \"%the skywars coins of player%\""})
public class ExprCoins extends SimpleExpression<Number> {

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
    public String toString(@Nullable Event e, boolean b) {
        return "SkyWars coins of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{SkyWarsAPI.getSkyPlayer(player.getSingle(e)).getCoins()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Number coinsChange = (Number) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).setCoins(coinsChange.intValue());
        } else if (mode == Changer.ChangeMode.ADD) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).setCoins(SkyWarsAPI.getSkyPlayer(player.getSingle(e)).getCoins() + coinsChange.intValue());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).setCoins(SkyWarsAPI.getSkyPlayer(player.getSingle(e)).getCoins() - coinsChange.intValue());
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
