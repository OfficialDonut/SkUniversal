package us._donut_.skuniversal.lockette;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.yi.acru.bukkit.Lockette.Lockette;
import javax.annotation.Nullable;

public class ExprOwner extends SimpleExpression<Player> {

    private Expression<Block> block;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        block = (Expression<Block>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "owner of block " + block.getSingle(e);
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        if (block.getSingle(e) != null) {
            if (!Lockette.isProtected(block.getSingle(e))) {
                return null;
            }
            Player player = Bukkit.getPlayer(Lockette.getProtectedOwner(block.getSingle(e)));
            return new Player[]{player};
        } else {
            Skript.error("Must provide a block, please refer to the syntax");
            return null;
        }
    }
}
