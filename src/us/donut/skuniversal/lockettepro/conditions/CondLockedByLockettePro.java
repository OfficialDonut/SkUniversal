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

@Name("LockettePro - Is Block Locked")
@Description("Checks if a block is locked.")
@Examples({"if the clicked block is locked:"})
public class CondLockedByLockettePro extends Condition {

    static {
        Skript.registerCondition(CondLockedByLockettePro.class,
                "%block% is (locked|protected) [by LockettePro]",
                "%block% is(n't| not) (locked|protected) [by LockettePro]");
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
        return "block " + block.toString(e, b) + " is locked";
    }

    @Override
    public boolean check(Event e) {
        if (block.getSingle(e) == null) return isNegated();
        return LocketteProAPI.isLocked(block.getSingle(e)) != isNegated();
    }
}
