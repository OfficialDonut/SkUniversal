package us._donut_.skuniversal.advancedban;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.leoko.advancedban.manager.PunishmentManager;
import me.leoko.advancedban.manager.UUIDManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("AdvancedBan - Warn Number")
@Description("Returns number of the warns of a player.")
@Examples({"send \"You have %warns of player% warns!\""})
public class ExprWarns extends SimpleExpression<Number> {

    private Expression<OfflinePlayer> player;

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
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the warns of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        return new Number[]{PunishmentManager.get().getCurrentWarns(UUIDManager.get().getUUID(player.getSingle(e).getName()))};
    }
}
