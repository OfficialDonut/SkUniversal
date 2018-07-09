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

@Name("AdvancedBan - Mute Time")
@Description("Returns the mute time of a player.")
@Examples({"send \"You can continue talking after %remaining mute time of player%.\""})
public class ExprMuteTime extends SimpleExpression<String> {

    private Expression<OfflinePlayer> player;

    @Override
    public boolean isSingle() {
        return true;
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
        return "the remaining mute time of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        Punishment mute = PunishmentManager.get().getMute(UUIDManager.get().getUUID(player.getSingle(e).getName()));
        return mute == null ? null : new String[]{mute.getDuration(false)};
    }
}
