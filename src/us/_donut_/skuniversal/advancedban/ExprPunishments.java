package us._donut_.skuniversal.advancedban;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.leoko.advancedban.manager.PunishmentManager;
import me.leoko.advancedban.manager.UUIDManager;
import me.leoko.advancedban.utils.Punishment;
import me.leoko.advancedban.utils.PunishmentType;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

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
    public String toString(@Nullable Event e, boolean arg1) {
        return "the punishments of player";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        List<String> punishments = new ArrayList<>();
        for (Punishment p : PunishmentManager.get().getPunishments(UUIDManager.get().getUUID(player.getSingle(e).getName()), PunishmentType.BAN, true)) {
            punishments.add(p.getType().getName());
        }
        for (Punishment p : PunishmentManager.get().getPunishments(UUIDManager.get().getUUID(player.getSingle(e).getName()), PunishmentType.IP_BAN, true)) {
            punishments.add(p.getType().getName());
        }
        for (Punishment p : PunishmentManager.get().getPunishments(UUIDManager.get().getUUID(player.getSingle(e).getName()), PunishmentType.TEMP_BAN, true)) {
            punishments.add(p.getType().getName());
        }
        for (Punishment p : PunishmentManager.get().getPunishments(UUIDManager.get().getUUID(player.getSingle(e).getName()), PunishmentType.TEMP_IP_BAN, true)) {
            punishments.add(p.getType().getName());
        }
        for (Punishment p : PunishmentManager.get().getPunishments(UUIDManager.get().getUUID(player.getSingle(e).getName()), PunishmentType.MUTE, true)) {
            punishments.add(p.getType().getName());
        }
        for (Punishment p : PunishmentManager.get().getPunishments(UUIDManager.get().getUUID(player.getSingle(e).getName()), PunishmentType.TEMP_MUTE, true)) {
            punishments.add(p.getType().getName());
        }
        for (Punishment p : PunishmentManager.get().getPunishments(UUIDManager.get().getUUID(player.getSingle(e).getName()), PunishmentType.WARNING, true)) {
            punishments.add(p.getType().getName());
        }
        for (Punishment p : PunishmentManager.get().getPunishments(UUIDManager.get().getUUID(player.getSingle(e).getName()), PunishmentType.TEMP_WARNING, true)) {
            punishments.add(p.getType().getName());
        }
        return punishments.toArray(new String[punishments.size()]);
    }
}
