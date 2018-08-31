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

@Name("AdvancedBan - Ban Reason")
@Description("Returns the ban reason of a player.")
@Examples({"send \"%{_player}% was banned with reason %ban reason of {_player}%.\""})
public class ExprBanReason extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprBanReason.class, String.class, ExpressionType.COMBINED,
                "[the] [AdvancedBan] ban reason of %offlineplayer%",
                "%offlineplayer%'s [AdvancedBan] ban reason");
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
        return "the ban reason of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        Punishment ban = punishmentManager.getBan(uuidManager.getUUID(player.getSingle(e).getName()));
        return ban == null ? null : new String[]{ban.getReason()};
    }
}
