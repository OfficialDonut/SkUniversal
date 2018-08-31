package us._donut_.skuniversal.pvplevels.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.pvplevels.PvPLevelsHook.*;

@Name("PvPLevels - KDR of Player")
@Description("Returns the kill-death ratio of a player.")
@Examples({"send \"%pvp KDR of player%\""})
public class ExprKDR extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprKDR.class, Number.class, ExpressionType.COMBINED,
                "[the] PvP[Levels] (kd[r]|kill death ratio) of %offlineplayer%",
                "%offlineplayer%'s PvP[Levels] (kd[r]|kill death ratio)");
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
        return "kdr of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        double kdr = ((double) pvpLevelsAPI.CurrentKillsOfflinePlayer(player.getSingle(e))) / (pvpLevelsAPI.CurrentDeathsOfflinePlayer(player.getSingle(e)) == 0 ? 1 : pvpLevelsAPI.CurrentDeathsOfflinePlayer(player.getSingle(e)));
        return new Number[]{kdr};
    }
}
