package us.donut.skuniversal.skywars_cookloco.expressions;

import ak.CookLoco.SkyWars.api.SkyWarsAPI;
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
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("SkyWars (CookLoco) - Player Coins")
@Description("Returns the amount of skywars coins of a player.")
@Examples({"send \"%the skywars coins of player%\""})
public class ExprCoins extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprCoins.class, Number.class, ExpressionType.COMBINED,
                "[the] [(amount|number) of] [SkyWars] coins of %player%",
                "%player%'s [(amount|number) of] [SkyWars] coins");
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
        return "SkyWars coins of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{SkyWarsAPI.getSkyPlayer(player.getSingle(e)).getCoins()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (player.getSingle(e) == null) return;
        int coinsChange = ((Number) delta[0]).intValue();
        if (mode == Changer.ChangeMode.SET) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).setCoins(coinsChange);
        } else if (mode == Changer.ChangeMode.ADD) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).setCoins(SkyWarsAPI.getSkyPlayer(player.getSingle(e)).getCoins() + coinsChange);
        } else if (mode == Changer.ChangeMode.REMOVE) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).setCoins(SkyWarsAPI.getSkyPlayer(player.getSingle(e)).getCoins() - coinsChange);
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) ? CollectionUtils.array(Number.class) : null;
    }
}
