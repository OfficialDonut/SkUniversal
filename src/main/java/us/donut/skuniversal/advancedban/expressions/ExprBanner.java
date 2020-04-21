package us.donut.skuniversal.advancedban.expressions;

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

import static us.donut.skuniversal.advancedban.AdvancedBanHook.*;

@Name("AdvancedBan - Banner")
@Description("Returns the banner of a player.")
@Examples({"send \"%{_player}% was banned by %banner of {_player}%.\""})
public class ExprBanner extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprBanner.class, String.class, ExpressionType.COMBINED,
                "[the] [AdvancedBan] banner of %offlineplayer%",
                "%offlineplayer%'s [AdvancedBan] banner");
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
        return "the banner of player " + player.toString(e, b);
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (player.getSingle(e) == null) return null;
        Punishment ban = punishmentManager.getBan(uuidManager.getUUID(player.getSingle(e).getName()));
        return ban == null ? null : new String[]{ban.getOperator()};
    }
}
