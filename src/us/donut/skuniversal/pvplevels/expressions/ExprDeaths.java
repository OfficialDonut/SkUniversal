package us.donut.skuniversal.pvplevels.expressions;

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

import static us.donut.skuniversal.pvplevels.PvPLevelsHook.*;

@Name("PvPLevels - Deaths of Player")
@Description("Returns the number of deaths of a player.")
@Examples({"send \"%pvp deaths of player%\""})
public class ExprDeaths extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprDeaths.class, Number.class, ExpressionType.COMBINED,
                "[the] [(number|amount) of] PvP[Levels] deaths of %offlineplayer%",
                "%offlineplayer%'s [(number|amount) of] PvP[Levels] deaths");
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
        return "number of deaths of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{pvpLevelsAPI.CurrentDeathsOfflinePlayer(player.getSingle(e))};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        if (player.getSingle(e) == null) return;
        int deathsChange = ((Number) delta[0]).intValue();
        if (mode == Changer.ChangeMode.SET) {
            pvpLevelsAPI.SetDeaths(player.getSingle(e).getPlayer(), deathsChange);
        } else if (mode == Changer.ChangeMode.ADD) {
            pvpLevelsAPI.AddDeaths(player.getSingle(e).getPlayer(), deathsChange);
        } else if (mode == Changer.ChangeMode.REMOVE) {
            pvpLevelsAPI.RemoveDeaths(player.getSingle(e).getPlayer(), deathsChange);
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) ? CollectionUtils.array(Number.class) : null;
    }
}
