package us._donut_.skuniversal.lockettepro;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.crafter.mc.lockettepro.LocketteProAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("LockettePro - Block Owner")
@Description("Returns the owner of a block.")
@Examples({"send \"%the owner of the clicked block%\""})
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
        return "owner of block";
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        if (!LocketteProAPI.isLocked(block.getSingle(e))) {
            return null;
        }
        for (int x = block.getSingle(e).getX() - 1; x <= block.getSingle(e).getX() + 1; x++) {
            for (int y = block.getSingle(e).getY() - 1; y <= block.getSingle(e).getY() + 1; y++) {
                for (int z = block.getSingle(e).getZ() - 1; z <= block.getSingle(e).getZ() + 1; z++) {
                    Location loc = new Location(block.getSingle(e).getWorld(), x, y, z);
                    if (LocketteProAPI.isLockSign(loc.getBlock())) {
                        Sign sign = (Sign) loc.getBlock().getState();
                        return new OfflinePlayer[]{Bukkit.getOfflinePlayer(sign.getLine(1))};
                    }
                }
            }
        }
        return null;
    }
}