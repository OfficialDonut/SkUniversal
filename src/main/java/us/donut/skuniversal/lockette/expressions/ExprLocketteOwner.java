package us.donut.skuniversal.lockette.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.yi.acru.bukkit.Lockette.Lockette;
import javax.annotation.Nullable;
import java.util.UUID;

@Name("Lockette - Block Owner")
@Description("Returns the owner of a block.")
@Examples({"send \"%owner of the clicked block%\""})
public class ExprLocketteOwner extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprLocketteOwner.class, OfflinePlayer.class, ExpressionType.COMBINED,
                "[the] [Lockette] owner of %block%",
                "%block%'s [Lockette] owner");
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
        return "owner of block " + block.toString(e, b);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        if (block.getSingle(e) == null) return null;
        UUID owner = Lockette.getProtectedOwnerUUID(block.getSingle(e));
        return owner == null ? null : new OfflinePlayer[]{Bukkit.getOfflinePlayer(owner)};
    }
}
