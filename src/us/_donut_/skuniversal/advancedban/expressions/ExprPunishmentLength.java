package us._donut_.skuniversal.advancedban.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("AdvancedBan - Punish Duration")
@Description("Returns the punish duration on Punishment event.")
public class ExprPunishmentLength extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprPunishmentLength.class, String.class, ExpressionType.SIMPLE, "[the] [AdvancedBan] punish[ment] (length|duration)");
    }

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
        if (!ScriptLoader.isCurrentEvent(PunishmentEvent.class)) {
            Skript.error("You can not use punishment length expression in any event but on punish.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the punishment length";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{((PunishmentEvent)e).getPunishment().getDuration(true)};
    }
}
