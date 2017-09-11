package us._donut_.skuniversal.lwc;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.griefcraft.lwc.LWC;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class ExprOwner extends SimpleExpression<OfflinePlayer> {

    private Expression<Block> block;

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
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        block = (Expression<Block>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the owner of block";
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        String[] playerNameAndUUID = LWC.getInstance().findProtection(block.getSingle(e)).getFormattedOwnerPlayerName().split(" ");
        return new OfflinePlayer[]{Bukkit.getOfflinePlayer(playerNameAndUUID[0])};
    }
}
