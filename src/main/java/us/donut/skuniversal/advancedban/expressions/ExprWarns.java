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
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import static us.donut.skuniversal.advancedban.AdvancedBanHook.*;

@Name("AdvancedBan - Warn Number")
@Description("Returns number of the warns of a player.")
@Examples({"send \"You have %warns of player% warns!\""})
public class ExprWarns extends SimpleExpression<Number> {

    static {
        Skript.registerExpression(ExprWarns.class, Number.class, ExpressionType.COMBINED,
                "[the] [(number|amount) of] [AdvancedBan] warns of %offlineplayer%",
                "%offlineplayer%'s [(number|amount) of] [AdvancedBan] warns");
    }

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
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
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
        if (player.getSingle(e) == null) return null;
        return new Number[]{punishmentManager.getCurrentWarns(uuidManager.getUUID(player.getSingle(e).getName()))};
    }
}
