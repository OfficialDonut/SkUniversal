package us.donut.skuniversal.lockettepro.conditions;

import ch.njol.skript.Skript;
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

    static {
        Skript.registerCondition(CondLockable.class,
                "%block% is (lockable|(able to|can) be locked) [by LockettePro]",
                "%block% is(n't| not) (lockable|(able to|can) be locked) [by LockettePro]");
    }

    private Expression<Block> block;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        block = (Expression<Block>) e[0];
        setNegated(matchedPattern == 1);
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "block " + block.toString(e, b) + " is lockable";
    }

    @Override
    public boolean check(Event e) {
        if (block.getSingle(e) == null) return isNegated();
        return LocketteProAPI.isLockable(block.getSingle(e)) != isNegated();
    }
}
