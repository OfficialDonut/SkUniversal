package us.donut.skuniversal.cannons.expressions;

import at.pavlov.cannons.cannon.Cannon;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.UUID;

import static us.donut.skuniversal.cannons.CannonsHook.*;

@Name("Cannons - Cannon Owner")
@Description("Returns the owner of a cannon.")
@Examples({"send \"%the owner of the cannon with id (id of cannon at player)%\""})
public class ExprCannonOwner extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprCannonOwner.class, OfflinePlayer.class, ExpressionType.COMBINED, "[the] owner of [the] cannon [with ID] %string%");
    }

    private Expression<String> id;

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
        id = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the owner of cannon with ID " + id.toString(e, b);
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        Cannon cannon;
        if (id.getSingle(e) == null || (cannon = cannons.getCannon(UUID.fromString(id.getSingle(e)))) == null) return null;
        return new OfflinePlayer[]{Bukkit.getOfflinePlayer(cannon.getOwner())};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode){
        OfflinePlayer newOwner = (OfflinePlayer) delta[0];
        Cannon cannon;
        if (id.getSingle(e) == null || (cannon = cannons.getCannon(UUID.fromString(id.getSingle(e)))) == null) return;
        if (mode == Changer.ChangeMode.SET) {
            cannon.setOwner(newOwner.getUniqueId());
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(OfflinePlayer.class) : null;
    }

}