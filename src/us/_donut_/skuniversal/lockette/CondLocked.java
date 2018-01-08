package us._donut_.skuniversal.lockette;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.yi.acru.bukkit.Lockette.Lockette;
import javax.annotation.Nullable;

@Name("Lockette - Is Locked")
@Description("Checks if a block is locked.")
@Examples({"if the clicked block is locked:"})
public class CondLocked extends Condition {

    private Expression<Block> block;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        block = (Expression<Block>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "block " + block.getSingle(e) + " is locked";
    }

    @Override
    public boolean check(Event e) {
        if (block.getSingle(e) != null) {
            return Lockette.isProtected(block.getSingle(e));
        } else {
            Skript.error("Must provide a block, please refer to the syntax");
            return false;
        }
    }
}