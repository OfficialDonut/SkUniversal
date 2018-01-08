package us._donut_.skuniversal.griefprevention;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("GriefPrevention - Accrued Claim Blocks")
@Description("Returns the accrued claim blocks of a player.")
@Examples({"send \"%the accrued claim blocks of player%\""})
public class ExprAccruedClaimBlocks extends SimpleExpression<Number> {

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "accrued claim blocks of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) != null) {
            return new Number[]{GriefPrevention.instance.dataStore.getPlayerData(player.getSingle(e).getUniqueId()).getAccruedClaimBlocks()};
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        Number blockChange = (Number) delta[0];
        if (mode == Changer.ChangeMode.SET) {
            GriefPrevention.instance.dataStore.getPlayerData(player.getSingle(e).getUniqueId()).setAccruedClaimBlocks(blockChange.intValue());
        } else if (mode == Changer.ChangeMode.ADD) {
            GriefPrevention.instance.dataStore.getPlayerData(player.getSingle(e).getUniqueId()).setAccruedClaimBlocks(GriefPrevention.instance.dataStore.getPlayerData(player.getSingle(e).getUniqueId()).getAccruedClaimBlocks() + blockChange.intValue());
        } else if (mode == Changer.ChangeMode.REMOVE) {
            GriefPrevention.instance.dataStore.getPlayerData(player.getSingle(e).getUniqueId()).setAccruedClaimBlocks(GriefPrevention.instance.dataStore.getPlayerData(player.getSingle(e).getUniqueId()).getAccruedClaimBlocks() - blockChange.intValue());
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