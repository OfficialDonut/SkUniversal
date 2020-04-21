package us.donut.skuniversal.lwc.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.griefcraft.model.Protection;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.lwc.LWCHook.*;

@Name("LWC - Owner")
@Description("Returns the owner of a block.")
@Examples({"send \"%the owner of the clicked block%\""})
public class ExprLWCOwner extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprLWCOwner.class, OfflinePlayer.class, ExpressionType.COMBINED,
                "[the] [LWC] owner of %block%",
                "%block%'s [LWC] owner");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        block = (Expression<Block>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the owner of block " + block.toString(e, b);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        if (block.getSingle(e) == null) return null;
        Protection protection = lwc.findProtection(block.getSingle(e));
        if (protection == null) return null;
        String[] playerNameAndUUID = protection.getFormattedOwnerPlayerName().split(" ");
        return new OfflinePlayer[]{Bukkit.getOfflinePlayer(playerNameAndUUID[0])};
    }
}
