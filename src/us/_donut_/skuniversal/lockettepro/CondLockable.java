package us._donut_.skuniversal.lockettepro;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.crafter.mc.lockettepro.LocketteProAPI;
import org.bukkit.block.Block;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("LockettePro - Is Block Lockable")
@Description("Checks if a block is lockable.")
@Examples({"if the clicked block is lockable:"})
public class CondLockable extends Condition {

    private Expression<Block> block;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        block = (Expression<Block>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "block " + block.toString(e, b) + " is lockable";
    }

    @Override
    public boolean check(Event e) {
        return LocketteProAPI.isLockable(block.getSingle(e));
    }
}
