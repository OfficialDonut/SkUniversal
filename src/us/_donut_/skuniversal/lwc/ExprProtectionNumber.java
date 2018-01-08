package us._donut_.skuniversal.lwc;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.griefcraft.lwc.LWC;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("LWC - Prtection Number")
@Description("Returns the number of protections a player has.")
@Examples({"send \"%the number of protections of player%\""})
public class ExprProtectionNumber extends SimpleExpression<Number> {

    private Expression<Player> player;

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "number of protections of player " + player.getSingle(e);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        if (player.getSingle(e) != null) {
            return new Number[]{LWC.getInstance().getPhysicalDatabase().getProtectionCount(player.getSingle(e).getName())};
        } else {
            Skript.error("Must provide a player, please refer to the syntax");
            return null;
        }
    }
}
