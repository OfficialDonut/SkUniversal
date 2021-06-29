package us.donut.skuniversal.advancedban.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.leoko.advancedban.utils.Punishment;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

import static us.donut.skuniversal.advancedban.AdvancedBanHook.punishmentManager;
import static us.donut.skuniversal.advancedban.AdvancedBanHook.uuidManager;

@Name("AdvancedBan - Mute Time")
@Description("Returns the mute time of a player.")
@Examples({"send \"You can continue talking after %remaining mute time of player%.\""})
public class ExprMuteTime extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprMuteTime.class, String.class, ExpressionType.COMBINED,
                "[the] remaining [AdvancedBan] mute time of %offlineplayer%",
                "%offlineplayer%'s remaining [AdvancedBan] mute time");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
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
        if (player.getSingle(e) == null) return null;
        Punishment mute = punishmentManager.getMute(uuidManager.getUUID(player.getSingle(e).getName()));
        return mute == null ? null : new String[]{mute.getDuration(false)};
    }
}
