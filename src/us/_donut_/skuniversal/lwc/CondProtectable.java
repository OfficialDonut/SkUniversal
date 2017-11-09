package us._donut_.skuniversal.lwc;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.griefcraft.lwc.LWC;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

public class CondProtectable extends Condition {

    private Expression<Block> block;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        block = (Expression<Block>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "block " + block.getSingle(e) + " is protectable";
    }

    @Override
    public boolean check(Event e) {
        if (block.getSingle(e) != null) {
            return LWC.getInstance().isProtectable(block.getSingle(e));
        } else {
            Skript.error("Must provide a block, please refer to the syntax");
            return false;
        }
    }
}
