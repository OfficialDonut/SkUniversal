package us.donut.skuniversal.pvplevels.expressions;

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

import static us.donut.skuniversal.pvplevels.PvPLevelsHook.*;

@Name("PvPLevels - XP Progress of Player")
@Description("Returns the XP progress of a player.")
@Examples({"send \"%pvp xp progress of player%\""})
public class ExprProgress extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprProgress.class, String.class, ExpressionType.COMBINED,
                "[the] PvP[Levels] [(xp|exp[erience])] progress of %offlineplayer%",
                "%offlineplayer%'s PvP[Levels] [(xp|exp[erience])] progress");
    }

    private Expression<OfflinePlayer> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "progress of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{pvpLevelsAPI.CurrentXPProgressOfflinePlayer(player.getSingle(e))};
    }
}
