package us._donut_.skuniversal.advancedban.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import me.leoko.advancedban.utils.Punishment;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us._donut_.skuniversal.advancedban.AdvancedBanHook.*;

@Name("AdvancedBan - Punishments")
@Description("Returns the active punishments of a player.")
@Examples({"send \"Your punishments: %active punishments of player%\""})
public class ExprPunishments extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPunishments.class, String.class, ExpressionType.COMBINED,
                "[(all [[of] the]|the)] [(current|active)] [AdvancedBan] punishments of %offlineplayer%",
                "[all of] %offlineplayer%'s [(current|active)] [AdvancedBan] punishments");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
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
        if (player.getSingle(e) == null) return null;
        return punishmentManager.getPunishments(uuidManager.getUUID(player.getSingle(e).getName()), null, true).stream().map(Punishment::getName).toArray(String[]::new);
    }
}
