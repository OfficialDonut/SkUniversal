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

@Name("SkyWars (CookLoco) - Player Wins")
@Description("Returns the amount of skywars wins of a player.")
@Examples({"send \"%the skywars wins of player%\""})
public class ExprWins extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprWins.class, Number.class, ExpressionType.COMBINED,
                "[the] [(amount|number) of] [SkyWars] wins of %player%",
                "%player%'s [(amount|number) of] [SkyWars] wins");
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
        return "SkyWars wins of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{SkyWarsAPI.getWins(player.getSingle(e))};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (player.getSingle(e) == null) return;
        int winsChange = ((Number) delta[0]).intValue();
        if (mode == Changer.ChangeMode.SET) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).setWins(winsChange);
        } else if (mode == Changer.ChangeMode.ADD) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).addWins(winsChange);
        } else if (mode == Changer.ChangeMode.REMOVE) {
            SkyWarsAPI.getSkyPlayer(player.getSingle(e)).setWins(SkyWarsAPI.getWins(player.getSingle(e)) - winsChange);
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) ? CollectionUtils.array(Number.class) : null;
    }
}