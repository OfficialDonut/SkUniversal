package us.donut.skuniversal.griefprevention.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.ryanhamshire.GriefPrevention.Claim;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.griefprevention.GriefPreventionHook.*;

@Name("GriefPrevention - Claim Owner")
@Description("Returns the owner of a claim.")
@Examples({"send \"%the owner of the claim with id (id of the basic claim at player)%\""})
public class ExprOwnerOfClaim extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprOwnerOfClaim.class, OfflinePlayer.class, ExpressionType.COMBINED, "[the] owner of [the] [G[rief]P[revention]] claim [with ID] %number%");
    }

    private Expression<Number> id;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        id = (Expression<Number>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "owner of claim with id " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        Claim claim = getClaim(id.getSingle(e).longValue());
        return claim == null ? null : new OfflinePlayer[]{Bukkit.getOfflinePlayer(claim.getOwnerName())};
    }
}