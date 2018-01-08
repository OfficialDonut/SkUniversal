package us._donut_.skuniversal.advancedban;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("AdvancedBan - Punishment Type")
@Description("Returns the punish type on Punishment event.")
public class ExprPunishmentType extends SimpleExpression<String> {

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
        if (!ScriptLoader.isCurrentEvent(PunishmentEvent.class)) {
            Skript.error("You can not use punishment type expression in any event but on punish.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "the punishment type";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{((PunishmentEvent)e).getPunishment().getType().getName()};
    }
}
