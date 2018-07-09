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
import me.leoko.advancedban.utils.Punishment;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("AdvancedBan - Punishments")
@Description("Returns the active punishments of a player.")
@Examples({"send \"Your punishments: %active punishments of player%\""})
public class ExprPunishments extends SimpleExpression<String> {

    private Expression<OfflinePlayer> player;

    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int i, Kleenean kl, SkriptParser.ParseResult pr) {
        player = (Expression<OfflinePlayer>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the punishments of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return PunishmentManager.get().getPunishments(UUIDManager.get().getUUID(player.getSingle(e).getName()), null, true).stream().map(Punishment::getName).toArray(String[]::new);
    }
}
