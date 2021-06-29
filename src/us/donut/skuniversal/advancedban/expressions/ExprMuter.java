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

@Name("AdvancedBan - Muter")
@Description("Returns the muter of a player.")
@Examples({"send \"You are muted by %muter of player%.\""})
public class ExprMuter extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprMuter.class, String.class, ExpressionType.COMBINED,
                "[the] [AdvancedBan] muter of %offlineplayer%",
                "%offlineplayer%'s [AdvancedBan] muter");
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
        return "the muter of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        Punishment mute = punishmentManager.getMute(uuidManager.getUUID(player.getSingle(e).getName()));
        return mute == null ? null : new String[]{mute.getOperator()};
    }
}
