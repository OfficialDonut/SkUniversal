package us._donut_.skuniversal.griefprevention.expressions;

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

import static us._donut_.skuniversal.griefprevention.GriefPreventionHook.*;

@Name("GriefPrevention - Claim Block Limit")
@Description("Returns the claim block limit of a player.")
@Examples({"send \"%the claim block limit of player%\""})
public class ExprClaimBlockLimit extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprClaimBlockLimit.class, Number.class, ExpressionType.COMBINED,
                "[the] [G[rief]P[revention]] [claim] block limit of %offlineplayer%",
                "%offlineplayer%'s [G[rief]P[revention]] [claim] block limit");
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
        return "claim block limit of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{dataStore.getPlayerData(player.getSingle(e).getUniqueId()).getAccruedClaimBlocksLimit()};
    }

}