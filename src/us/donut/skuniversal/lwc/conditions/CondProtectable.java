package us.donut.skuniversal.lwc.conditions;

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

import javax.annotation.Nullable;

import static us.donut.skuniversal.lwc.LWCHook.lwc;

@Name("LWC - Is Block Protectable")
@Description("Checks if a block is protectable.")
@Examples({"if the clicked block is protectable:"})
public class CondProtectable extends Condition {

    static {
        Skript.registerCondition(CondProtectable.class,
                "%block% is (lockable|protectable) [by LWC]",
                "%block% is(n't| not) (lockable|protectable) [by LWC]");
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
        return "block " + block.toString(e, b) + " is protectable";
    }

    @Override
    public boolean check(Event e) {
        if (block.getSingle(e) == null) return isNegated();
        return lwc.isProtectable(block.getSingle(e)) != isNegated();
    }
}
