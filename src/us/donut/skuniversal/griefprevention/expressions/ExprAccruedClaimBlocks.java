package us.donut.skuniversal.griefprevention.expressions;

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

import static us.donut.skuniversal.griefprevention.GriefPreventionHook.dataStore;

@Name("GriefPrevention - Accrued Claim Blocks")
@Description("Returns the accrued claim blocks of a player.")
@Examples({"send \"%the accrued claim blocks of player%\""})
public class ExprAccruedClaimBlocks extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprAccruedClaimBlocks.class, Number.class, ExpressionType.COMBINED,
                "[the] [G[rief]P[revention]] accrued [claim] blocks of %offlineplayer%",
                "%offlineplayer%'s [G[rief]P[revention]] accrued [claim] blocks");
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
        return "accrued claim blocks of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        return new Number[]{dataStore.getPlayerData(player.getSingle(e).getUniqueId()).getAccruedClaimBlocks()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        int blockChange = ((Number) delta[0]).intValue();
        if (player.getSingle(e) == null) return;
        if (mode == Changer.ChangeMode.SET) {
            dataStore.getPlayerData(player.getSingle(e).getUniqueId()).setAccruedClaimBlocks(blockChange);
        } else if (mode == Changer.ChangeMode.ADD) {
            dataStore.getPlayerData(player.getSingle(e).getUniqueId()).setAccruedClaimBlocks(dataStore.getPlayerData(player.getSingle(e).getUniqueId()).getAccruedClaimBlocks() + blockChange);
        } else if (mode == Changer.ChangeMode.REMOVE) {
            dataStore.getPlayerData(player.getSingle(e).getUniqueId()).setAccruedClaimBlocks(dataStore.getPlayerData(player.getSingle(e).getUniqueId()).getAccruedClaimBlocks() - blockChange);
        }
    }
    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.ADD) ? CollectionUtils.array(Number.class) : null;
    }

}