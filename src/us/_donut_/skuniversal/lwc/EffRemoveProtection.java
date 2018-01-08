package us._donut_.skuniversal.lwc;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.griefcraft.lwc.LWC;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("LWC - Remove Protection")
@Description("Removes protection from a block.")
@Examples({"remove protection from clicked block"})
public class EffRemoveProtection extends Effect {

    private Expression<Block> block;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult p) {
        block = (Expression<Block>) e[0];
        return true;
    }
    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "remove protection from block " + block.getSingle(e);
    }

    @Override
    protected void execute(Event e) {
        if (block.getSingle(e) != null) {
            LWC.getInstance().findProtection(block.getSingle(e)).remove();
        } else {
            Skript.error("Must provide a block, please refer to the syntax");
        }
    }
}
