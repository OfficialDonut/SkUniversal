package us.donut.skuniversal.lwc.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.griefcraft.model.Protection;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.lwc.LWCHook.*;

@Name("LWC - Remove Protection")
@Description("Removes protection from a block.")
@Examples({"remove protection from clicked block"})
public class EffRemoveProtection extends Effect {

    static {
        Skript.registerEffect(EffRemoveProtection.class, "(remove|delete) [the] [LWC] (protection|lock) from %block%");
    }

    private Expression<Block> block;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult p) {
        block = (Expression<Block>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "remove protection from block " + block.toString(e, b);
    }

    @Override
    protected void execute(Event e) {
        if (block.getSingle(e) == null) return;
        Protection protection = lwc.findProtection(block.getSingle(e));
        if (protection == null) return;
        protection.remove();
    }
}
